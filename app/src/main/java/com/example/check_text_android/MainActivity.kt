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

            // Also check if the password is empty
            val emptyBool = passText.isEmpty() || confirmText.isEmpty()
            if (emptyBool) { button.text = "Puste hasło!" }

            val zgodneBool = passText == confirmText
            val duzaBool = passText.any { it.isUpperCase() }
            val malaBool = passText.any { it.isLowerCase() }
            val cyfraBool = passText.any { it.isDigit() }
            val znakBool = passText.any { it.isLetterOrDigit() }

            // Set the state of the CheckedTextViews
            zgodne.isChecked = zgodneBool
            if (!zgodneBool) { button.text = "Hasła nie są zgodne!" }
            duza.isChecked = duzaBool
            if (!duzaBool) { button.text = "Brak dużej litery!" }
            mala.isChecked = malaBool
            if (!malaBool) { button.text = "Brak małej litery!" }
            cyfra.isChecked = cyfraBool
            if (!cyfraBool) { button.text = "Brak cyfry!" }
            znak.isChecked = znakBool
            if (!znakBool) { button.text = "Brak znaku!" }

            // Enable the button if all the conditions are met
            val wynik = zgodneBool && duzaBool && malaBool && cyfraBool && znakBool
            if (wynik) {
                button.text = "Zaloguj"
            }
            button.isEnabled = wynik
            return wynik
        }

        // Listen for changes in the password input or the password confirmation input
        passInput.addTextChangedListener { checkPass() }
        passConfirm.addTextChangedListener { checkPass() }

        // Listen for clicks on the button
        button.setOnClickListener {
            if (checkPass()) {
                // Change the text of the button to "Zalogowano"
                button.text = "Zalogowano!"
            } else {
                // Show a snackbar with the message "Hasła nie są zgodne"
                button.text = "Potwierdź"
            }
        }
    }
}