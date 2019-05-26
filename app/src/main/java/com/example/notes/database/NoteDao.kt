package com.example.notes.database

import androidx.room.*
import com.example.notes.model.Note

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    fun retrieveNotes() : MutableList<Note>
}