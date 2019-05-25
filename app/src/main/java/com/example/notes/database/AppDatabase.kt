package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database (version = DATABASE_SCHEMA_VERSION, entities = [])
abstract class AppDatabase : RoomDatabase() {

    //Insert DAO below


    companion object {

        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): AppDatabase? =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    }

}