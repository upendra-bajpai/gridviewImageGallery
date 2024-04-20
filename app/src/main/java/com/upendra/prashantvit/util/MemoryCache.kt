package com.upendra.prashantvit.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import com.upendra.prashantvit.R
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class MemoryCache {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8
    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, bitmap: Bitmap): Int {
            return bitmap.byteCount / 1024
        }
    }

    operator fun get(key: String): Bitmap? = memoryCache[key]

    operator fun set(key: String, bitmap: Bitmap) {
        memoryCache.put(key, bitmap)
    }
}

class DiskCache(private val context: Context) {
    operator fun get(key: String): Bitmap? {
        val file = File(context.cacheDir, key.hashCode().toString())
        return if (file.exists()) BitmapFactory.decodeFile(file.absolutePath) else null
    }

    operator fun set(key: String, bitmap: Bitmap) {
        val file = File(context.cacheDir, key.hashCode().toString())
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.close()
    }
}

class ImageLoader(private val context: Context) {
    private val memoryCache = MemoryCache()
    private val diskCache = DiskCache(context)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val activeJobs = mutableMapOf<String, Job>()

    fun loadBitmap(imageUrl: String, imageView: ImageView) {
        var bitmap = memoryCache[imageUrl]
        Log.d("alex", "loadBitmap: url $imageUrl")
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            Log.d("alex", "loadBitmap: bitload ")
        } else {
            bitmap = diskCache[imageUrl]
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                memoryCache[imageUrl] = bitmap
                Log.d("alex", "loadBitmap: diskload ")
            } else {
                imageView.setImageResource(R.drawable.wait)
                val job = coroutineScope.launch {
                    try {
                        bitmap = downloadBitmap(imageUrl)
                        bitmap = bitmap?.let { processBitmap(it, 392,392,60) }
                        bitmap?.let {
                            memoryCache[imageUrl] = it
                            diskCache[imageUrl] = it
                            withContext(Dispatchers.Main) {
                                imageView.setImageBitmap(it)
                            }
                        }
            /*            bitmap = downloadBitmap(imageUrl)
                        bitmap?.let {
                            memoryCache[imageUrl] = it
                            diskCache[imageUrl] = it
                            withContext(Dispatchers.Main) {
                                imageView.setImageBitmap(it)
                                Log.d("alex", "loadBitmap: urlload")
                            }
                        }*/
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Handle the exception
                        withContext(Dispatchers.Main) {
                            imageView.setImageResource(R.drawable.warning)
                        }
                    }
                }
                activeJobs[imageUrl] = job
            }
        }
    }

    fun cancel(imageUrl: String) {
        activeJobs[imageUrl]?.cancel()
        activeJobs.remove(imageUrl)
        Log.d("alex", "cancel: ${activeJobs.hashCode()}")
    }

    private suspend fun downloadBitmap(url: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.connect()
            val inputStream = conn.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            null
        }
    }
}
