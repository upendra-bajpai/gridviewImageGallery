package com.upendra.prashantvit.ui

import com.upendra.prashantvit.common.BaseUiState
import com.upendra.prashantvit.data.model.MovieModel

/**
 * Created by Upendra on 19/2/2022.
 * map it to a required model class with filtering, mapping in viewModel
 */
data class MovieItemUiState(private val userModel: MovieModel) : BaseUiState() {

    fun getImageUrl() = "${userModel.thumbnail?.domain}/${userModel.thumbnail?.basePath}/0/${userModel.thumbnail?.key}"

    fun getName() = userModel.title

    fun getImdbId() =" Id: ${ userModel.id }"

}