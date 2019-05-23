package com.example.notes.tasks

import android.util.Log
import com.example.notes.model.Task
import com.example.notes.model.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    override fun addTask(task: Task, callback: SuccessCallback) {
        Log.d("AddTask", task.toString())
        callback.invoke(true)
    }

    override fun retrieveTask(): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFakeData(): MutableList<Task> = mutableListOf(
            Task("Testing One!", mutableListOf(
                    Todo("Should to do ONE!"),
                    Todo("Should to do TWO!")
            )),
            Task("Testing Two!", mutableListOf(
                    Todo("Should to do ONE!"),
                    Todo("Should to do TWO!")
            )))

}