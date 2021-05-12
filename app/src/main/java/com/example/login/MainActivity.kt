package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log.d
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.core.text.isDigitsOnly
import com.example.login.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val USERNAME_PATTERN: Pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{9,}$")
        private val NAME_PATTERN: Pattern = Pattern.compile("^[a-zA-Z\\s]*$")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save()
        }
        binding.buttonClear.setOnLongClickListener {
            clear()
            return@setOnLongClickListener true
        }
    }

    /**
     * checks if inputs are valid and "saves"
     */
    private fun save() {
        validateInput()
        //save()
    }

    /**
     * @return true if every field input type is valid
     */
    private fun validateInput(): Boolean {
        var result = true
        val email = binding.editTextEmail.text.trim()
        val username = binding.editTextUsername.text.trim()
        val firstName = binding.editTextFirstName.text.trim()
        val lastName = binding.editTextLastName.text.trim()
        val age = binding.editTextAge.text.trim()

        //EMAIL CHECK
        if (email.isEmpty()) {
            binding.editTextEmail.error = getString(R.string.empty_field_error_msg)
            result = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = getString(R.string.email_error_msg)
            result = false
        }

        //USERNAME CHECK
        if (username.isEmpty()) {
            binding.editTextUsername.error = getString(R.string.empty_field_error_msg)
            result = false
        }
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            binding.editTextUsername.error = getString(R.string.username_error_msg)
            result = false
        }

        //FIRST NAME CHECK
        if (firstName.isEmpty()) {
            binding.editTextFirstName.error = getString(R.string.empty_field_error_msg)
            result = false
        }
        if (!NAME_PATTERN.matcher(firstName).matches()) {
            binding.editTextFirstName.error = getString(R.string.name_error_msg)
            result = false
        }

        //LAST NAME CHECK
        if (lastName.isEmpty()) {
            binding.editTextLastName.error = getString(R.string.empty_field_error_msg)
            result = false
        }
        if (!NAME_PATTERN.matcher(lastName).matches()) {
            binding.editTextLastName.error = getString(R.string.name_error_msg)
            result = false
        }

        //AGE CHECK
        if (age.isEmpty()) {
            binding.editTextAge.error = getString(R.string.empty_field_error_msg)
            result = false
        }

        /*REDUNDANT CODE
         *EditTextAge input type is "Number" that already accepts only Ints
         */
        if (age.toString().toIntOrNull() == null) {
            binding.editTextAge.error = getString(R.string.age_error_msg)
            result = false
        }
        return result
    }

    /**
     * clears all fields
     */
    private fun clear() {
        binding.editTextEmail.text.clear()
        binding.editTextUsername.text.clear()
        binding.editTextFirstName.text.clear()
        binding.editTextLastName.text.clear()
        binding.editTextAge.text.clear()
    }
}

