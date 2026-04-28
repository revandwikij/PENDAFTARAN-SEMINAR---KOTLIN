package com.example.interfaceloginform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class hasil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")
        val hp = intent.getStringExtra("hp")
        val gender = intent.getStringExtra("gender")
        val seminar = intent.getStringExtra("seminar")

        tvHasil.text = """
            Pendaftaran Berhasil

            Nama: $nama
            Email: $email
            Nomor HP: $hp
            Jenis Kelamin: $gender
            Seminar: $seminar
        """.trimIndent()

        btnKembali.setOnClickListener {
            startActivity(Intent(this, mainmenu::class.java))
            finish()
        }
    }
}