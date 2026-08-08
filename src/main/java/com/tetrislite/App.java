package com.tetrislite;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.io.IOException;
import com.tetrislite.UI.Gameapp;


public class App extends Application {
    
    public static Stage stage;
    public Parent root;

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
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(); //Calls innit and start method. An initial stage is also created and passed to the start method at this time
    }

}

