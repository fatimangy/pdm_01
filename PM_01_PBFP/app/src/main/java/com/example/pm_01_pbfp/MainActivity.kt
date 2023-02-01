package com.example.pm_01_pbfp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var operacionEnMemoria: Operacion = Operacion();
    var operacionActual: Operacion = Operacion();
    var numeroActual: String = "0";

    /* Botones para los numeros */
    lateinit var boton0: Button
    lateinit var boton1: Button
    lateinit var boton2: Button
    lateinit var boton3: Button
    lateinit var boton4: Button
    lateinit var boton5: Button
    lateinit var boton6: Button
    lateinit var boton7: Button
    lateinit var boton8: Button
    lateinit var boton9: Button

    /* Botones para los signos */
    lateinit var botonSumar: Button
    lateinit var botonRestar: Button
    lateinit var botonMultiplicar: Button
    lateinit var botonDividir: Button
    lateinit var botonIgual: Button
    lateinit var botonDeshacer: Button
    lateinit var botonInicio: Button
    lateinit var botonDecimal: Button
    lateinit var botonMemorizar: Button
    lateinit var botonRecordar: Button

    /* TextView para el resultado e historial */
    lateinit var historial: TextView
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListeners()
    }

    private fun initView(){
        /* Iniciamos los botones numericos */
        boton0 = findViewById(R.id.boton0);
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);
        boton5 = findViewById(R.id.boton5);
        boton6 = findViewById(R.id.boton6);
        boton7 = findViewById(R.id.boton7);
        boton8 = findViewById(R.id.boton8);
        boton9 = findViewById(R.id.boton9);

        /* Iniciamos los botones de los signos */
        botonSumar = findViewById(R.id.botonSumar);
        botonRestar = findViewById(R.id.botonRestar);
        botonMultiplicar = findViewById(R.id.botonMultiplicar);
        botonDividir = findViewById(R.id.botonDividir);
        botonIgual = findViewById(R.id.botonIgual);
        botonDeshacer = findViewById(R.id.botonDeshacer);
        botonInicio = findViewById(R.id.botonInicio);
        botonDecimal = findViewById(R.id.botonDecimal);
        botonMemorizar = findViewById(R.id.botonMemorizar);
        botonRecordar = findViewById(R.id.botonRecordar);

        /* Iniciamos los textview */
        historial = findViewById(R.id.historial);
        resultado = findViewById(R.id.resultado);
    }

    private fun listenerNumerico(boton:Button, numero:Int){
        boton.setOnClickListener {
            if(numeroActual == "0" || numeroActual == "M"){
                numeroActual = numero.toString();
            }
            else{
                numeroActual += numero;
            }
            resultado.text = numeroActual;
        }
    }

    private fun listenerSigno(boton:Button, signo:String){
        boton.setOnClickListener {
            /* En el caso de que no haya ningun digito en n1 */
            if(operacionActual.getN1() == null){
                operacionActual.setN1("0".toFloat());
            }
            /* Caso de que una operacion este completa */
            if(operacionActual.getN2() != null){
                operacionActual.comprimir();
            }
            /* Cambiamos el signo */
            operacionActual.setSigno(signo);
            operacionActual.setN2(null);
            historial.text = operacionActual.estado();

            /* Reiniciamos el numero actual */
            numeroActual = "";
            resultado.text = numeroActual;
        }
    }

    private fun initListeners(){
        /* Le añadimos funcionalidad a los botones numericos */
        listenerNumerico(boton0, 0);
        listenerNumerico(boton1, 1);
        listenerNumerico(boton2, 2);
        listenerNumerico(boton3, 3);
        listenerNumerico(boton4, 4);
        listenerNumerico(boton5, 5);
        listenerNumerico(boton6, 6);
        listenerNumerico(boton7, 7);
        listenerNumerico(boton8, 8);
        listenerNumerico(boton9, 9);

        /* Le añadimos funcionalidad a los botones de los signos */
        listenerSigno(botonSumar, "+");
        listenerSigno(botonRestar, "-");
        listenerSigno(botonMultiplicar, "*");
        listenerSigno(botonDividir, "/");

        /* Le añadimos funcionalidad a los botones de memoria */
        botonMemorizar.setOnClickListener {
            if(resultado.text != "M"){
                operacionEnMemoria = operacionActual;
                historial.text = "";
                numeroActual = "M"
                resultado.text = "M";
            }
        }

        botonRecordar.setOnClickListener {
            operacionActual = operacionEnMemoria;
            historial.text = operacionActual.estado();
            if(operacionActual.getN2() != null){
                resultado.text = operacionActual.resultado().toString();
            }
        }

        /* Le añadimos funcionalidad al boton decimal */
        botonDecimal.setOnClickListener {
            numeroActual = numeroActual.replace(".", "");
            numeroActual += ".";
            resultado.text = numeroActual;
        }

        /* Le añadimos funcionalidad al boton inicio */
        botonInicio.setOnClickListener {
            operacionActual = Operacion()
            numeroActual += "";
            resultado.text = "";
            historial.text = "";
        }

        /* Le añadimos funcionalidad al boton deshacer */
        botonDeshacer.setOnClickListener {
            if(numeroActual == ""){
                operacionActual.remove();
            }
            else{
                numeroActual == "";
            }
            resultado.text = numeroActual
            historial.text = operacionActual.estado()
        }

        /* Le añadimos funcionalidad al boton igual */
        botonIgual.setOnClickListener {
            /* Comprobamos que se pueda completar la cuenta */
            if(operacionActual.getSigno() != null && numeroActual != "" && numeroActual != "M"){

                /* Quitamos los decimales si no tienen valores */
                if(numeroActual.indexOf(".") == numeroActual.length - 1){
                    numeroActual.substring(0, numeroActual.indexOf("."));
                }

                /* Comprobamos que no sea una division por 0 */
                if(numeroActual == "0" && operacionActual.getSigno() == "/"){
                    resultado.text = "DIVISION POR 0"
                    operacionActual.setN2(null)
                }
                else{
                    operacionActual.setN2(numeroActual.toFloat());
                    numeroActual = operacionActual.resultado().toString()
                    resultado.text = numeroActual;
                }
            }
        }
    }
}