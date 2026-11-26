package com.example.hangman.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPalabras {
    private final Path archivo;

    public RepositorioPalabras(Path archivo) {
        this.archivo = archivo;
    }

    public List<String> leer() throws IOException {
        List<String> palabras = new ArrayList<>();
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            String palabra = linea.trim();
            if (!palabra.isEmpty()) {
                palabras.add(palabra);
            }
        }
        return palabras;
    }
}
