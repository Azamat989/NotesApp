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
import com.example.notes.foundations.ApplicationScope
import com.example.notes.foundations.NullFieldChecker
import com.example.notes.foundations.StateChangeTextWatcher
import com.example.notes.model.Task
import com.example.notes.model.Todo
import com.example.notes.tasks.ITaskModel
import com.example.notes.tasks.TaskLocalModel
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

private const val MAX_TODO_ADDED = 8

class CreateTaskFragment : Fragment() {

    @Inject
    lateinit var model: ITaskModel

    private var listener: CreateNoteFragment.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)
    }

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

    private fun isTaskEmpty() : Boolean = taskTodoContainerView.edtTaskTitle.editableText.isNullOrEmpty()

    fun saveTask(callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            createTask()?.let {task ->
                model.addTask(task) {success ->
                    callback.invoke(success)
                }
            } ?: callback.invoke(false)
        }
    }

    private fun createTask() : Task? {

        if (!isTaskEmpty()) {

            taskTodoContainerView.run {

                lateinit var taskTitle: String
                val todoList: MutableList<Todo> = mutableListOf()
                  for (i in 0 until this.childCount) {

                    if (i == 0) {

                        taskTitle = getChildAt(i).edtTaskTitle.editableText.toString()

                    } else if (!getChildAt(i).edtTodoDescription.editableText?.toString().isNullOrEmpty())
                    {

                        todoList.add(Todo(description = getChildAt(i).edtTodoDescription.editableText.toString()))
                    }
                }

                return  Task(taskTitle, todoList)
            }

        } else {
            return null
        }
    }


    private fun addTodoView() {

        if (canAddToDo()) {

            val view = LayoutInflater.from(context).inflate(R.layout.view_create_todo, taskTodoContainerView, false).apply {

                edtTodoDescription.addTextChangedListener(object : StateChangeTextWatcher() {
                    override fun afterTextChanged(s: Editable?) {

                        if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                            addTodoView()
                        } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                            taskTodoContainerView.removeView(this@apply)
                        }

                        super.afterTextChanged(s)
                    }
                })
            }
            taskTodoContainerView.addView(view)
        }
    }

    private fun canAddToDo() : Boolean = (taskTodoContainerView.childCount < MAX_TODO_ADDED + 1) &&
                    !(taskTodoContainerView.getChildAt(taskTodoContainerView.childCount - 1) as NullFieldChecker).isFieldNull()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateNoteFragment.OnFragmentInteractionListener) {
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

