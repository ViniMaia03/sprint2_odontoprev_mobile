package com.example.sprint2_odontoprev

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint2_odontoprev.databinding.ActivityCadastroBinding
import com.example.sprint2_odontoprev.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class Cadastro : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCadastro.setOnClickListener{
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {
        val nome = binding.editNomeCadastro.text.toString()
        val email = binding.editEmailCadastro.text.toString()
        val senha = binding.editSenhaCadastro.text.toString()

        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener {
                authResult -> AlertDialog.Builder(this)
                .setTitle("Sucesso")
                .setMessage("Cadastro concluído com sucesso")
                .setPositiveButton("Ok") {dialog, posicao ->
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .setCancelable(false).create().show()
            } .addOnFailureListener { exception ->
                val mensagemErro = exception.message
                AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("Erro ao cadastrar")
                    .setPositiveButton("Fechar") {dialog, posicao ->}
                    .create().show()
            }
    }
}