package com.example.notes.tasks

import com.example.notes.model.Task

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    fun getFakeData(): MutableList<Task>

    fun addTask(task: Task, callback: SuccessCallback)
    fun retrieveTask(): Task
    fun updateTask(task: Task, callback: SuccessCallback)
    fun deleteTask(task: Task, callback: SuccessCallback)

}