package com.example.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.foundations.BaseRecyclerAdapter
import com.example.notes.model.Task
import com.example.notes.navigation.NavigationActivity
import com.example.notes.views.TaskView
import kotlinx.android.synthetic.main.item_add_button.view.*

class TaskAdapter(
        taskList: List<Task> = mutableListOf(),
        val touchActionDelegate: TasksListFragment.TouchActionDelegate
): BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType == TYPE_ADD_BUTTON) {
                AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_add_button, parent, false))
            } else {
                TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
            }

    class TaskViewHolder(view: View): BaseViewHolder<Task>(view) {
        override fun onBind(data: Task) {
            (view as TaskView).initView(data)
        }
    }
    
     inner class AddButtonViewHolder (view: View): BaseRecyclerAdapter.AddButtonViewHolder(view) {
         override fun onBind(data: Unit) {
             view.addBtnTxtView.text = view.context.getText(R.string.add_task_button)
             view.setOnClickListener {
                 touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_TASK)
             }
         }
    }
}