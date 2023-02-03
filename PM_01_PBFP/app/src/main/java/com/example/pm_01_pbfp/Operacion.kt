package com.example.pm_01_pbfp

class Operacion() {
    //Ponemos vacias todas las variables
    private var n1: Float? = null;
    private var signo: String? = null;
    private var n2: Float? = null;

    fun setN1(N1:Float?){
        n1 = N1;
    }

    fun setSigno(Signo:String?){
        signo = Signo;
    }

    fun setN2(N2:Float?){
        n2 = N2;
    }

    fun getN1():Float?{
        return n1;
    }

    fun getSigno():String?{
        return signo;
    }

    fun getN2():Float?{
        return n2;
    }

    fun add(valor:String){
        if(n1 == null){
            n1 = valor.toFloat();
        }
        if(signo == null){
            signo = valor;
        }
        if(n2 == null){
            n2 = valor.toFloat();
        }
    }

    fun remove(){
        if(n2 != null){
            n2 = null;
            return;
        }
        if(signo != null){
            signo = null;
            return;
        }
        n1 = null;
    }

    fun comprimir(){
        n1 = resultado();
        signo = null;
        n2 = null;
    }

    fun resultado():Float{
        when(signo){
            "+" -> {return n1!! + n2!!}
            "-" -> {return n1!! - n2!!}
            "*" -> {return n1!! * n2!!}
            "/" -> {return n1!! / n2!!}
        }
        return "0.15".toFloat();
    }

    fun estado(): String{
        //Preparamos el string para el formato
        var estado: String = "";

        //Vemos por que paso vamos
        if(n1 != null){
            estado += n1.toString();
        }
        if(signo != null){
            estado += signo;
        }
        if(n2 != null){
            estado += n2.toString();
        }

        return estado;
    }

}