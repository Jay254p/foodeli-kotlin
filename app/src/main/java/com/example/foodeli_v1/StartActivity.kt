package com.example.foodeli_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.foodeli_v1.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
private val binding: ActivityStartBinding by lazy {
    ActivityStartBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nextButtonStart.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.contactMenu)
        registerForContextMenu(textView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Options:")
        menuInflater.inflate(R.menu.context_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.xml_option_call -> {
                Toast.makeText( this, "You receive a call shortly.", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.xml_option_feedback -> {
                Toast.makeText( this, "You will be redirected shortly.", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.xml_option_report -> {
                Toast.makeText( this, "You will be redirected shortly.", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onContextItemSelected(item)
        }


    }

}