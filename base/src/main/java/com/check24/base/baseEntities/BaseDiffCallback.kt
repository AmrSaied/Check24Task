package com.check24.base.baseEntities

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * A generic diff callback that checks if items are the same by default equal implementation
 */
open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}