package com.example.interfaceloginform

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class registerform : AppCompatActivity() {

    lateinit var editNama: TextInputEditText
    lateinit var editEmail: TextInputEditText
    lateinit var editPass: TextInputEditText
    lateinit var editConPass: TextInputEditText

    lateinit var inputNama: TextInputLayout
    lateinit var inputEmail: TextInputLayout
    lateinit var inputPass: TextInputLayout
    lateinit var inputConPass: TextInputLayout
    lateinit var tvSignIn: TextView

    lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registerform)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editNama = findViewById(R.id.editNama)
        editEmail = findViewById(R.id.editEmail)
        editPass = findViewById(R.id.editPass)
        editConPass = findViewById(R.id.editConPass)

        inputNama = findViewById(R.id.inputNama)
        inputEmail = findViewById(R.id.inputEmail)
        inputPass = findViewById(R.id.inputPass)
        inputConPass = findViewById(R.id.inputConPass)
        tvSignIn = findViewById(R.id.tvSignIn)

        btnNext = findViewById(R.id.btnNext)

        tvSignIn.setOnClickListener {
            startActivity(Intent(this, loginactivity::class.java))
            finish()
        }

        btnNext.setOnClickListener {

            val nama = editNama.text.toString()
            val email = editEmail.text.toString()
            val pass = editPass.text.toString()
            val conpass = editConPass.text.toString()

            if (nama.isEmpty()) {
                inputNama.error = "Nama wajib diisi"
                return@setOnClickListener
            } else {
                inputNama.error = null
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.error = "Email tidak valid"
                return@setOnClickListener
            } else {
                inputEmail.error = null
            }

            if (pass.length < 6) {
                inputPass.error = "Password minimal 6 karakter"
                return@setOnClickListener
            } else {
                inputPass.error = null
            }

            if (pass != conpass) {
                inputConPass.error = "Password tidak sama"
                return@setOnClickListener
            } else {
                inputConPass.error = null
            }

            val intent = Intent(this, register2::class.java)
            intent.putExtra("nama", nama)
            intent.putExtra("email", email)
            intent.putExtra("password", pass)

            startActivity(intent)
        }

    }
}