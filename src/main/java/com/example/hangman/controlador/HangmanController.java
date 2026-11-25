package com.example.hangman.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HangmanController {
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

    @FXML
    private void initialize() {
        historialLista.setPlaceholder(new Label("No hay partidas guardadas."));
    }
}
