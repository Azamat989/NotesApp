package com.example.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.foundations.BaseRecyclerAdapter
import com.example.notes.model.Note
import com.example.notes.navigation.NavigationActivity
import com.example.notes.views.NoteView
import kotlinx.android.synthetic.main.item_add_button.view.*

class NoteAdapter(
        notesList: List<Note> = mutableListOf(),
        val touchActoinDelegate: NotesListFragment.TouchActionDelegate
): BaseRecyclerAdapter<Note>(notesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType == TYPE_ADD_BUTTON) {
                AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_add_button, parent, false))
            } else {
                NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
            }

    class NoteViewHolder(view: View): BaseViewHolder<Note>(view) {
        override fun onBind(data: Note) {
            (view as NoteView).initView(data)
        }
    }

    inner class AddButtonViewHolder(view: View): BaseRecyclerAdapter.AddButtonViewHolder(view) {
         override fun onBind(data: Unit) {
            view.addBtnTxtView.text = view.context.getText(R.string.add_note_button)

             view.setOnClickListener {
                 touchActoinDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_NOTE)
             }
        }
    }

}