package com.example.interfaceloginform

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.GrammaticalInflectionManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast

class seminar_registration : AppCompatActivity() {

    lateinit var radioGender: RadioGroup
    lateinit var spinnerSeminar: Spinner
    lateinit var btnRegistrasi: Button
    lateinit var editNama: TextInputEditText
    lateinit var editEmail: TextInputEditText
    lateinit var editNomor: TextInputEditText
    lateinit var cbSetuju: CheckBox

    lateinit var inputNama: TextInputLayout
    lateinit var inputEmail: TextInputLayout
    lateinit var inputNomor: TextInputLayout

    fun TextInputEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seminar_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGender = findViewById(R.id.radioGender)
        spinnerSeminar = findViewById(R.id.spinnerSeminar)

        editNama = findViewById(R.id.editNama)
        editEmail = findViewById(R.id.editEmail)
        editNomor = findViewById(R.id.editNomor)

        inputNama = findViewById(R.id.inputNama)
        inputEmail = findViewById(R.id.inputEmail)
        inputNomor = findViewById(R.id.inputNomor)

        btnRegistrasi = findViewById(R.id.btnRegistrasi)
        cbSetuju = findViewById(R.id.cbSetuju)

        val seminar = arrayOf(
            "Teknologi Artificial Intelligence",
            "Digital Marketing Strategy",
            "Cyber Security Awareness",
            "UI UX Design Fundamentals",
            "Startup dan Kewirausahaan Digital"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            seminar
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerSeminar.adapter = adapter

        editNama.afterTextChanged { nama ->
            if (nama.isEmpty()) {
                inputNama.error = "Nama wajib diisi"
            } else {
                inputNama.error = null
            }
        }

        editEmail.afterTextChanged { email ->
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.error = "Email tidak valid"
            } else {
                inputEmail.error = null
            }
        }

        editNomor.afterTextChanged { hp ->
            val regex = Regex("^08[0-9]{8,11}$")

            if (!regex.matches(hp)) {
                inputNomor.error = "Nomor HP tidak valid"
            } else {
                inputNomor.error = null
            }
        }

        btnRegistrasi.setOnClickListener {

            val nama = editNama.text.toString()
            val email = editEmail.text.toString()
            val hp = editNomor.text.toString()
            val regex = Regex("^08[0-9]{8,11}$")

            if (nama.isEmpty()) {
                inputNama.error = "Nama wajib diisi"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.error = "Email tidak valid"
                return@setOnClickListener
            }

            if (!regex.matches(hp)) {
                inputNomor.error = "Nomor HP tidak valid"
                return@setOnClickListener
            }

            if (radioGender.checkedRadioButtonId == -1) {
                android.widget.Toast.makeText(this, "Pilih jenis kelamin", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cbSetuju.isChecked) {
                Toast.makeText(this, "Anda harus menyetujui terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedSeminar = spinnerSeminar.selectedItem.toString()

            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data yang Anda isi sudah benar?")
                .setPositiveButton("Ya") { _, _ ->

                    val genderId = radioGender.checkedRadioButtonId
                    val gender = findViewById<android.widget.RadioButton>(genderId).text.toString()
                    val selectedSeminar = spinnerSeminar.selectedItem.toString()

                    val intent = android.content.Intent(this, hasil::class.java)
                    intent.putExtra("nama", nama)
                    intent.putExtra("email", email)
                    intent.putExtra("hp", hp)
                    intent.putExtra("gender", gender)
                    intent.putExtra("seminar", selectedSeminar)
                    startActivity(intent)
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}