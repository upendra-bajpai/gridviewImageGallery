package com.upendra.prashantvit.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.upendra.prashantvit.data.model.MovieModel
import com.upendra.prashantvit.data.pagingdatasource.ImagePagingDataSource
import com.upendra.prashantvit.network.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @MoviePagingSource class that will obtain the data, need a Pager that will provide the data here as a flow.
 * @Returns PagingData<Model>
 *
 */

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val userService: MovieService

) : ImageRepository {
    override fun getMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { ImagePagingDataSource(userService) }
        ).flow
    }


    companion object {
        const val NETWORK_PAGE_SIZE = 1
    }
}