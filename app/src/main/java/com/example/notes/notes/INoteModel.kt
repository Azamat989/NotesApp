package com.example.notes.notes

import com.example.notes.model.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {

    fun addNote(note: Note, callback: SuccessCallback)
    fun retrieveNotes(callback: (List<Note>?) -> Unit)
    fun updateNote(note: Note, callback: SuccessCallback)
    fun deleteNote(note: Note, callback: SuccessCallback)

//    fun getFakeData(): MutableList<Note>

}