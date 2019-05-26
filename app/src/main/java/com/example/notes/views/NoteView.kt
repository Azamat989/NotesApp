package com.example.notes.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteView @JvmOverloads constructor(
        context: Context,
        attrb: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrb, defStyleAttr) {

    fun initView(note: Note, deleteNoteBtnClicked: () -> Unit) {
        noteDescription.text = note.description
        imgBtnDeleteNote.setOnClickListener {
            deleteNoteBtnClicked.invoke()
        }
    }


}