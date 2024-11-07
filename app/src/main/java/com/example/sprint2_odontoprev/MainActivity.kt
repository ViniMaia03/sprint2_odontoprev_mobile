package com.example.sprint2_odontoprev

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint2_odontoprev.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuarioLogado()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCadastrar.setOnClickListener {
            startActivity(Intent(this, Cadastro::class.java))
        }

        binding.btnLogar.setOnClickListener{
            logarUsuario()
        }
    }

    private fun verificarUsuarioLogado() {
        val usuario = autenticacao.currentUser

        if(usuario != null) {
            startActivity(
                Intent(this, Logado::class.java)
            )
        }
    }

    private fun logarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        autenticacao.signInWithEmailAndPassword(email, senha).addOnSuccessListener {
            authResult -> startActivity(Intent(this, Logado::class.java))
        }
            .addOnFailureListener{
                exeption -> AlertDialog.Builder(this)
                .setTitle("Erro ao logar")
                .setMessage("Verifique e-mail e senha digitados")
                .setPositiveButton("Fechar") {dialog, posicao ->}
                .create().show()
            }
    }
}