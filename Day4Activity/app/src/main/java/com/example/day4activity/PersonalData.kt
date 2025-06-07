package com.example.day4activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.day4activity.databinding.ActivityPersonalDataBinding
import java.util.*

class PersonalData : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }

        binding.dobCard.setOnClickListener {
            showDatePicker()
        }

        binding.incomeCard.setOnClickListener {
            showIncomeOptions()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val months = arrayOf(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                )
                val selectedDate = "$selectedDay ${months[selectedMonth]} $selectedYear"
                binding.dobText.text = selectedDate
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    private fun showIncomeOptions() {
        val incomeRanges = arrayOf(
            "$500 - $1000 / month",
            "$1000 - $2000 / month",
            "$2000 - $3000 / month",
            "$3000 - $5000 / month",
            "$5000 - $10000 / month",
            "$10000+ / month"
        )

        val popupMenu = PopupMenu(this, binding.incomeCard)

        incomeRanges.forEachIndexed { index, range ->
            popupMenu.menu.add(0, index, 0, range)
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            binding.income.text = incomeRanges[menuItem.itemId]
            true
        }

        popupMenu.show()
    }
}