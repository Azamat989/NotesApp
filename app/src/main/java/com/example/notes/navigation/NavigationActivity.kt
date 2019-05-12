package com.example.notes.navigation

import android.content.Intent
import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.appcompat.app.AppCompatActivity

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.notes.create.CreateActivity
import com.example.notes.R
import com.example.notes.tasks.TasksListFragment
import com.example.notes.notes.NotesListFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity(), TasksListFragment.TouchActionDelegate, NotesListFragment.TouchActionDelegate {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_task -> {
                replaceFragment(TasksListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_note -> {
                replaceFragment(NotesListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(TasksListFragment.newInstance())
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()
    }

    private fun goToCreateActivity(fragmentType: String) {
        startActivity(Intent(this, CreateActivity::class.java).apply {
            putExtra(FRAGMENT_TYPE_KEY, fragmentType)
        })
    }

    override fun onAddButtonClicked(fragmentType: String) {
        goToCreateActivity(fragmentType)
    }

    companion object {
        const val FRAGMENT_TYPE_KEY = "f_t_k"
        const val FRAGMENT_VALUE_NOTE = "f_v_n"
        const val FRAGMENT_VALUE_TASK = "f_v_t"
    }
}
