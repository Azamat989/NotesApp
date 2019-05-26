package com.example.notes.database

import androidx.room.*
import com.example.notes.model.Task
import com.example.notes.model.TaskEntity
import com.example.notes.model.Todo

@Dao
interface TaskDao {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodos(todo: Todo)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks() : MutableList<Task>
}