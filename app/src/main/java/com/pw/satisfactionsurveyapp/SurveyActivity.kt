package com.pw.satisfactionsurveyapp

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEnviar = findViewById<Button>(R.id.brnEnviar)

        val radioP1 = findViewById<RadioGroup>(R.id.radioP1)
        val radioP2 = findViewById<RadioGroup>(R.id.radioP2)
        val radioP3 = findViewById<RadioGroup>(R.id.radioP3)
        val radioP4 = findViewById<RadioGroup>(R.id.radioP4)
        val radioP5 = findViewById<RadioGroup>(R.id.radioP5)

        btnEnviar.setOnClickListener {
            if (radioP1.checkedRadioButtonId == -1
                || radioP2.checkedRadioButtonId == -1
                || radioP3.checkedRadioButtonId == -1
                || radioP4.checkedRadioButtonId == -1
                || radioP5.checkedRadioButtonId == -1)
                Toast.makeText(this, "Todos los campos deben de ser llenados", Toast.LENGTH_SHORT).show()
            else{
                when (radioP1.checkedRadioButtonId){
                    R.id.radioP1R1 -> {}
                    R.id.radioP1R2 -> {}
                    R.id.radioP1R3 -> {}
                    R.id.radioP1R4 -> {}
                }
                when (radioP2.checkedRadioButtonId){
                    R.id.radioP2R1 -> {}
                    R.id.radioP2R2 -> {}
                    R.id.radioP2R3 -> {}
                    R.id.radioP2R4 -> {}
                }
                when (radioP3.checkedRadioButtonId){
                    R.id.radioP3R1 -> {}
                    R.id.radioP3R2 -> {}
                    R.id.radioP3R3 -> {}
                    R.id.radioP3R4 -> {}
                }
                when (radioP4.checkedRadioButtonId){
                    R.id.radioP4R1 -> {}
                    R.id.radioP4R2 -> {}
                    R.id.radioP4R3 -> {}
                    R.id.radioP4R4 -> {}
                }
                when (radioP5.checkedRadioButtonId){
                    R.id.radioP5R1 -> {}
                    R.id.radioP5R2 -> {}
                    R.id.radioP5R3 -> {}
                }
            }
        }
    }
}