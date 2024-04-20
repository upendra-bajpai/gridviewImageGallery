package com.upendra.prashantvit.util.ext

import androidx.databinding.ViewDataBinding

/**
 * Created by Upendra on 19/2/2022.
 */


fun <T : ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
    action()
    executePendingBindings()
}
