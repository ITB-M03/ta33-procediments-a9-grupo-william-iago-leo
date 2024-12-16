
package org.example.controllers
import java.text.DecimalFormat
import java.util.Scanner
/**
 * Funció principal del programa. Inicia el procés de repetició.
 */
fun main() {
    repeticion()
}
/**
 * Gestiona el flux principal de la màquina de bitllets.
 * Permet seleccionar el tipus de bitllet i zona, calcula el preu,
 * gestiona el pagament i ofereix l'opció d'imprimir un tiquet.
 */
fun repeticion() {
    var pararse = false
    while (!pararse) {
        val scan = Scanner(System.`in`)
        val eleccion = eleccioTipusBitllet(scan)
        if (eleccion == 4321) {
            pararse = true
            println("MAQUINA ATURADA . . .")
        } else {
            val zonaElegida = triarZona(scan)
            val precio = gestionarEleccion(eleccion, zonaElegida)
            imprimirEleccion(eleccionEnString(eleccion), zonaElegida, precio)
            pagarITornarCanvi(scan, precio)
            tiquet(scan, eleccionEnString(eleccion), zonaElegida, precio)
        }
    }
}
/**
 * Calcula el preu final segons el tipus de bitllet i la zona triada.
 * @param menuBitlletsIEleccio El tipus de bitllet seleccionat.
 * @param eleccioZona La zona seleccionada.
 * @return El preu formatat com a string.
 */
fun gestionarEleccion(menuBitlletsIEleccio: Int, eleccioZona: Int): String {
    var precio = 0.0
    when (menuBitlletsIEleccio) {
        1 -> precio = 2.4
        2 -> precio = 11.35
        3 -> precio = 40.00
        4 -> precio = 10.00
        5 -> precio = 80.00
    }
    when (eleccioZona) {
        1 -> precio *= 1
        2 -> precio *= 1.3125
        3 -> precio *= 1.8443
    }
    val formato = DecimalFormat("#.00")
    return formato.format(precio).toString()
}
/**
 * Mostra el menú de tipus de bitllet i retorna l'opció triada per l'usuari.
 * @param scan Scanner per a la lectura de dades.
 * @return L'opció triada per l'usuari.
 */
fun eleccioTipusBitllet(scan: Scanner): Int {
    imprimirMenuEleccioTipusBitllet()
    var eleccio = 0
    while (eleccio == 0) {
        try {
            eleccio = scan.nextInt()
            while (eleccio !in 1..5 && eleccio != 4321) {
                println("Has de triar alguna opció vàlida (1-5)")
                eleccio = scan.nextInt()
            }
        } catch (e: Exception) {
            println("Has d'introduir un número del 1-5")
            scan.next()
        }
    }
    return eleccio
}
/**
 * Mostra el menú de zones i retorna l'opció triada per l'usuari.
 * @param scan Scanner per a la lectura de dades.
 * @return L'opció triada per l'usuari.
 */
fun triarZona(scan: Scanner): Int {
    imprimirMenuEleccioZona()
    var eleccio = 0
    while (eleccio == 0) {
        try {
            eleccio = scan.nextInt()
            while (eleccio !in 1..3) {
                println("Has de triar alguna opció vàlida (1-3)")
                eleccio = scan.nextInt()
            }
        } catch (e: Exception) {
            println("Has d'introduir un número del 1-3")
            scan.next()
        }
    }
    return eleccio
}
/**
 * Converteix el tipus de bitllet triat en una descripció textual.
 * @param menuBitlletsIEleccio El tipus de bitllet seleccionat.
 * @return Una cadena amb la descripció del bitllet.
 */
fun eleccionEnString(menuBitlletsIEleccio: Int): String {
    return when (menuBitlletsIEleccio) {
        1 -> "Bitllet Senzill"
        2 -> "TCasual"
        3 -> "TUsual"
        4 -> "TFamiliar"
        5 -> "TJove"
        else -> " "
    }
}
/**
 * Mostra la informació de l'elecció del bitllet i el seu preu.
 * @param eleccionString Descripció del bitllet triat.
 * @param zona Zona seleccionada.
 * @param precio Preu calculat.
 * @return La descripció del bitllet.
 */
fun imprimirEleccion(eleccionString: String, zona: Int, precio: String): String {
    println("Has escollit l'opció $eleccionString, zona $zona")
    println("El preu del bitllet és de $precio€")
    return eleccionString
}
/**
 * Gestiona el procés de pagament, calculant el canvi a retornar.
 * @param scan Scanner per a la lectura de dades.
 * @param precio Preu del bitllet.
 */
fun pagarITornarCanvi(scan: Scanner, precio: String) {
    val formato = DecimalFormat("#.00")
    println("Introdueix moneda per moneda")
    var dineroMetidoPorElCliente = 0.0
    var aPagar = precio.toDouble()
    imprimirmonedesPermeses()
    while (dineroMetidoPorElCliente < aPagar) {
        println("Et queda per pagar ${formato.format(aPagar - dineroMetidoPorElCliente)}€")
        var introducido = scan.nextDouble()
        if (introducido in listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0)) {
            dineroMetidoPorElCliente += introducido
        } else {
            println("Moneda no vàlida, introdueix una altra moneda.")
        }
    }
    println("Et torno el canvi ${formato.format(dineroMetidoPorElCliente - aPagar)}€")
    animacioTornarCanvi()
}
/**
 * Mostra el menú d'elecció de tipus de bitllet.
 */
fun imprimirMenuEleccioTipusBitllet() {
    println("""
        - - - - - - - - - - - - - - - - - - - - - - 
        Quin bitllet desitja adquirir?
        1. Bitllet Senzill
        2. TCasual
        3. TUsual
        4. TFamiliar
        5. TJove
    """.trimIndent())
}
/**
 * Mostra el menú d'elecció de zona.
 */
fun imprimirMenuEleccioZona() {
    println("""
        De quina zona desitja adquirir el bitllet?
        1
        2
        3
    """.trimIndent())
}
/**
 * Mostra les monedes i bitllets permesos per al pagament.
 */
fun imprimirmonedesPermeses() {
    println("""
        PAGAMENT EN EFECTIU:
        MONEDES I BITLLETS PERMESOS: 
        0.05€, 0.10€, 0.20€, 0.50€, 1€, 2€, 5€, 10€, 20€ i 50€.
    """.trimIndent())
}
/**
 * Simula una animació per a la devolució del canvi.
 */
fun animacioTornarCanvi() {
    Thread.sleep(500)
    println("Cling")
    Thread.sleep(500)
    println("Clong")
    Thread.sleep(500)
    println("Cling")
    Thread.sleep(500)
    println("Ja pots recollir els diners")
}
/**
 * Ofereix l'opció de generar un tiquet per a l'usuari.
 * @param scan Scanner per a la lectura de dades.
 * @param eleccionString Descripció del bitllet.
 * @param zonaTriada Zona seleccionada.
 * @param precio Preu del bitllet.
 */
fun tiquet(scan: Scanner, eleccionString: String, zonaTriada: Int, precio: String) {
    println("Vol tiquet? S/N ")
    val eleccio = scan.next().lowercase()
    if (eleccio == "s") {
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
          """.trimIndent())}}