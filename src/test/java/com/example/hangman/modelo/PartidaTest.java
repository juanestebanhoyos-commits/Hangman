package com.example.hangman.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PartidaTest {

    @Test
    void letraCorrectaRevelaTodasSusPosiciones() {
        Partida partida = new Partida("Sara", "palabra");

        assertEquals(ResultadoJugada.ACIERTO, partida.probarLetra('a'));

        assertTrue(partida.estaRevelada(1));
        assertTrue(partida.estaRevelada(3));
        assertTrue(partida.estaRevelada(6));
        assertFalse(partida.estaRevelada(0));
        assertEquals(6, partida.getIntentosRestantes());
    }

    @Test
    void falloRepetidaEInvalidaManejanLosIntentos() {
        Partida partida = new Partida("Sara", "palabra");

        assertEquals(ResultadoJugada.FALLO, partida.probarLetra('z'));
        assertEquals(5, partida.getIntentosRestantes());

        assertEquals(ResultadoJugada.REPETIDA, partida.probarLetra('z'));
        assertEquals(5, partida.getIntentosRestantes());

        assertEquals(ResultadoJugada.INVALIDA, partida.probarLetra('1'));
        assertEquals(5, partida.getIntentosRestantes());
    }

    @Test
    void victoriaAlRevelarTodasLasLetras() {
        Partida partida = new Partida("Sara", "sol");

        assertEquals(ResultadoJugada.ACIERTO, partida.probarLetra('s'));
        assertEquals(ResultadoJugada.ACIERTO, partida.probarLetra('o'));
        assertEquals(ResultadoJugada.VICTORIA, partida.probarLetra('l'));

        assertTrue(partida.estaTerminada());
        assertEquals(ResultadoJugada.TERMINADA, partida.probarLetra('x'));
        assertEquals(0, partida.getErrores());
        assertEquals("Sara,sol,Victoria,0", partida.comoLineaHistorial());
    }

    @Test
    void derrotaAlSextoError() {
        Partida partida = new Partida("Sara", "sol");

        for (char letra : new char[]{'x', 'z', 'q', 'w', 'k'}) {
            assertEquals(ResultadoJugada.FALLO, partida.probarLetra(letra));
        }
        assertEquals(ResultadoJugada.DERROTA, partida.probarLetra('j'));

        assertEquals(0, partida.getIntentosRestantes());
        assertTrue(partida.estaTerminada());
        assertEquals(ResultadoJugada.TERMINADA, partida.probarLetra('s'));
        assertEquals(6, partida.getErrores());
        assertEquals("Sara,sol,Derrota,6", partida.comoLineaHistorial());
    }
}
