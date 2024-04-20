package com.upendra.prashantvit.data.repository

import androidx.paging.PagingData
import com.upendra.prashantvit.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Upendra on 19/2/2022.
 */

interface ImageRepository {
      fun getMovies(): Flow<PagingData<MovieModel>>
}