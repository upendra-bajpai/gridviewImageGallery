package com.upendra.prashantvit.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

private fun compressBitmap(bitmap: Bitmap, quality: Int): Bitmap {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
    val byteArray = stream.toByteArray()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

private fun resizeBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false)
}

 fun processBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int, quality: Int): Bitmap {
    val resizedBitmap = resizeBitmap(bitmap, newWidth, newHeight)
    return compressBitmap(resizedBitmap, quality)
}
