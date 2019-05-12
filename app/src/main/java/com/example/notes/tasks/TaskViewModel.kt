package com.example.notes.tasks

import androidx.lifecycle.ViewModel
import com.example.notes.model.Task
import com.example.notes.model.Todo

class TaskViewModel: ViewModel() {

    fun getFakeData(): List<Task> = mutableListOf(
                Task("Testing One!", mutableListOf(
                        Todo("Should to do ONE!"),
                        Todo("Should to do TWO!")
                )),
                Task("Testing Two!", mutableListOf(
                        Todo("Should to do ONE!"),
                        Todo("Should to do TWO!")
                )))
}