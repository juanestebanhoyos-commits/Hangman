package com.example.hangman;

import com.example.hangman.controlador.HangmanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HangmanApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HangmanApp.class.getResource("vista/hangman-view.fxml"));
        Scene scene = new Scene(loader.load(), 640, 520);

        HangmanController controller = loader.getController();
        scene.addEventFilter(KeyEvent.KEY_TYPED, controller::procesarTecla);

        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.show();
    }
}
