package com.pw.satisfactionsurveyapp

import android.content.ContentValues
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
                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val db = admin.writableDatabase
                val registro = ContentValues()
                when (radioP1.checkedRadioButtonId){
                    R.id.radioP1R1 -> registro.put("pregunta1", 1)
                    R.id.radioP1R2 -> registro.put("pregunta1", 2)
                    R.id.radioP1R3 -> registro.put("pregunta1", 3)
                    R.id.radioP1R4 -> registro.put("pregunta1", 4)
                }
                when (radioP2.checkedRadioButtonId){
                    R.id.radioP2R1 -> registro.put("pregunta2", 1)
                    R.id.radioP2R2 -> registro.put("pregunta2", 2)
                    R.id.radioP2R3 -> registro.put("pregunta2", 3)
                    R.id.radioP2R4 -> registro.put("pregunta2", 4)
                }
                when (radioP3.checkedRadioButtonId){
                    R.id.radioP3R1 -> registro.put("pregunta3", 1)
                    R.id.radioP3R2 -> registro.put("pregunta3", 2)
                    R.id.radioP3R3 -> registro.put("pregunta3", 3)
                    R.id.radioP3R4 -> registro.put("pregunta3", 4)
                }
                when (radioP4.checkedRadioButtonId){
                    R.id.radioP4R1 -> registro.put("pregunta4", 1)
                    R.id.radioP4R2 -> registro.put("pregunta4", 2)
                    R.id.radioP4R3 -> registro.put("pregunta4", 3)
                    R.id.radioP4R4 -> registro.put("pregunta4", 4)
                }
                when (radioP5.checkedRadioButtonId){
                    R.id.radioP5R1 -> registro.put("pregunta5", 1)
                    R.id.radioP5R2 -> registro.put("pregunta5", 2)
                    R.id.radioP5R3 -> registro.put("pregunta5", 3)
                }
                val resultado = db.insert("encuesta", null, registro)
                if (resultado != -1L) {
                    Toast.makeText(this, "Los resultados de la encuesta se guardaron con exito", Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this, "Error al guardar los resultados de la encuesta", Toast.LENGTH_SHORT).show()
                db.close()
                finish()
            }
        }
    }
}