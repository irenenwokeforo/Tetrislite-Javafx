package com.tetrislite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;


import java.io.File;
import java.io.IOException;
import java.net.URL;






public class App extends Application {
    
    public static Stage stage;
    public Parent root;
    public MediaPlayer mediaPlayer;

    @Override
    public void start(Stage s) throws IOException {
        
        try{
            stage = s;
            root = FXMLLoader.load(getClass().getResource("/com/tetrislite/IntroScreen.fxml"));
            Scene introScreen = new Scene(root);
            stage.setTitle("TetrisLite");
            stage.setResizable(false);
            stage.setScene(introScreen);
            stage.show();

            //music
            URL resource = getClass().getResource("/tetrislite_music.m4a");
            Media sound = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(); //Calls innit and start method. An initial stage is also created and passed to the start method at this time
    }

}

