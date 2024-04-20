package com.upendra.prashantvit.network

import com.upendra.prashantvit.data.model.MovieModel
import retrofit2.http.GET

/**
 * Created by Upendra on 19/2/2022.
 */
interface MovieService {

    @GET("api/v2/content/misc/media-coverages?limit=100")
    suspend fun getMovies(
    ): List<MovieModel>

}