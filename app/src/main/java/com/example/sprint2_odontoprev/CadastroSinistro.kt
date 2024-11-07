package com.example.sprint2_odontoprev

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint2_odontoprev.databinding.ActivityCadastroSinistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroSinistro : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroSinistroBinding.inflate(layoutInflater)
    }

    private  val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_cadastro_sinistro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCadastrarSinistro.setOnClickListener{
            salvarSinistro()
            startActivity(Intent(this, Logado::class.java))
        }
    }

    private fun salvarSinistro(){
        val dadosSinistro = mapOf(
            "descricaoSinistro" to binding.editDescSinistro.text.toString(),
            "dataHora" to binding.editDataSinistro.text.toString()
        )

        val idSinistro = autenticacao.currentUser?.uid

        if (idSinistro != null) {
            bancoDados
                .collection("usuarios")
                .document(idSinistro)
                .set(dadosSinistro)
        }
    }
}