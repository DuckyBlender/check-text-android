package com.example.check_text_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the inputs and views
        val passInput = findViewById<EditText>(R.id.passInput)
        val passConfirm = findViewById<EditText>(R.id.passConfirm)

        val zgodne = findViewById<CheckedTextView>(R.id.zgodne)
        val duza = findViewById<CheckedTextView>(R.id.duza)
        val mala = findViewById<CheckedTextView>(R.id.mala)
        val cyfra = findViewById<CheckedTextView>(R.id.cyfra)
        val znak = findViewById<CheckedTextView>(R.id.znak)

        val button = findViewById<Button>(R.id.button)

        fun checkPass(): Boolean {
            val passText = passInput.text.toString()
            val confirmText = passConfirm.text.toString()
            val zgodneBool = passText == confirmText
            val duzaBool = passText.any { it.isUpperCase() }
            val malaBool = passText.any { it.isLowerCase() }
            val cyfraBool = passText.any { it.isDigit() }
            val znakBool = passText.any { it.isLetterOrDigit() }

            // Set the state of the CheckedTextViews
            zgodne.isChecked = zgodneBool
            duza.isChecked = duzaBool
            mala.isChecked = malaBool
            cyfra.isChecked = cyfraBool
            znak.isChecked = znakBool

            // Enable the button if all the conditions are met
            val wynik = zgodneBool && duzaBool && malaBool && cyfraBool && znakBool


            button.isEnabled = wynik
            return wynik
        }

        // Listen for changes in the password input or the password confirmation input
        passInput.addTextChangedListener { checkPass() }
        passConfirm.addTextChangedListener { checkPass() }

        // Listen for clicks on the button
        button.setOnClickListener {
            if (checkPass()) {
                // Pop up a message saying that the password is correct using snackbar
                var snackbar = Snackbar.make(findViewById(R.id.button), "Hasło poprawne", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
            else {
                // Pop up a message saying that the password is incorrect
                var snackbar = Snackbar.make(findViewById(R.id.button), "Hasło niepoprawne", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }
}