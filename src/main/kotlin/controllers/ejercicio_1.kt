package org.example.controllers

import java.text.DecimalFormat
import java.util.Scanner

fun main() {
    repeticion()

}

fun repeticion(){

    val scan = Scanner(System.`in`)
    val eleccion = eleccioTipusBitllet(scan)
    val zonaElegida = triarZona(scan)
    val precio = gestionarEleccion(eleccion, zonaElegida)
    imprimirEleccion(eleccionEnString(eleccion), zonaElegida, precio)
    pagarITornarCanvi(scan, precio)
    tiquet(scan, eleccionEnString(eleccion), zonaElegida, precio)

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
fun eleccioTipusBitllet(scan:Scanner):Int{
   imprimirMenuEleccioTipusBitllet()
    var eleccio = 0
    while (eleccio == 0) {
        try {
            eleccio = scan.nextInt()
            while (eleccio < 1 || eleccio > 5) {
                println("Has de triar alguna opció vàlida (1-5)")
                eleccio = scan.nextInt()
            }
        } catch (e: Exception) {
            println("Has d'introduir un número del 1-5")
            eleccio = 0
            scan.next()
        }
    }
    return eleccio
}
fun triarZona(scan: Scanner):Int{
    imprimirMenuEleccioZona()
    var eleccio = 0
    while (eleccio == 0){
        try {
            eleccio = scan.nextInt()
            while (eleccio < 1 || eleccio > 3) {
                println("Has de triar alguna opció vàlida (1-3)")
                eleccio = scan.nextInt()
            }
        } catch (e: Exception) {
            println("Has d'introduir un número del 1-3")
            eleccio = 0
            scan.next()
        }
    }
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
fun imprimirEleccion(eleccionString:String, zona:Int, precio:String):String{
    println("Has escollit l'opcio $eleccionString, zona $zona")
    println("El preu del bitllet es de $precio€")
    return eleccionString
}
fun pagarITornarCanvi(scan:Scanner, precio:String){
    val formato = DecimalFormat("#.00")
    println("Introdueix moneda per moneda")
    var dineroMetidoPorElCliente = 0.0
    var aPagar = 0.0
    imprimirmonedesPermeses()
    while (aPagar == 0.0)
    try { aPagar = precio.toDouble() }
    catch (e:Exception){
        println("Has d'introduir monedes!, (numeros)")
        aPagar = 0.0
        scan.next()
    }
    while (dineroMetidoPorElCliente < aPagar) {
        var dineroFormateado = formato.format(aPagar - dineroMetidoPorElCliente)
        println("Et queda per pagar $dineroFormateado€")
        var introducido = 0.0
        while (introducido == 0.0) {
            try { introducido = scan.nextDouble() }
            catch (e: Exception) {
                println("Has d'introduir monedes!, (numeros)")
                introducido = 0.0
                scan.next()}}
        if (introducido != 0.05 && introducido != 0.10 && introducido != 0.20 && introducido != 0.50 && introducido != 1.0 && introducido != 2.0 && introducido != 5.0 && introducido != 10.0 && introducido != 20.0 && introducido != 50.0) {
            println("Moneda no vàlida, introdueix una altra moneda.")
        } else { dineroMetidoPorElCliente += introducido }}
    if (aPagar > 0.0) {
        var dineroFormateado1 = formato.format(dineroMetidoPorElCliente - aPagar)
        println("Et torno el canvi $dineroFormateado1€")
        animacioTornarCanvi()
    }
}
fun imprimirMenuEleccioTipusBitllet(){
    println("""
        - - - - - - - - - - - - - - - - - - - - - - 
        Quin bitllet desitja adquirir?
        1. Btillet Senzill
        2. TCasual
        3. TUsual
        4. TFamiliar
        5. TJove
    """.trimIndent())
}
fun imprimirMenuEleccioZona(){
    println("""
        De quina zona desitja adquirir el bitllet?
        1
        2
        3
    """.trimIndent())
}
fun imprimirmonedesPermeses(){
    println("""
        PAGAMENT EN EFECTIU:
        MONEDES I BITLLETS PERMESOS: 
        0.05€, 0.10€, 0.20€, 0.50€, 1€, 2€, 5€, 10€, 20€ i 50€.
    """.trimIndent())
}
fun animacioTornarCanvi(){
    Thread.sleep(500)
    println("Cling")
    Thread.sleep(500)
    println("Clong")
    Thread.sleep(500)
    println("Cling")
    Thread.sleep(500)
    println("Ja pots recollir els diners")
}

fun tiquet(scan: Scanner, eleccionString: String, zonaTriada:Int, precio: String){
    println("Vol tiquet? S/N ")
    val eleccio = scan.next().lowercase()
    if (eleccio == "s"){
        println("""
            - - - - - - - - - - - - - - - - - -
                          TICKET
            - - - - - - - - - - - - - - - - - -            
              BITLLET: $eleccionString
              ZONA/ES: $zonaTriada
              PREU: $precio
              METODE DE PAGAMENT: EFECTIU
             
             **********************************
             *** GRÀCIES PER LA SEVA COMPRA ***
             **********************************
             
            - - - - - - - - - - - - - - - - - -  
                           TMB
            - - - - - - - - - - - - - - - - - -                 
        """.trimIndent())}
}


