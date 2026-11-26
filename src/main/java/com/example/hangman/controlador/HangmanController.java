package com.example.hangman.controlador;

import com.example.hangman.modelo.Partida;
import com.example.hangman.modelo.RepositorioPalabras;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HangmanController {
    private static final Path ARCHIVO_PALABRAS = Path.of("palabras.txt");

    @FXML
    private TextField nombreField;

    @FXML
    private HBox cajasPalabra;

    @FXML
    private Label intentosLabel;

    @FXML
    private Label mensajeLabel;

    @FXML
    private ListView<String> historialLista;

    private RepositorioPalabras repositorioPalabras;
    private Partida partidaActual;

    @FXML
    private void initialize() {
        repositorioPalabras = new RepositorioPalabras(ARCHIVO_PALABRAS);
        historialLista.setPlaceholder(new Label("No hay partidas guardadas."));
    }

    @FXML
    private void iniciarPartida() {
        String nombre = nombreField.getText().trim();
        if (nombre.isEmpty()) {
            mostrarMensaje("Escriba un nombre para iniciar la partida.");
            return;
        }
        if (nombre.contains(",")) {
            mostrarMensaje("El nombre no puede contener comas.");
            return;
        }

        List<String> palabras;
        try {
            palabras = repositorioPalabras.leer();
        } catch (IOException e) {
            mostrarMensaje("No se pudo leer palabras.txt. Verifique que exista y sea accesible.");
            return;
        }
        if (palabras.isEmpty()) {
            mostrarMensaje("palabras.txt no contiene palabras.");
            return;
        }

        String palabra = palabras.get(ThreadLocalRandom.current().nextInt(palabras.size()));
        partidaActual = new Partida(nombre, palabra);
        dibujarCasillas();
        mostrarMensaje("Partida iniciada. Escriba una letra.");
    }

    private void dibujarCasillas() {
        cajasPalabra.getChildren().clear();
        for (int i = 0; i < partidaActual.getPalabra().length(); i++) {
            Label casilla = new Label();
            casilla.getStyleClass().add("casilla");
            cajasPalabra.getChildren().add(casilla);
        }
        actualizarTablero();
    }

    private void actualizarTablero() {
        for (int i = 0; i < cajasPalabra.getChildren().size(); i++) {
            Label casilla = (Label) cajasPalabra.getChildren().get(i);
            casilla.setText(partidaActual.estaRevelada(i)
                    ? String.valueOf(partidaActual.getPalabra().charAt(i))
                    : "");
        }
        intentosLabel.setText("Intentos restantes: " + partidaActual.getIntentosRestantes());
    }

    private void mostrarMensaje(String texto) {
        mensajeLabel.setText(texto);
    }
}
