package com.tetrislite.input;

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

        ga.tetrisScene.setOnKeyPressed(e -> { //Calls handle(e) which contains this body
            
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




    

