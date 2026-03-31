package com.example.flagquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var tvContador: TextView
    private lateinit var ivBandeira: ImageView
    private lateinit var etResposta: EditText
    private lateinit var tvFeedback: TextView
    private lateinit var btnConfirmar: Button

    private var nomeJogador = ""
    private var pontuacao = 0
    private var perguntaAtual = 0
    private var respondido = false

    private lateinit var bandeirasDaRodada: List<Pair<Int, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        nomeJogador = intent.getStringExtra("NOME_JOGADOR") ?: "Jogador"

        tvContador = findViewById(R.id.tvContador)
        ivBandeira = findViewById(R.id.ivBandeira)
        etResposta = findViewById(R.id.etResposta)
        tvFeedback = findViewById(R.id.tvFeedback)
        btnConfirmar = findViewById(R.id.btnConfirmar)

        val todasAsBandeiras = listOf(
            Pair(R.drawable.ad, "Andorra"),
            Pair(R.drawable.af, "Afeganistão"),
            Pair(R.drawable.am, "Armênia"),
            Pair(R.drawable.ao, "Angola"),
            Pair(R.drawable.ar, "Argentina"),
            Pair(R.drawable.arab, "Liga Árabe"),
            Pair(R.drawable.at, "Áustria"),
            Pair(R.drawable.au, "Austrália"),
            Pair(R.drawable.ba, "Bósnia"),
            Pair(R.drawable.bg, "Bulgária"),
            Pair(R.drawable.br, "Brasil"),
            Pair(R.drawable.cd, "Congo"),
            Pair(R.drawable.cx, "Ilhas Christmas"),
            Pair(R.drawable.dm, "Dominica"),
            Pair(R.drawable.gb_eng, "Inglaterra")
        )

        bandeirasDaRodada = todasAsBandeiras.shuffled().take(5)

        carregarPergunta()

        btnConfirmar.setOnClickListener {
            if (!respondido) {
                verificarResposta()
            } else {
                proximaPergunta()
            }
        }
    }

    private fun carregarPergunta() {
        respondido = false
        btnConfirmar.text = "Confirmar"
        tvFeedback.text = ""
        etResposta.text.clear()
        etResposta.isEnabled = true

        tvContador.text = "${perguntaAtual + 1} de 5"
        ivBandeira.setImageResource(bandeirasDaRodada[perguntaAtual].first)
    }

    private fun verificarResposta() {
        val respostaDigitada = etResposta.text.toString().trim()
        if (respostaDigitada.isEmpty()) return

        val respostaCerta = bandeirasDaRodada[perguntaAtual].second

        if (respostaDigitada.equals(respostaCerta, ignoreCase = true)) {
            tvFeedback.text = "Correto!"
            tvFeedback.setTextColor(Color.GREEN)
            pontuacao += 20
        } else {
            tvFeedback.text = "Incorreto! Era $respostaCerta"
            tvFeedback.setTextColor(Color.RED)
        }

        respondido = true
        etResposta.isEnabled = false
        btnConfirmar.text = "Próxima"
    }

    private fun proximaPergunta() {
        perguntaAtual++
        if (perguntaAtual < 5) {
            carregarPergunta()
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("NOME_JOGADOR", nomeJogador)
            intent.putExtra("PONTUACAO", pontuacao)
            startActivity(intent)
            finish()
        }
    }
}