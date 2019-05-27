package com.example.notes.tasks

import com.example.notes.application.NoteApplication
import com.example.notes.database.AppDatabase
import com.example.notes.model.Task
import com.example.notes.model.Todo
import com.example.notes.notes.TIMEOUT_DURATION_MILLIS
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient = AppDatabase.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, successCallback: SuccessCallback) {
        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: Exception) {
                successCallback.invoke(false)
            }
        }
        job.await()
        successCallback.invoke(true)
    }

    override suspend fun addTask(task: Task, callback: SuccessCallback) {

        val masterJob = GlobalScope.async {
            //adds TaskEntity component
            try {
                databaseClient.taskDao().addTask(task)
            } catch (e: Exception) {
                callback.invoke(false)
            }
            //adds todos list component
            addTodosInTask(task)
        }
        masterJob.await()
        callback.invoke(true)
    }

    override suspend fun retrieveTasks(callback: (List<Task>?) -> Unit) {

            val job = GlobalScope.async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient.taskDao().retrieveTasks() }
            }
            callback.invoke(job.await())
    }
    override suspend fun updateTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout( {
            databaseClient.taskDao().updateTask(task)
        }, callback)
    }

    override suspend fun updateTodo(todo: Todo, callback: SuccessCallback) {
        performOperationWithTimeout( {
            databaseClient.taskDao().updateTodo(todo)
        }, callback)
    }

    override suspend fun deleteTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout( {
            databaseClient.taskDao().deleteTask(task)
        }, callback)
    }

    private fun addTodosInTask(task: Task) : Job = GlobalScope.async {
        task.todos.forEach {todo ->
            databaseClient.taskDao().addTodos(todo)
        }
    }
}