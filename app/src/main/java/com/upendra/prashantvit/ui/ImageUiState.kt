package com.upendra.prashantvit.ui

import android.content.Context
import androidx.paging.LoadState
import com.upendra.prashantvit.R
import com.upendra.prashantvit.common.BaseUiState

/**
 * Created by Upendra on 19/2/2022.
 */
data class MoviesUiState(
    private val loadState: LoadState
) : BaseUiState() {

    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?: context.getString(R.string.something_went_wrong)
    } else ""
}