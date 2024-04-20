package com.upendra.prashantvit.common

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.upendra.prashantvit.util.ImageLoader


/**
 * Created by Upendra on 19/2/2022.
 */

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Log.d("alex", "loadImage: $url")
    if (!url.contains("https")) return
    else{
         ImageLoader(context).loadBitmap(url,this)

    }


/*    imageLoader.loadBitmap(imageUrl, holder.imageView)
    val gurl = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader("User-Agent", "your-user-agent")
            .build()
    )
    Glide.with(this).load(gurl).override(196,196).into(this)*/
}
