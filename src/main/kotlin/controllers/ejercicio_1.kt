package org.example.controllers

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.InputMismatchException
import java.util.Scanner
import kotlin.math.abs

fun main() {
    val scan = Scanner(System.`in`)
    val eleccion = menuBitlletsIEleccio(scan)
    val zonaElegida = triarZona(scan)
    val precio = gestionarEleccion(eleccion, zonaElegida)
    imprimirEleccion(eleccionEnString(eleccion), zonaElegida, precio)
    pagarITornarCanvi(scan, precio)

}

fun gestionarEleccion(menuBitlletsIEleccio: Int,eleccioZona:Int): String {
    var precio = 0.0
    var eleccionEnString = " "
    when (menuBitlletsIEleccio){
        1 -> precio = 2.4
        2 -> precio = 11.35
        3 -> precio = 40.00
        4 -> precio = 10.00
        5 -> precio = 80.00
    }
    when (eleccioZona){
        1 -> precio = precio * 1
        2 -> precio = precio * 1.3125
        3 -> precio = precio * 1.8443


    }
    val formato = DecimalFormat("#.00") // Si usas otro sistema operativo usa la coma en ve del putno
    val numeroFormateado = formato.format(precio).toString()

    return numeroFormateado

}


fun menuBitlletsIEleccio(scan:Scanner):Int{
    println("""
        - - - - - - - - - - - - - - - - - - - - - - 
        Quin bitllet desitja adquirir?
        1. Btillet Senzill
        2. TCasual
        3. TUsual
        4. TFamiliar
        5. TJove
    """.trimIndent())
    try {
        val eleccio = scan.nextInt()

    }
    catch (e:InputMismatchException) {
        println("")
    }

    while ( eleccio < 0 || eleccio > 5 ) {
        println("Has de triar alguna opció valida (1-5)")
        val eleccio = scan.nextInt()


    }
    return eleccio

}

fun triarZona(scan: Scanner):Int{
    println("""
        De quina zona desitja adquirir el bitllet?
        1
        2
        3
    """.trimIndent())
    val eleccio = scan.nextInt()
    return  eleccio
}

fun eleccionEnString(menuBitlletsIEleccio: Int):String{
    var eleccionEnString = " "
    when (menuBitlletsIEleccio){
        1 -> eleccionEnString =  "Bitllet Sencill"
        2 -> eleccionEnString = "TCasual"
        3 -> eleccionEnString = "TUsual"
        4 -> eleccionEnString = "TFamiliar"
        5 -> eleccionEnString = "TJove"
    }
    return eleccionEnString
}

fun imprimirEleccion(eleccionString:String, zona:Int, precio:String){
    println("Has escollit l'opcio $eleccionString, zona $zona")
    println("El preu del bitllet es de $precio€")



}


fun pagarITornarCanvi(scan:Scanner, precio:String){
    println("""
        PAGAMENT EN EFECTIU:
        MONEDES I BITLLETS PERMESOS: 
        0.05€, 0.10€, 0.20€, 0.50€, 1€, 2€, 5€, 10€, 20€ i 50€.
        
       et queda per pagar: $precio€
    """.trimIndent())

    println("Introdueix moneda per moneda")
    var dineroMetidoPorElCliente = 0.0
    val aPagar = precio.toDouble()

    while (dineroMetidoPorElCliente < aPagar){
        println("Introdueix")
        println("Et queda per pagar ${aPagar - dineroMetidoPorElCliente}")
        var introducido = scan.nextDouble()
        if (introducido != 0.05 && introducido != 0.10 && introducido != 0.20 && introducido != 0.50 && introducido != 1.0 && introducido != 2.0 && introducido != 5.0 && introducido != 10.0 && introducido != 20.0 && introducido != 50.0) {
            println("Moneda no vàlida, introdueix una altra moneda.")
        } else {
            dineroMetidoPorElCliente += introducido
        }
    }

    if (aPagar > 0.0){
       println("Et torno el canvi ${dineroMetidoPorElCliente - aPagar}")
    }





}

