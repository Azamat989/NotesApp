package com.example.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout

import com.example.notes.R
import com.example.notes.model.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor (
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun todoInit(todo: Todo, callback: (() -> Unit)?) {

        todoDescription.text = todo.description
        checkBox.isChecked = todo.isComplete

        if (todo.isComplete) {
            createStrikeThrough()
        }

        setCheckStateListener(todo, callback)
    }

    private fun setCheckStateListener(todo: Todo, callback: (() -> Unit)? = null) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            todo.isComplete = isChecked
            callback?.invoke()
            if (isChecked) {
                createStrikeThrough()
            } else {
                removeStrikeThrough()
            }
        }
    }

    private fun createStrikeThrough() {
        todoDescription.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        todoDescription.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
