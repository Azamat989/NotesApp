package com.example.notes.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.model.Note
import kotlinx.android.synthetic.main.fragment_notes_list.view.*

class NoteListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: NoteAdapter
    private lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    private lateinit var daActionDelegate: NoteListViewContract

    fun initView(touchActionDelegate: NotesListFragment.TouchActionDelegate,
                 daActionDelegate: NoteListViewContract) {

        setupDelegates(touchActionDelegate, daActionDelegate)
        setupView()
    }

    private fun setupDelegates(noDelegates: NotesListFragment.TouchActionDelegate,
                               daDelegate: NoteListViewContract) {
        touchActionDelegate = noDelegates
        daActionDelegate = daDelegate
    }

    private fun setupView() {

        recyclerViewNotes.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(touchActionDelegate = touchActionDelegate, dateActionDelegate = daActionDelegate)
        recyclerViewNotes.adapter = adapter
    }

    fun updateList(list: MutableList<Note>) {
        adapter.updateList(list)
    }

}