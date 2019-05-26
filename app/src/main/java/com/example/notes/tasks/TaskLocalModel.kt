package com.example.notes.tasks

import com.example.notes.application.NoteApplication
import com.example.notes.database.AppDatabase
import com.example.notes.model.Task
import com.example.notes.model.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient = AppDatabase.getInstance(NoteApplication.instance.applicationContext)

    override fun addTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().addTask(task)
        addTodosInTask(task)
        callback.invoke(true)
    }

    override fun retrieveTasks(): MutableList<Task> = databaseClient.taskDao().retrieveTasks()

    override fun updateTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().updateTask(task)
        callback.invoke(true)
    }

    override fun updateTodo(todo: Todo, callback: SuccessCallback) {
        databaseClient.taskDao().updateTodo(todo)
        callback.invoke(true)
    }

    override fun deleteTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().deleteTask(task)
        callback.invoke(true)
    }

    private fun addTodosInTask(task: Task) {
        task.todos.forEach {todo ->
            databaseClient.taskDao().addTodos(todo)
        }
    }

}