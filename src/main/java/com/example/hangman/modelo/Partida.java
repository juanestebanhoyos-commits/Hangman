package com.example.hangman.modelo;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Partida {
    public static final int MAX_ERRORES = 6;

    private final String jugador;
    private final String palabra;
    private final Set<Character> letrasProbadas = new HashSet<>();
    private int errores;

    public Partida(String jugador, String palabra) {
        this.jugador = jugador;
        this.palabra = palabra.toLowerCase(Locale.ROOT);
    }

    public ResultadoJugada probarLetra(char letra) {
        if (estaTerminada()) {
            return ResultadoJugada.TERMINADA;
        }

        char normalizada = Character.toLowerCase(letra);
        if (normalizada < 'a' || normalizada > 'z') {
            return ResultadoJugada.INVALIDA;
        }

        if (letrasProbadas.contains(normalizada)) {
            return ResultadoJugada.REPETIDA;
        }

        letrasProbadas.add(normalizada);

        if (palabra.indexOf(normalizada) >= 0) {
            return estaGanada() ? ResultadoJugada.VICTORIA : ResultadoJugada.ACIERTO;
        }

        errores++;
        return errores == MAX_ERRORES ? ResultadoJugada.DERROTA : ResultadoJugada.FALLO;
    }

    public boolean estaRevelada(int posicion) {
        // Al terminar la partida se muestra la palabra completa.
        return estaTerminada() || letrasProbadas.contains(palabra.charAt(posicion));
    }

    public boolean estaTerminada() {
        return estaGanada() || errores >= MAX_ERRORES;
    }

    public boolean estaGanada() {
        for (int i = 0; i < palabra.length(); i++) {
            if (!letrasProbadas.contains(palabra.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String comoLineaHistorial() {
        if (!estaTerminada()) {
            throw new IllegalStateException("La partida todavía está en curso");
        }
        String resultado = estaGanada() ? "Victoria" : "Derrota";
        return String.join(",", jugador, palabra, resultado, String.valueOf(errores));
    }

    public String getJugador() {
        return jugador;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getErrores() {
        return errores;
    }

    public int getIntentosRestantes() {
        return MAX_ERRORES - errores;
    }
}
