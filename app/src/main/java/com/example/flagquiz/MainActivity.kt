package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNome = findViewById<EditText>(R.id.etNome)
        val btnComecar = findViewById<Button>(R.id.btnComecar)

        btnComecar.setOnClickListener {
            val nome = etNome.text.toString().trim()

            if (nome.isNotEmpty()) {

                val intent = Intent(this, QuizActivity::class.java)

                intent.putExtra("NOME_JOGADOR", nome)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show()
            }
        }
    }
}