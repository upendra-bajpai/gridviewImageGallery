package com.upendra.prashantvit.ui

import androidx.recyclerview.widget.RecyclerView
import com.upendra.prashantvit.databinding.ItemMovieBinding
import com.upendra.prashantvit.util.ext.executeWithAction

/**
 * Created by Upendra on 19/2/2022.
 */
class ImageViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: MovieItemUiState) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }
}