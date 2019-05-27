package com.example.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.foundations.ApplicationScope
import com.example.notes.foundations.ApplicationScope.scope
import com.example.notes.model.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        Toothpick.inject(this, scope)
        loadTasks()
    }

    fun loadTasks() {
        GlobalScope.launch {
            model.retrieveTasks {taskList ->
                taskList?.let {
                    _taskListLiveData.postValue(it.toMutableList())
                }
            }
        }
    }

    override fun onTodoUpdate(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {

        _taskListLiveData.value?.let {
            val todo = it[taskIndex].todos[todoIndex]
            todo.apply {
                this.isComplete = isComplete
                this.taskID = it[taskIndex].uid
            }
            GlobalScope.launch {
                model.updateTodo(todo) {
                    loadTasks()
                }
            }
        }
    }

    override fun onTaskDelete(taskIndex: Int) {
        _taskListLiveData.value?.let {
            GlobalScope.launch {
                model.deleteTask(it[taskIndex]) {
                    loadTasks()
                }
            }
        }
    }
}