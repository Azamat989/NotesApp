package com.example.notes.notes

import com.example.notes.application.NoteApplication
import com.example.notes.database.AppDatabase
import com.example.notes.model.Note
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class NoteLocalModel @Inject constructor(): INoteModel {

    private var databaseClient = AppDatabase.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, successCallback: SuccessCallback) {
        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: Exception) {
                successCallback.invoke(false)
            }
        }
        job.await()
        successCallback.invoke(true)
    }

    override suspend fun addNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout( { databaseClient.noteDao().addNote(note) }, callback)
    }

    override suspend fun retrieveNotes(callback: (List<Note>?) -> Unit) {

        val databaseJob = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                databaseClient.noteDao().retrieveNotes()
            }
        }
        callback.invoke(databaseJob.await())
    }

    override suspend fun updateNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({databaseClient.noteDao().updateNote(note)}, callback)
    }

    override suspend fun deleteNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout( {databaseClient.noteDao().deleteNote(note)}, callback )
    }
}