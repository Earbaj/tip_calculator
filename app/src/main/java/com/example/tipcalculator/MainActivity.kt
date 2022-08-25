package com.example.tipcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.etTripCost.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.rdGroup.checkedRadioButtonId) {
            R.id.rdb_spcial -> 0.20
            R.id.rdb_medium -> 0.15
            else -> 0.10
        }
        var tip = tipPercentage * cost
        val roundUp = binding.swRoundtrip.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(amount: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(amount)
        binding.txtFinalAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}