package com.example.notes.tasks


import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.model.Task
import com.example.notes.model.Todo
import kotlinx.android.synthetic.main.fragment_tasks_list.*

class TasksListFragment : Fragment() {

    lateinit var viewModel: TaskViewModel
    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        context?.let {
            if (it is TouchActionDelegate)
            touchActionDelegate = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()

        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter(viewModel.getFakeData(), touchActionDelegate)
        recyclerViewTasks.adapter = adapter

    }

    private fun bindViewModel() {
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(fragmentType: String)
    }
}
