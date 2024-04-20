package com.upendra.prashantvit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.upendra.prashantvit.R
import com.upendra.prashantvit.databinding.ItemMovieBinding
import com.upendra.prashantvit.util.ImageLoader
import javax.inject.Inject

/**
 * Created by Upendra on 19/2/2022.
 */
class ImageAdapter @Inject constructor() :
    PagingDataAdapter<MovieItemUiState, ImageViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.bind(userItemUiState) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val binding = inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onViewRecycled(holder: ImageViewHolder) {
        super.onViewRecycled(holder)
        getItem(holder.bindingAdapterPosition)?.let { userItemUiState -> ImageLoader(holder.itemView.context).cancel(userItemUiState.getImageUrl()) }
      //  ImageLoader(holder.itemView.context).cancel(holder.getImageUrl)
    }

    object Comparator : DiffUtil.ItemCallback<MovieItemUiState>() {
        override fun areItemsTheSame(oldItem: MovieItemUiState, newItem: MovieItemUiState): Boolean {
            return oldItem.getImdbId() == newItem.getImdbId()
        }

        override fun areContentsTheSame(
            oldItem: MovieItemUiState,
            newItem: MovieItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}