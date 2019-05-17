package com.example.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet

import androidx.constraintlayout.widget.ConstraintLayout

import com.example.notes.model.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initTodoView(todo: Todo, callback: ((Boolean) -> Unit)?) {

        todoDescription.text = todo.description
        checkBox.isChecked = todo.isComplete

        if (todo.isComplete) {
            todoDescription.setStrikeThrough()
        }

        setCheckStateListener(todo, callback)
    }

    private fun setCheckStateListener(todo: Todo, onTaskCompleteCallback: ((Boolean) -> Unit)? = null) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            //todo.isComplete = isChecked
            if (isChecked) {
                todoDescription.setStrikeThrough()
            } else {
                todoDescription.removeStrikeThrough()
            }
            onTaskCompleteCallback?.invoke(isChecked)
        }
    }
}
