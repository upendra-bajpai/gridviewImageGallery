package com.upendra.prashantvit.common

import android.view.View

/**
 * Created by Upendra on 19/2/2022.
 */
open class BaseUiState {
    fun getViewVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}