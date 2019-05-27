package com.example.notes.notes

import com.example.notes.model.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {

    suspend fun addNote(note: Note, callback: SuccessCallback)
    suspend fun retrieveNotes(callback: (List<Note>?) -> Unit)
    suspend fun updateNote(note: Note, callback: SuccessCallback)
    suspend fun deleteNote(note: Note, callback: SuccessCallback)

//    fun getFakeData(): MutableList<Note>

}