package com.example.interfaceloginform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class loginactivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editPass: EditText
    lateinit var btnLogin: Button
    lateinit var tvSignUp: TextView

    fun login(email: String, password: String): Boolean {
        val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
        val savedEmail = sharedPref.getString("email", "")
        val savedPass = sharedPref.getString("password", "")

        return email == savedEmail && password == savedPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loginform)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editEmail = findViewById(R.id.editEmail)
        editPass = findViewById(R.id.editPass)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, registerform::class.java))
            finish()
        }

        btnLogin.setOnClickListener {

            val email = editEmail.text.toString().trim()
            val pass = editPass.text.toString().trim()

            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", "")
            val savedPass = sharedPref.getString("password", "")

            if (login(email, pass)) {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, mainmenu::class.java))
                finish()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}