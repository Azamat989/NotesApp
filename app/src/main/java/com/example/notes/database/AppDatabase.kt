package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.model.Note
import com.example.notes.model.Tag
import com.example.notes.model.TaskEntity
import com.example.notes.model.Todo


const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database (version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, Todo::class, Tag::class, Note::class])
abstract class AppDatabase : RoomDatabase() {

    //Insert DAO below
    abstract fun noteDao() : NoteDao
    abstract fun taskDao() : TaskDao


    companion object {

        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): AppDatabase? =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .build()

    }
}