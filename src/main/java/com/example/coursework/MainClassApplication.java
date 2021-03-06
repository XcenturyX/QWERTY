package com.example.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Класс, который запускает приложение.
 */
public class MainClassApplication extends Application {
    static final double widthScene = 1024;
    static final double heightScene = 494;

    /**
     * Точка входа для JavaFX приложения.
     * @param stage - главный контейнер.
     * @throws IOException - возможно IOException.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClassApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), widthScene, heightScene );
        scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
        stage.setTitle("Фоторедактор");
        InputStream iconStream = getClass().getResourceAsStream("useFoto/iconca.png");
        Image icon = new Image(iconStream);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Запуск приложения.
     */
    public static void main(String[] args) {
        launch();
    }
}