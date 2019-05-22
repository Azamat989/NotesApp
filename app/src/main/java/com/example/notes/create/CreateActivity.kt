package com.example.notes.create

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notes.R
import com.example.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity(), CreateNoteFragment.OnFragmentInteractionListener, CreateTaskFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar?.title = ""

        intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {
            when {
                this == NavigationActivity.FRAGMENT_VALUE_NOTE -> createFragment(CreateNoteFragment.newInstance())
                this == NavigationActivity.FRAGMENT_VALUE_TASK -> createFragment(CreateTaskFragment.newInstance())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.saveItem -> {
                supportFragmentManager.findFragmentById(R.id.fragmentHolder).run {

                    if (this is CreateTaskFragment) {
                        this.saveTask { success ->
                            if(success) {
                                this@CreateActivity.supportFinishAfterTransition()
                            } else {
                                Toast.makeText(this@CreateActivity, getString(R.string.toast_error_saving), Toast.LENGTH_LONG).show()
                            }

                        }
                    } else if (this is CreateNoteFragment) {

                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun createFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()

    }
}
