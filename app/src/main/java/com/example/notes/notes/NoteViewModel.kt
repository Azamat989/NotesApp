package com.example.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.foundations.ApplicationScope
import com.example.notes.foundations.ApplicationScope.scope
import com.example.notes.model.Note
import com.example.notes.tasks.ITaskModel
import com.example.notes.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: INoteModel

    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        Toothpick.inject(this, scope)
        loadNotes()
    }

    fun loadNotes() {
        _noteListLiveData.postValue(model.retrieveNotes())
    }

    override fun deleteNote(note: Note) {
        model.deleteNote(note) {
            if (it) {
                loadNotes()
            }
        }
    }
}