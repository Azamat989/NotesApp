package com.example.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.R
import com.example.notes.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initTaskView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit, deleteTaskCallback: () -> Unit) {
        resetChildViews()
        this.task = task
        //init task line
        initTaskLine(deleteTaskCallback)
        //add  TodoViews to container
        addTodoViews(todoCheckedCallback)
    }

    private fun resetChildViews() {
        todoContainer.removeAllViewsInLayout()
    }

    private fun initTaskLine(deleteTaskCallback: () -> Unit) {
        taskTitle.text = task.title
        imgBtnDeleteTask.setOnClickListener {
            deleteTaskCallback.invoke()
        }
    }

    private fun addTodoViews(todoCheckedCallback: (Int, Boolean) -> Unit) {
        task.todos.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                initTodoView(todo) { isChecked ->

                    todoCheckedCallback.invoke(todoIndex, isChecked)

                    if (taskIsCompleted()) {
                        this@TaskView.taskTitle.setStrikeThrough()
                    } else {
                        this@TaskView.taskTitle.removeStrikeThrough()
                    }
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun taskIsCompleted(): Boolean =
            task.todos.filter { !it.isComplete }.isEmpty()
}