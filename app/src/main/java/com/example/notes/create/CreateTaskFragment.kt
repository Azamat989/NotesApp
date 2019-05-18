package com.example.notes.create

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.notes.R
import com.example.notes.foundations.StateChangeTextWatcher
import com.example.notes.model.Todo
import com.example.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*

class CreateTaskFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTaskView.edtTaskTitle.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                }

                super.afterTextChanged(s)
            }

        })

    }

    private fun addTodoView() {

        Toast.makeText(context, "Add Todo View", Toast.LENGTH_LONG).show()
        val view = LayoutInflater.from(context).inflate(R.layout.view_create_todo, taskTodoContainerView, false) as CreateTodoView

        view.edtTodoDescription.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {

                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                    removeTodoView(view)
                }

                super.afterTextChanged(s)
            }
        })
        Toast.makeText(context, "ADD T ODO  VIEW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_LONG).show()
        taskTodoContainerView.addView(view)
    }

    private fun removeTodoView(view: View) {

        taskTodoContainerView.removeView(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = CreateTaskFragment()
    }
}
