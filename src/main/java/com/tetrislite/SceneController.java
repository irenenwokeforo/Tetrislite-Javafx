package com.tetrislite;

import java.io.IOException;
import java.time.Duration;

import com.tetrislite.UI.BackgroundScene;
import com.tetrislite.UI.Gameapp;
import com.tetrislite.UI.KeyHandler;

import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SceneController {
    @FXML
    public Canvas backgroundCanvas;

    final static int FPS = 60;
    

    public void switchToGame(ActionEvent event) throws IOException{

        Gameapp ga = new Gameapp(); //new instance of the 
        ga.launchGame(); //triggers
        App.stage.setScene(ga.tetrisScene);
        App.stage.show(); //reveals our scene where the details are in primary.fxml
    }


    @FXML
    public void initialize(){ //Automatically called after loading FXML file in App

        GraphicsContext background_gc = backgroundCanvas.getGraphicsContext2D();
        launchBackground(background_gc);
    }


    public void launchBackground(GraphicsContext background_gc){

        BackgroundScene bs = new BackgroundScene();

        new AnimationTimer(){
            double delta = 0; 
            double renderInterval = 1000000000/FPS; //paint every 0.01666s 
            long lastTime = System.nanoTime();

            @Override
            public void handle(long currentTime){ //this is called every frame and has the current system time in nanoseconds as the parameter
                delta += (currentTime - lastTime) / renderInterval;

                if(delta >= 1){
                    bs.update();
                    bs.render(background_gc); 
                    delta--;
                }
            }
        }.start();
    }

}
