package com.pw.satisfactionsurveyapp

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AnalyticsActivity : AppCompatActivity() {
    lateinit var tvTotalEncuestas: TextView

    // Audio
    lateinit var tvAudioExcelente: TextView
    lateinit var tvAudioBueno: TextView
    lateinit var tvAudioRegular: TextView
    lateinit var tvAudioMalo: TextView

    // Personal
    lateinit var tvPersonalExcelente: TextView
    lateinit var tvPersonalBueno: TextView
    lateinit var tvPersonalRegular: TextView
    lateinit var tvPersonalMalo: TextView

    lateinit var tvHigieneExcelente: TextView
    lateinit var tvHigieneBueno: TextView
    lateinit var tvHigieneRegular: TextView
    lateinit var tvHigieneMalo: TextView

    lateinit var tvFrecMes: TextView
    lateinit var tvFrecSemana: TextView
    lateinit var tvFrecMasSemana: TextView
    lateinit var tvFrecNunca: TextView

    lateinit var tvSalaNormal: TextView
    lateinit var tvSala3d: TextView
    lateinit var tvSalaMacro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_analytics)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSalir: Button = findViewById(R.id.btnSalir)

        tvTotalEncuestas = findViewById(R.id.total)

        tvAudioExcelente = findViewById(R.id.tv_audio_excelente)
        tvAudioBueno = findViewById(R.id.tv_audio_bueno)
        tvAudioRegular = findViewById(R.id.tv_audio_regular)
        tvAudioMalo = findViewById(R.id.tv_audio_malo)

        // Personal
        tvPersonalExcelente = findViewById(R.id.tv_personal_excelente)
        tvPersonalBueno = findViewById(R.id.tv_personal_bueno)
        tvPersonalRegular = findViewById(R.id.tv_personal_regular)
        tvPersonalMalo = findViewById(R.id.tv_personal_malo)

        // Higiene
        tvHigieneExcelente = findViewById(R.id.tv_higiene_excelente)
        tvHigieneBueno = findViewById(R.id.tv_higiene_bueno)
        tvHigieneRegular = findViewById(R.id.tv_higiene_regular)
        tvHigieneMalo = findViewById(R.id.tv_higiene_malo)

        // Frecuencia
        tvFrecMes = findViewById(R.id.tv_frec_mes)
        tvFrecSemana = findViewById(R.id.tv_frec_semana)
        tvFrecMasSemana = findViewById(R.id.tv_frec_mas_semana)
        tvFrecNunca = findViewById(R.id.tv_frec_nunca)

        // Sala
        tvSalaNormal = findViewById(R.id.tv_sala_normal)
        tvSala3d = findViewById(R.id.tv_sala_3d)
        tvSalaMacro = findViewById(R.id.tv_sala_macro)

        btnSalir.setOnClickListener {
            finish()
        }
    }

    override fun onResume(){
        super.onResume()
        cargarYMostrarEstadisticas()
    }

    private fun cargarYMostrarEstadisticas() {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val db = admin.readableDatabase

        val query = "SELECT pregunta1, pregunta2, pregunta3, pregunta4, pregunta5 FROM encuesta"
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al leer la base de datos", Toast.LENGTH_SHORT).show()
            db.close()
            return
        }

        val totalRespuestas = cursor.count
        if (totalRespuestas == 0) {
            Toast.makeText(this, "Aún no hay encuestas para mostrar", Toast.LENGTH_LONG).show()
            cursor.close()
            db.close()
            return
        }
        tvTotalEncuestas.text = "Encuestas totales: ${totalRespuestas}"

        // Pregunta 1 (Frecuencia)
        var p1_r1_count = 0 // 1 vez al mes
        var p1_r2_count = 0 // 1 vez cada semana
        var p1_r3_count = 0 // Mas de una vez a la semana
        var p1_r4_count = 0 // Casi nunca

        // Pregunta 2 (Audio)
        var p2_r1_count = 0 // Malo
        var p2_r2_count = 0 // Regular
        var p2_r3_count = 0 // Bueno
        var p2_r4_count = 0 // Excelente

        // Pregunta 3 (Personal)
        var p3_r1_count = 0 // Malo
        var p3_r2_count = 0 // Regular
        var p3_r3_count = 0 // Bueno
        var p3_r4_count = 0 // Excelente

        // Pregunta 4 (Higiene)
        var p4_r1_count = 0 // Malo
        var p4_r2_count = 0 // Regular
        var p4_r3_count = 0 // Bueno
        var p4_r4_count = 0 // Excelente

        // Pregunta 5 (Sala)
        var p5_r1_count = 0 // 3D
        var p5_r2_count = 0 // Sala normal
        var p5_r3_count = 0 // Macro XE

        if (cursor.moveToFirst()) {
            do {
                val p1Value = cursor.getInt(0) // Columna "pregunta1"
                val p2Value = cursor.getInt(1) // Columna "pregunta2"
                val p3Value = cursor.getInt(2) // Columna "pregunta3"
                val p4Value = cursor.getInt(3) // Columna "pregunta4"
                val p5Value = cursor.getInt(4) // Columna "pregunta5"

                when (p1Value) {
                    1 -> p1_r1_count++
                    2 -> p1_r2_count++
                    3 -> p1_r3_count++
                    4 -> p1_r4_count++
                }

                when (p2Value) {
                    1 -> p2_r1_count++
                    2 -> p2_r2_count++
                    3 -> p2_r3_count++
                    4 -> p2_r4_count++
                }

                when (p3Value) {
                    1 -> p3_r1_count++
                    2 -> p3_r2_count++
                    3 -> p3_r3_count++
                    4 -> p3_r4_count++
                }

                when (p4Value) {
                    1 -> p4_r1_count++
                    2 -> p4_r2_count++
                    3 -> p4_r3_count++
                    4 -> p4_r4_count++
                }

                when (p5Value) {
                    1 -> p5_r1_count++
                    2 -> p5_r2_count++
                    3 -> p5_r3_count++
                }

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        val totalDouble = totalRespuestas.toDouble()

        // --- Pregunta 1: Frecuencia ---
        val p1Pct1 = (p1_r1_count / totalDouble) * 100
        val p1Pct2 = (p1_r2_count / totalDouble) * 100
        val p1Pct3 = (p1_r3_count / totalDouble) * 100
        val p1Pct4 = (p1_r4_count / totalDouble) * 100

        // --- Pregunta 2: Audio ---
        val p2Pct1 = (p2_r1_count / totalDouble) * 100 // Malo
        val p2Pct2 = (p2_r2_count / totalDouble) * 100 // Regular
        val p2Pct3 = (p2_r3_count / totalDouble) * 100 // Bueno
        val p2Pct4 = (p2_r4_count / totalDouble) * 100 // Excelente

        // --- Pregunta 3: Personal ---
        val p3Pct1 = (p3_r1_count / totalDouble) * 100 // Malo
        val p3Pct2 = (p3_r2_count / totalDouble) * 100 // Regular
        val p3Pct3 = (p3_r3_count / totalDouble) * 100 // Bueno
        val p3Pct4 = (p3_r4_count / totalDouble) * 100 // Excelente

        // --- Pregunta 4: Higiene ---
        val p4Pct1 = (p4_r1_count / totalDouble) * 100 // Malo
        val p4Pct2 = (p4_r2_count / totalDouble) * 100 // Regular
        val p4Pct3 = (p4_r3_count / totalDouble) * 100 // Bueno
        val p4Pct4 = (p4_r4_count / totalDouble) * 100 // Excelente

        // --- Pregunta 5: Sala ---
        val p5Pct1 = (p5_r1_count / totalDouble) * 100 // 3D
        val p5Pct2 = (p5_r2_count / totalDouble) * 100 // Normal
        val p5Pct3 = (p5_r3_count / totalDouble) * 100 // Macro

        tvFrecMes.text = String.format("%.2f%%", p1Pct1)
        tvFrecSemana.text = String.format("%.2f%%", p1Pct2)
        tvFrecMasSemana.text = String.format("%.2f%%", p1Pct3)
        tvFrecNunca.text = String.format("%.2f%%", p1Pct4)

        tvAudioExcelente.text = String.format("%.2f%%", p2Pct1)
        tvAudioBueno.text = String.format("%.2f%%", p2Pct2)
        tvAudioRegular.text = String.format("%.2f%%", p2Pct3)
        tvAudioMalo.text = String.format("%.2f%%", p2Pct4)

        tvPersonalExcelente.text = String.format("%.2f%%", p3Pct1)
        tvPersonalBueno.text = String.format("%.2f%%", p3Pct2)
        tvPersonalRegular.text = String.format("%.2f%%", p3Pct3)
        tvPersonalMalo.text = String.format("%.2f%%", p3Pct4)

        tvHigieneExcelente.text = String.format("%.2f%%", p4Pct1)
        tvHigieneBueno.text = String.format("%.2f%%", p4Pct2)
        tvHigieneRegular.text = String.format("%.2f%%", p4Pct3)
        tvHigieneMalo.text = String.format("%.2f%%", p4Pct4)

        tvSalaNormal.text = String.format("%.2f%%", p5Pct2)
        tvSala3d.text = String.format("%.2f%%", p5Pct1)
        tvSalaMacro.text = String.format("%.2f%%", p5Pct3)

    }
}