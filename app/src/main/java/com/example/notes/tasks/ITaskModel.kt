package com.example.notes.tasks

import com.example.notes.model.Task
import com.example.notes.model.Todo

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    suspend fun addTask(task: Task, callback: SuccessCallback)

    suspend fun retrieveTasks(function: (List<Task>?) -> Unit)

    suspend fun updateTask(task: Task, callback: SuccessCallback)

    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)

    suspend fun deleteTask(task: Task, callback: SuccessCallback)

}