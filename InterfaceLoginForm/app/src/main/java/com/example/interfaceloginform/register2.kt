package com.example.interfaceloginform

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.appcompat.app.AlertDialog

class register2 : AppCompatActivity() {

    lateinit var radioGender: RadioGroup
    lateinit var cbCoding: CheckBox
    lateinit var cbGame: CheckBox
    lateinit var cbMusic: CheckBox
    lateinit var cbSport: CheckBox
    lateinit var spinnerJurusan: Spinner
    lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register2)
        val nama = intent.getStringExtra("nama") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        radioGender = findViewById(R.id.radioGender)
        cbCoding = findViewById(R.id.cbCoding)
        cbGame = findViewById(R.id.cbGame)
        cbMusic = findViewById(R.id.cbMusic)
        cbSport = findViewById(R.id.cbSport)
        spinnerJurusan = findViewById(R.id.spinnerJurusan)
        btnSignUp = findViewById(R.id.btnSignUp)

        val jurusan = arrayOf(
            "Informatika",
            "Sistem Informasi",
            "Teknik Komputer"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            jurusan
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerJurusan.adapter = adapter

        btnSignUp.setOnClickListener {

            if (radioGender.checkedRadioButtonId == -1) {
                Toast.makeText(this,"Pilih jenis kelamin",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var count = 0
            if (cbCoding.isChecked) count++
            if (cbGame.isChecked) count++
            if (cbMusic.isChecked) count++
            if (cbSport.isChecked) count++

            if (count < 3) {
                Toast.makeText(this,"Pilih minimal 3 hobi",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            sharedPref.edit()
                .putString("nama", nama)
                .putString("email", email)
                .putString("password", password)
                .apply()

            AlertDialog.Builder(this)
                .setTitle("Berhasil")
                .setMessage("Registrasi berhasil")
                .setPositiveButton("OK") { _, _ ->

                    startActivity(Intent(this, loginactivity::class.java))
                    finish()
                }
                .show()
        }

        btnSignUp.setOnLongClickListener {
            Toast.makeText(this, "Long Press terdeteksi", Toast.LENGTH_SHORT).show()
            true
        }
    }
}