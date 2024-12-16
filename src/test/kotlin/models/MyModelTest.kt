package models

import org.example.controllers.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

class EleccionEnStringTest {

    @Test
    fun `convertir tipo de bitllet 1 a descripcion`() {
        // Entrada
        val menuBitlletsIEleccio = 1

        // Ejecución
        val resultado = eleccionEnString(menuBitlletsIEleccio)

        // Verificación
        assertEquals("Bitllet Senzill", resultado)
    }

    @Test
    fun `convertir tipo de bitllet 3 a descripcion`() {
        // Entrada
        val menuBitlletsIEleccio = 3

        // Ejecución
        val resultado = eleccionEnString(menuBitlletsIEleccio)

        // Verificación
        assertEquals("TUsual", resultado)
    }

    @Test
    fun `convertir tipo de bitllet desconocido`() {
        // Entrada
        val menuBitlletsIEleccio = 99

        // Ejecución
        val resultado = eleccionEnString(menuBitlletsIEleccio)

        // Verificación
        assertEquals(" ", resultado)
    }
}


class ImprimirEleccionTest {

    @Test
    fun `imprimir eleccion con Bitllet Senzill y zona 1`() {
        // Entrada
        val eleccionString = "Bitllet Senzill"
        val zona = 1
        val precio = "2.40"

        // Ejecución
        val resultado = imprimirEleccion(eleccionString, zona, precio)

        // Verificación
        assertEquals("Bitllet Senzill", resultado)
    }

    @Test
    fun `imprimir eleccion con TUsual y zona 3`() {
        // Entrada
        val eleccionString = "TUsual"
        val zona = 3
        val precio = "73.77"

        // Ejecución
        val resultado = imprimirEleccion(eleccionString, zona, precio)

        // Verificación
        assertEquals("TUsual", resultado)
    }

    @Test
    fun `imprimir eleccion con tipo desconocido`() {
        // Entrada
        val eleccionString = " "
        val zona = 2
        val precio = "0.00"

        // Ejecución
        val resultado = imprimirEleccion(eleccionString, zona, precio)

        // Verificación
        assertEquals(" ", resultado)
    }
}


class TriarZonaTest {

    @Test
    fun `seleccionar zona valida 1`() {
        // Simulación de entrada del usuario
        val input = "1\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = triarZona(scan)

        // Verificación
        assertEquals(1, resultado)
    }

    @Test
    fun `seleccionar zona valida 2`() {
        // Simulación de entrada del usuario
        val input = "2\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = triarZona(scan)

        // Verificación
        assertEquals(2, resultado)
    }

    @Test
    fun `introducir valores no validos y luego zona 3`() {
        // Simulación de entradas: un texto inválido y luego una zona válida
        val input = "abc\n4\n3\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = triarZona(scan)

        // Verificación
        assertEquals(3, resultado)
    }
}


class EleccioTipusBitlletTest {

    @Test
    fun `seleccionar bitllet TCasual`() {
        // Simulación de entrada del usuario
        val input = "2\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = eleccioTipusBitllet(scan)

        // Verificación
        assertEquals(2, resultado)
    }

    @Test
    fun `introducir valores no validos y luego TJove`() {
        // Simulación de entradas: un texto inválido y luego un valor válido
        val input = "xyz\n6\n5\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = eleccioTipusBitllet(scan)

        // Verificación
        assertEquals(5, resultado)
    }

    @Test
    fun `detener la máquina con código 4321`() {
        // Simulación de entrada del usuario
        val input = "4321\n"
        val scan = Scanner(input)

        // Ejecución
        val resultado = eleccioTipusBitllet(scan)

        // Verificación
        assertEquals(4321, resultado)
    }
}


class TiquetTest {

    @Test
    fun `generar tiquet cuando el usuario dice que sí`() {
        // Simulación de entrada del usuario
        val input = "s\n"
        val scan = Scanner(input)

        // Variables de entrada
        val eleccionString = "Bitllet Senzill"
        val zonaTriada = 1
        val precio = "2.40"

        // Ejecución (Verificamos que la función no lanza excepciones)
        tiquet(scan, eleccionString, zonaTriada, precio)
    }

    @Test
    fun `no generar tiquet cuando el usuario dice que no`() {
        // Simulación de entrada del usuario
        val input = "n\n"
        val scan = Scanner(input)

        // Variables de entrada
        val eleccionString = "TUsual"
        val zonaTriada = 3
        val precio = "73.77"

        // Ejecución
        tiquet(scan, eleccionString, zonaTriada, precio)
    }

    @Test
    fun `manejar entrada invalida y luego generar tiquet`() {
        // Simulación de entradas: un valor inválido y luego uno válido
        val input = "abc\ns\n"
        val scan = Scanner(input)

        // Variables de entrada
        val eleccionString = "TJove"
        val zonaTriada = 2
        val precio = "105.00"

        // Ejecución
        tiquet(scan, eleccionString, zonaTriada, precio)
    }
}



class ImprimirMenusTest {

    @Test
    fun `imprimir menu de tipus de bitllet`() {
        // Redirigir la salida estándar
        val salida = ByteArrayOutputStream()
        System.setOut(PrintStream(salida))

        // Ejecución
        imprimirMenuEleccioTipusBitllet()

        // Verificación
        val resultado = salida.toString().trim()
        assertTrue(resultado.contains("Quin bitllet desitja adquirir?"))
        assertTrue(resultado.contains("1. Bitllet Senzill"))
    }

    @Test
    fun `imprimir menu de eleccio de zona`() {
        // Redirigir la salida estándar
        val salida = ByteArrayOutputStream()
        System.setOut(PrintStream(salida))

        // Ejecución
        imprimirMenuEleccioZona()

        // Verificación
        val resultado = salida.toString().trim()
        assertTrue(resultado.contains("De quina zona desitja adquirir el bitllet?"))
        assertTrue(resultado.contains("1"))
        assertTrue(resultado.contains("2"))
    }
}

