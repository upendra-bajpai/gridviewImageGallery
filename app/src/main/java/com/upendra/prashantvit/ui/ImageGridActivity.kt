package com.upendra.prashantvit.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.upendra.prashantvit.common.FooterAdapter
import com.upendra.prashantvit.databinding.ActivityMovieBinding
import com.upendra.prashantvit.util.ext.collect
import com.upendra.prashantvit.util.ext.collectLast
import com.upendra.prashantvit.util.ext.executeWithAction
import com.upendra.prashantvit.viewModels.ImageViewModel
import com.upendra.prashantvit.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *@collectLast to
 * withLoadStateFooter() returns us a ConcatAdapter, use it to show loading status of the newly added paginatedData
 */
@AndroidEntryPoint
class ImageGridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val viewModel: ImageViewModel by viewModels()

    @Inject
    lateinit var userAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setListener()
        setAdapter()
        collectLast(viewModel.userItemsUiStates, ::setMovies)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }

    private fun setListener() {
        binding.addMore.setOnClickListener({userAdapter.refresh()})
        binding.btnRetry.setOnClickListener { userAdapter.retry() }
        binding.rvMovies.setHasFixedSize(true)
    }


    private fun setAdapter() {
        collect(flow = userAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setMoviesUiState
        )
        binding.rvMovies.adapter = userAdapter.withLoadStateFooter(FooterAdapter(userAdapter::retry))
    }

    private fun setMoviesUiState(loadState: LoadState) {
        binding.executeWithAction {
            moviessUiState = MoviesUiState(loadState)
        }
    }

    private suspend fun setMovies(userItemsPagingData: PagingData<MovieItemUiState>) {
        userAdapter.submitData(userItemsPagingData)
    }

}