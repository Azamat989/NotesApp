package com.example.notes.notes

import com.example.notes.application.NoteApplication
import com.example.notes.database.AppDatabase
import com.example.notes.model.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor(): INoteModel {

    private var databaseClient = AppDatabase.getInstance(NoteApplication.instance.applicationContext)

    override fun addNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().addNote(note)
        callback.invoke(true)
    }

    override fun retrieveNotes(): MutableList<Note> = databaseClient.noteDao().retrieveNotes()


    override fun updateNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().updateNote(note)
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().deleteNote(note)
        callback.invoke(true)
    }

//    override fun getFakeData(): MutableList<Note> = retrieveNotes()

//            mutableListOf(
//            Note(description = "Note one!"),
//            Note(description = "Note two!"))
}