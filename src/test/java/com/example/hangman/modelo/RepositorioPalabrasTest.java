package com.example.hangman.modelo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositorioPalabrasTest {

    @TempDir
    Path tempDir;

    @Test
    void leePalabrasEIgnoraLineasVacias() throws IOException {
        Path archivo = tempDir.resolve("palabras.txt");
        Files.writeString(archivo, "palabra\n\njava\n");

        List<String> palabras = new RepositorioPalabras(archivo).leer();

        assertEquals(List.of("palabra", "java"), palabras);
    }

    @Test
    void archivoVacioDevuelveListaVacia() throws IOException {
        Path archivo = tempDir.resolve("palabras.txt");
        Files.writeString(archivo, "");

        assertTrue(new RepositorioPalabras(archivo).leer().isEmpty());
    }

    @Test
    void archivoInexistenteLanzaIOException() {
        Path archivo = tempDir.resolve("no-existe.txt");

        assertThrows(IOException.class, () -> new RepositorioPalabras(archivo).leer());
    }
}
