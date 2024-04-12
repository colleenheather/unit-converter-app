package com.gamecodeschool.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    internal lateinit var etLbs: EditText
    internal lateinit var etKg: EditText
    internal lateinit var etfar: EditText
    internal lateinit var etcel: EditText

    class ConversionHelper {

        fun convertCelToFar(celValue: Double): Double {
            return ((9.0/5.0)* celValue) + 32
        }

        fun convertFarToCel(farValue: Double): Double {
            return (farValue - 32) * (5.0/9.0)
        }

        fun convertKgsToLbs(kgValue: Double): Double {
            return kgValue / 0.453592 // 1 kg = 0.453592 lbs
        }

        fun convertLbsToKgs(lbsValue: Double): Double {
            return lbsValue * 0.453592 // 1 lb = 0.453592 kg
        }
    }

    private val conversionHelper = ConversionHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLbs = findViewById(R.id.et_lbs)
        etKg = findViewById(R.id.et_kg)
        etfar = findViewById(R.id.et_fahrenheit)
        etcel = findViewById(R.id.et_celsius)

        etLbs.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                convertLbsToKgs()
                true
            } else {
                false
            }
        }

        etKg.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                convertKgsToLbs()
                true
            } else {
                false
            }
        }

        etfar.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                convertFarToCel()
                true
            } else {
                false
            }
        }

        etcel.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                convertCelToFar()
                true
            } else {
                false
            }
        }


    }

    internal fun convertCelToFar() {
        val celValue = etcel.text.toString().toDoubleOrNull() ?: 0.0
        val farValue = conversionHelper.convertCelToFar(celValue)
        etfar.setText(String.format("%.2f", farValue))
    }

    internal fun convertFarToCel() {
        val farValue = etfar.text.toString().toDoubleOrNull() ?: 0.0
        //val celValue = (farValue - 32) * (5.0/9.0)
        val celValue = conversionHelper.convertFarToCel(farValue)
        etcel.setText(String.format("%.2f", celValue))
    }

    internal fun convertKgsToLbs() {
        val kgValue = etKg.text.toString().toDoubleOrNull() ?: 0.0
        //val lbsValue = kgValue / 0.453592 // 1 kg = 0.453592 lbs
        val lbsValue = conversionHelper.convertKgsToLbs(kgValue)
        etLbs.setText(String.format("%.2f", lbsValue))
    }

    internal fun convertLbsToKgs() {
        val lbsValue = etLbs.text.toString().toDoubleOrNull() ?: 0.0
        //val kgValue = lbsValue * 0.453592 // 1 lb = 0.453592 kg
        val kgValue = conversionHelper.convertLbsToKgs(lbsValue)
        etKg.setText(String.format("%.2f", kgValue))
    }

}