package com.tetrislite.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Gameapp { 

    public final int APP_WIDTH = 800;
    public final int APP_HEIGHT = 800;

    final static int FPS = 60;

    //Root Scene creation (the root acts as the root node so that)
    TetrisScene ts;
    public Scene tetrisScene;
    protected Group root; 


    public Gameapp() { //Subclass of Scene so its constructor will call the scene constructor with the appropriate
        root = new Group(); //Group is used as the root which will put all nodes in a container and clip their properties to the size of the scene
        tetrisScene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.PINK);
        ts = new TetrisScene(this); //use to access the GameScene component which will have the rectangles

    }


    public void launchGame(){
        
        //Game Loop
        new AnimationTimer(){
            double delta = 0; 
            double renderInterval = 1000000000/FPS; //paint every 1s / 60 = 0.01666s 
            long lastTime = System.nanoTime();

            @Override
            public void handle(long currentTime){ //this is called every frame and has the current system time in nanoseconds as the parameter
                delta += (currentTime - lastTime) / renderInterval;

                if(delta >= 1){
                    if(KeyHandler.pausePressed == false && ts.gameOver == false){
                        ts.update();
                    }
                    ts.render(); 
                    delta--;
                }
            }
        }.start(); //starts the animation timer which is different from App's start, this triggers the handle method to be called 60 times per second
    }

    public void addNode(Node node) { //makes it easier to add nodes
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
        }
    }
}
