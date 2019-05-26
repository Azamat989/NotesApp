package com.example.notes.tasks

import com.example.notes.model.Task
import com.example.notes.model.Todo

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    fun addTask(task: Task, callback: SuccessCallback)

    fun retrieveTasks(): MutableList<Task>

    fun updateTask(task: Task, callback: SuccessCallback)

    fun updateTodo(todo: Todo, callback: SuccessCallback)

    fun deleteTask(task: Task, callback: SuccessCallback)

}