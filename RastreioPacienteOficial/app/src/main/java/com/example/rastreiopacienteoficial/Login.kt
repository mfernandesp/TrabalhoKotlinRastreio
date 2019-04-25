package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Usuario
import kotlinx.android.synthetic.main.activity_cadastrar_usuario.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.*

class Login : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Login"

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        entrar.setOnClickListener {
            login()
        }

        cadastraruser.setOnClickListener {
            cadastro()
        }
    }

    fun login() {

        try {
            val login = username.text.toString()
            val senha = password.text.toString()

            var usr = banco?.usuarioCrud()?.verifyUruario(username.text.toString(), password.text.toString())
                if(usr?.login.equals(login) && usr?.senha.equals(senha)) {

                    val sharedPreferences: SharedPreferences =
                        getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isLogged", true)
                    editor.putString("username", usr?.nome)
                    editor.putString("password", usr?.senha)
                    editor.putString("name", usr?.nome)
                    editor.putString("paciente_nome","" )
                    editor.putString("paciente_id","" )
                    editor.apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "Login ou senha incorreta.", Toast.LENGTH_LONG).show()
                }
        } catch (e: JSONException) {
                Toast.makeText(this, "Erro ao logar.", Toast.LENGTH_LONG).show()
        }

    }

    fun cadastro(){

        startActivity(Intent(this, CadastrarUsuario::class.java))
        finish()

    }

}
