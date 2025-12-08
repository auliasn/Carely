package com.example.carely

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextUsername = findViewById<EditText>(R.id.editTextTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            if (username == "aulia" && password == "123") {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(KEY_USERNAME, username)
                intent.putExtra(KEY_PASSWORD, password)
                startActivity(intent)
                finish()
                Toast.makeText(
                    this,
                    "Login Succesfull",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Login Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    companion object KEY {
        const val KEY_USERNAME = "UserName"
        const val KEY_PASSWORD = "password"
    }
}