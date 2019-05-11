package com.example.notes.tasks


import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.model.Task
import com.example.notes.model.Todo
import kotlinx.android.synthetic.main.fragment_tasks_list.*

class TasksListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter(mutableListOf(
                Task("Testing One!", mutableListOf(
                        Todo("Should to do ONE!", true),
                        Todo("Should to do TWO!")
                )),
                Task("Testing Two!", mutableListOf(
                        Todo("Should to do ONE!"),
                        Todo("Should to do TWO!")
                ))
        ))
        recyclerViewTasks.adapter = adapter

    }

    companion object {
        fun newInstance() = TasksListFragment()
    }

}
