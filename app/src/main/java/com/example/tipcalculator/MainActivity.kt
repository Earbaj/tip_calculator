package com.example.tipcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip() {
        val stringInTextField = binding.etTripCost.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.rdGroup.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.rdb_spcial -> 0.20
            R.id.rdb_medium -> 0.15
            else -> 0.10
        }
        var tip = tipPercentage * cost
        val roundUp = binding.swRoundtrip.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtFinalAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}