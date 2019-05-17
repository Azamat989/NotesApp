package com.example.notes.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.model.Task
import kotlinx.android.synthetic.main.fragment_tasks_list.view.*

class TaskListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: TaskAdapter
    private lateinit var touchActionDelegate: TasksListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(taskDelegate: TasksListFragment.TouchActionDelegate, dataDelegate: TaskListViewContract) {
        setDelegates(taskDelegate, dataDelegate)
        setUpView()
    }

    private fun setDelegates(taskDelegate: TasksListFragment.TouchActionDelegate,
                             dataDelegate: TaskListViewContract) {
        touchActionDelegate = taskDelegate
        dataActionDelegate = dataDelegate
    }

    private fun setUpView() {
        recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        adapter = TaskAdapter(
                touchActionDelegate = touchActionDelegate,
                dataActionDelegate = dataActionDelegate
        )
        recyclerViewTasks.adapter = adapter
    }

    fun updateList(list: MutableList<Task>) {
        adapter.updateList(list)
    }

}