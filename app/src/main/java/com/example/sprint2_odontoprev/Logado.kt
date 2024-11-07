package com.example.sprint2_odontoprev

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint2_odontoprev.databinding.ActivityLogadoBinding

class Logado : AppCompatActivity() {

    private val binding by lazy {
        ActivityLogadoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_logado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSinistro.setOnClickListener{
            cadastratSinistro()
        }

        binding.btnAtualizarCadastro.setOnClickListener{
            startActivity(Intent(this, AtualizacaoCadastro::class.java))
        }
    }

    private fun cadastratSinistro() {
        startActivity(Intent(this, CadastroSinistro::class.java))
    }
}