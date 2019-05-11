package com.example.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.R
import com.example.notes.model.Task
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_todo.view.*

class TaskView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initView(task: Task) {
        this.task = task
        taskTitle.text = task.title

        task.todos.forEach { todo ->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                todoInit(todo) {
                    if (taskIsCompleted()) {
                        createStrikeThrough()
                    } else {
                        removeStrikeThrough()
                    }
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun taskIsCompleted(): Boolean = task.todos.filter { !it.isComplete }.isEmpty()

    private fun createStrikeThrough() {
        taskTitle.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        taskTitle.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}