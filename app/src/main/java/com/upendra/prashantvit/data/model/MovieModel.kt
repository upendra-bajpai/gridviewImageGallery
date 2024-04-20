package com.upendra.prashantvit.data.model

import com.google.gson.annotations.SerializedName
import com.upendra.prashantvit.Thumbnail

/**
 * Created by Upendra on 19/2/2022.
 */
data class MovieModel(
/*    val title: String,
    val thumbnailUrl: String,
    val id: Int,*/

    @SerializedName("id"            ) var id            : String?        = null,
    @SerializedName("title"         ) var title         : String?        = null,
    @SerializedName("language"      ) var language      : String?        = null,
    @SerializedName("thumbnail"     ) var thumbnail     : Thumbnail?     = Thumbnail(),
    @SerializedName("mediaType"     ) var mediaType     : Int?           = null,
    @SerializedName("coverageURL"   ) var coverageURL   : String?        = null,
    @SerializedName("publishedAt"   ) var publishedAt   : String?        = null,
    @SerializedName("publishedBy"   ) var publishedBy   : String?        = null,
)
