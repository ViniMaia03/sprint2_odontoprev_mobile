package com.example.sprint2_odontoprev

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint2_odontoprev.databinding.ActivityAtualizacaoCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AtualizacaoCadastro : AppCompatActivity() {

    private val binding by lazy {
        ActivityAtualizacaoCadastroBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_atualizacao_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAtualizar.setOnClickListener{
            atualizarDados()
        }
    }

    private fun atualizarDados() {
        val dados = mapOf(
            "endereco" to binding.btnEndereco.text.toString(),
            "telefone" to binding.editTelefone.text.toString(),
            "plano" to binding.btnPlano.text.toString()
        )

        val idUsuarioAtual = autenticacao.currentUser?.uid

        if(idUsuarioAtual != null) {
            bancoDados
                .collection("usuarios")
                .document(idUsuarioAtual)
                .set(dados)
                .addOnSuccessListener {
                    androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Sucesso ao atualizar")
                        .setMessage("Registro com salvo com sucesso.")
                        .setPositiveButton("Fechar") { dialog, posicao -> }
                        .create().show()
                }
                .addOnFailureListener {
                    androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Erro ao atualizar")
                        .setMessage("Registro não salvo.")
                        .setPositiveButton("Fechar") { dialog, posicao -> }
                        .create().show()
                }
        }
    }
}