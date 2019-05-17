package com.example.notes.notes

import com.example.notes.model.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor(): INoteModel {
    override fun addNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieve(): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFakeData(): MutableList<Note> = mutableListOf(
            Note("Note one!"),
            Note("Note two!"))

}