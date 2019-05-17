package com.example.notes.tasks

interface TaskListViewContract {

    fun onTodoUpdate(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
}