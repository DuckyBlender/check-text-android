package com.example.check_text_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val passInput = findViewById<EditText>(R.id.passInput)
        val passCheck = findViewById<EditText>(R.id.passConfirm)

        val zgodneView = findViewById<TextView>(R.id.zgodneView)
        val duzaView = findViewById<TextView>(R.id.duzaView)
        val malaView = findViewById<TextView>(R.id.malaView)
        val cyfraView = findViewById<TextView>(R.id.cyfraView)
        val znakView = findViewById<TextView>(R.id.znakView)

        fun checkPass() {
            val pass = passInput.text.toString()
            val passConfirm = passCheck.text.toString()
            val zgodne = pass == passConfirm
            val duza = pass.any { it.isUpperCase() }
            val mala = pass.any { it.isLowerCase() }
            val cyfra = pass.any { it.isDigit() }
            val znak = pass.any { it.isLetterOrDigit() }

            zgodneView.text = "Hasła zgodne: $zgodne"
            duzaView.text = "Hasło zawiera dużą literę: $duza"
            malaView.text = "Hasło zawiera małą literę: $mala"
            cyfraView.text = "Hasło zawiera cyfrę: $cyfra"
            znakView.text = "Hasło zawiera znak specjalny: $znak"
        }

        // Listen for changes in the password input
        passInput.addTextChangedListener {
            checkPass()
        }

}