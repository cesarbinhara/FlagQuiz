package com.example.flagquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvNomeFinal = findViewById<TextView>(R.id.tvNomeFinal)
        val tvPontuacao = findViewById<TextView>(R.id.tvPontuacao)
        val btnRecomecar = findViewById<Button>(R.id.btnRecomecar)


        val nome = intent.getStringExtra("NOME_JOGADOR") ?: "Jogador"
        val pontuacao = intent.getIntExtra("PONTUACAO", 0)


        tvNomeFinal.text = "Parabéns, $nome!"
        tvPontuacao.text = "Sua pontuação: $pontuacao / 100"


        btnRecomecar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}