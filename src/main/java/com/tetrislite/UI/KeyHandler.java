package com.tetrislite.UI;

import java.util.ArrayList;

import com.tetrislite.Tetriminos.Block;

import javafx.scene.input.KeyCode;




public class KeyHandler {

    public static boolean leftPressed, rightPressed, upPressed, downPressed, pausePressed;
    public static KeyCode code;
    Gameapp ga;
    public static ArrayList<KeyCode> comboCodes = new ArrayList<>();

    
    public KeyHandler(Gameapp ga){
        pausePressed = false;
        this.ga = ga;
        ga.tetrisScene.setOnKeyPressed(e -> { //creates a new event handler, calls handle(e) which contains the lambda we created with e as its
            //parameter and the code within {} is the body. An argument is passed to handle which is when a key is pressed hence setOnKeyPressed
            //which as we learned in CMPUT 325 takes the place of our e.
            
            KeyCode code = e.getCode();
            comboCodes.add(code);
            
            if(code == KeyCode.A || code == KeyCode.LEFT){
                leftPressed = true;
            }
    
            if(code == KeyCode.D || code == KeyCode.RIGHT){
                rightPressed = true;
            }
    
            if(code == KeyCode.W || code == KeyCode.UP){
                upPressed = true;
            }
    
            if(code == KeyCode.S || code == KeyCode.DOWN){
                downPressed = true;
            }

            if(code == KeyCode.SPACE){
                if(pausePressed){
                    pausePressed = false;
                }
                else{
                    pausePressed = true;
                }
            }
        });



    }

}




    

