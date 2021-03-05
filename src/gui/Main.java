package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = null;
        if (getClass().getResource("resources/sample.fxml") == null) {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } else
            root = FXMLLoader.load(getClass().getResource("resources/sample.fxml"));
        stage.setTitle("Fakturačný systém");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("resources/Ikona.png")));
        stage.setScene(new Scene(root, 600, 800));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
