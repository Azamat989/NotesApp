package com.example.notes.tasks

import com.example.notes.model.Task

interface TaskListViewContract {

    fun onTodoUpdate(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
    fun onTaskDelete(taskIndex: Int)
}