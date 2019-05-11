package com.example.notes.navigation

import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.appcompat.app.AppCompatActivity

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.tasks.TasksListFragment
import com.example.notes.notes.NotesListFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

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

}
