package com.example.miappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var txtMensaje: TextView
    private lateinit var btnProcesar: Button
    private lateinit var swCon: Switch
    private lateinit var etMensaje: EditText
    private lateinit var etNombre: EditText
    private var mensaje: String? = null
    //Comando de voz
    //tts = Text To Speech
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarViews()
        //Inicializar comando de voz
        //this en posición 1 = contexto de pantalla.
        //this en posición 2 = si está ene esta clase implementa la regla del juego.
        tts = TextToSpeech(this, this)
        mensaje = "Don't worry, be Happy!"
        mensaje = "La fe es lo más lindo del mundo"
        //mensaje = etMensaje.text.toString()
        //Las llaves representan una función de orden superior
        //Función anónima de una interfaz que implementa una regla de juego llamada OnClick
        btnProcesar.setOnClickListener { speakMessage() }
    }

    private fun inicializarViews() {
        txtMensaje = findViewById(R.id.txtMensaje)
        btnProcesar = findViewById(R.id.btnProcesar)
        //Si no se quiere usar la vista en una variable:
        //findViewById<TextView>(R.id.txtMensaje).text
        etMensaje = findViewById(R.id.etMensaje)
        etNombre = findViewById(R.id.etNombre)
        swCon = findViewById(R.id.swCon)
    }

    private fun speakMessage() {
        txtMensaje.text = mensaje
        tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
        if (swCon.isChecked){
            mensaje = etMensaje.text.toString() + etNombre.text.toString()
        } else {
            mensaje = etMensaje.text.toString()
        }
        tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(/*p0*/status: Int) {
        //Por defecto el comando de voz está en English
        var respuesta = if (status == TextToSpeech.SUCCESS){
            //tts.language = Locale.US
            tts.language = Locale("ES")
            "Todo ha salido bien"
        } else "Algo salió mal, por favor intente más tarde"
        Toast.makeText(this,respuesta,Toast.LENGTH_SHORT).show()
    }
}