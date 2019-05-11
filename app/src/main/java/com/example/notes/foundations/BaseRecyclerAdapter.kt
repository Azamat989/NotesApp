package com.example.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.notes.NoteAdapter
import com.example.notes.tasks.TaskAdapter

abstract class BaseRecyclerAdapter<T> (
        protected val list: List<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
            if (position == 0) {
                TYPE_ADD_BUTTON
            } else {
                TYPE_INFO
            }

    override fun getItemCount(): Int = list.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder ) {
            holder.onBind(Unit)
        } else {
            (holder as BaseViewHolder<T>).onBind(list[position - 1])
        }
    }

    abstract class BaseViewHolder<E> (val view: View): RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E)
    }

    abstract class AddButtonViewHolder (view: View): BaseViewHolder<Unit>(view)

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

}