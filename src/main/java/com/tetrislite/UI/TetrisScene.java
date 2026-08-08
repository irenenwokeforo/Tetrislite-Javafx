package com.tetrislite.UI;

import java.util.ArrayList;
import java.util.Random;

import com.tetrislite.Tetriminos.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TetrisScene { //Holds layout of play area, canvas for drawing, connects visuals to logic

    public GraphicsContext gc;
    Canvas canvas; //allows you to 'draw' on a scene is a type of node
    Gameapp ga;
    KeyHandler kh;

    //Play Area Dimensions
    final int PANEL_WIDTH = 300;
    final int PANEL_HEIGHT = 600; //each block should be 30x30 pixels
    final int BLOCK_SIZE = 30;

    //Play Area Boundaries
    public static int p_left_x;
    public static int p_right_x;
    public static int p_top_y;
    public static int p_bottom_y;

    //Current Mino Handling
    Mino cur_mino;
    int minoValue;
    final int START_POS_X;
    final int START_POS_Y;

    //Next Mino Handling
    Mino next_mino1;
    Mino next_mino2;
    Mino next_mino3;

    final int NEXTMINO_X;
    final int NEXTMINO_Y;

    //Inactive Mino Handling
    public static ArrayList<Block> inactiveBlocks = new ArrayList<>();

    //Line Clearing Effects
    int clearingCounter = 0;
    int totalLinesCleared;
    boolean clearingCounterOn;
    ArrayList<Integer> rowsforEffects = new ArrayList<>();

    //Game Settings
    static public int dropSpeed = Gameapp.FPS;
    int staticSpeedCounter = 0;
    int level = 1;
    int score = 0;
    public boolean gameOver;

    double[] x_Paused;
    double[] y_Paused;

    public TetrisScene(Gameapp ga){ //passing the instance of the gameapp object from App to the scene

        this.ga = ga;
        canvas = new Canvas(ga.APP_WIDTH, ga.APP_HEIGHT); //creates a new canvas ONCE (only when this constructor is called)
        gc = canvas.getGraphicsContext2D(); //attatching our paint brush to said canvas
        kh = new KeyHandler(ga);

        //Panel corners
        p_left_x = (ga.APP_WIDTH / 2) - (PANEL_WIDTH / 2); //250
        p_right_x = p_left_x + PANEL_WIDTH; //550
        p_top_y = (ga.APP_HEIGHT / 2) - (PANEL_HEIGHT / 2); //100
        p_bottom_y = p_top_y + PANEL_HEIGHT;

        //Starting Mino Coordinates
        START_POS_X = p_left_x + (PANEL_WIDTH / 2) - BLOCK_SIZE;
        START_POS_Y = p_top_y + BLOCK_SIZE;

        //Next Mino Coordinates
        NEXTMINO_X = p_right_x + 117 - BLOCK_SIZE;
        NEXTMINO_Y = p_top_y + BLOCK_SIZE;


        //Setting each mino
        cur_mino = pickMino(); //change to pickMino2

        if(cur_mino instanceof Mino_O){
            cur_mino.setPosition(START_POS_X, START_POS_Y - BLOCK_SIZE);
        }

        else{
            cur_mino.setPosition(START_POS_X, START_POS_Y);
        }

        next_mino1 = pickMino();
        recenter(next_mino1, NEXTMINO_Y);

        next_mino2 = pickMino();
        recenter(next_mino2, NEXTMINO_Y + (4* BLOCK_SIZE));
    
        next_mino3 = pickMino();
        recenter(next_mino3, NEXTMINO_Y + (8 * BLOCK_SIZE));
            //Misc Dimensions

        staticRender();

        ga.addNode(canvas);
    }


    private void recenter(Mino m, final int Y_POS){
        //Center minos in the waiting box horizontally
        if(m instanceof Mino_Z || m instanceof Mino_J || m instanceof Mino_T){
            m.setPosition(NEXTMINO_X + (BLOCK_SIZE / 2), Y_POS);
        }

        else{
            m.setPosition(NEXTMINO_X, Y_POS);
        }
    }

// make reusable
    public Mino pickMino(){
        Mino mino = null;
        minoValue = new Random().nextInt(7);

        switch(minoValue){
            case 0: mino = new Mino_I();break;
            case 1: mino = new Mino_J();break;
            case 2: mino = new Mino_L();break;
            case 3: mino = new Mino_O();break;
            case 4: mino = new Mino_S();break;
            case 5: mino = new Mino_T();break;
            case 6: mino = new Mino_Z();break;
        }

        return mino;
    }


    public void clearRow(){
        int x_scanner = p_left_x;
        int y_scanner = p_top_y;
        int staticBlockCount = 0;
        int tempLinesCleared = 0;

        //check blocks bottom up
        while(x_scanner < p_right_x && y_scanner < p_bottom_y){
            for(int i = 0; i < inactiveBlocks.size(); i++){
                //once a match is found at the scanner position count the block and break the loop
                if(inactiveBlocks.get(i).x_pos == x_scanner && inactiveBlocks.get(i).y_pos == y_scanner){
                    staticBlockCount++;
                    break;
                }
            }

            x_scanner += BLOCK_SIZE;

            if(x_scanner == p_right_x){
                if(staticBlockCount == 10){
                    clearingCounterOn = true;
                    rowsforEffects.add(y_scanner);

                    for(int i = inactiveBlocks.size() - 1; i > -1; i--){
                        if(inactiveBlocks.get(i).y_pos == y_scanner){
                            inactiveBlocks.remove(i);
                        }
                    }

                    for (int i = inactiveBlocks.size() -1; i > -1; i--){
                        if(inactiveBlocks.get(i).y_pos < y_scanner){
                            inactiveBlocks.get(i).y_pos += BLOCK_SIZE;
                        }
                    }

                    tempLinesCleared++;
                    totalLinesCleared++;

                    increaseSpeed();
                }
                
                staticBlockCount = 0;
                x_scanner = p_left_x;
                y_scanner += BLOCK_SIZE;
            }

        }

        calcScore(tempLinesCleared);
    }


    public void increaseSpeed(){
        if(totalLinesCleared > 0 && totalLinesCleared % 10 == 0 && dropSpeed > 4 && level <= 30){
            level++;

            if(level <= 10){
                dropSpeed = dropSpeed - 5;
            }

            else if(level > 10 && level < 20){
                staticSpeedCounter++;

                if(staticSpeedCounter == 2){
                    dropSpeed = dropSpeed - 1;
                    staticSpeedCounter = 0;
                }
            }
        }
    }


    public int calcScore(int tempLinesCleared){

        if (tempLinesCleared > 0 && score <= 999999){
            int scorePerLine = level * 10;
            score += scorePerLine * tempLinesCleared;
        }

        return score;
    }


    public void update(){

        if(cur_mino.active == false){

            //Add inactive minos to deactivated list
            inactiveBlocks.add(cur_mino.blocks[0]);
            inactiveBlocks.add(cur_mino.blocks[1]);
            inactiveBlocks.add(cur_mino.blocks[2]);
            inactiveBlocks.add(cur_mino.blocks[3]);

            //Check if game over
            if(cur_mino.blocks[0].x_pos == START_POS_X && cur_mino.blocks[0].y_pos == START_POS_Y){
                gameOver = true;
            }

            //Set next minos
            cur_mino = next_mino1;

            if(cur_mino instanceof Mino_O){
                cur_mino.setPosition(START_POS_X, START_POS_Y - BLOCK_SIZE);
            }
    
            else{
                cur_mino.setPosition(START_POS_X, START_POS_Y);
            }

            next_mino1 = next_mino2;
            recenter(next_mino1, NEXTMINO_Y);

            next_mino2 = next_mino3;
            recenter(next_mino2, NEXTMINO_Y + (4* BLOCK_SIZE));

            next_mino3 = pickMino();
            recenter(next_mino3, NEXTMINO_Y + (8 * BLOCK_SIZE));

            clearRow();
        }

        else{
            cur_mino.update();
        }
        
    }

    public void staticRender(){
        //Set Font**
        Font font = Font.loadFont("file:src/main/resources/font/Handjet-Bold.ttf", 50);
        gc.setFont(font);

        //Play Area Box
        gc.setLineWidth(8);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(p_left_x - 8, p_top_y - 8, PANEL_WIDTH + 16, PANEL_HEIGHT + 16, 16, 16);

        //Mino Waiting Box
        gc.strokeRoundRect(p_right_x + 42, p_top_y - 8, 150, 360, 16, 16);

        //Stats Box
        gc.strokeRoundRect(p_left_x - 192, p_top_y - 8, 150, 360, 16, 16);
    }

    public void render(){

        //Clear Dynamic Areas 
        gc.clearRect(p_left_x , p_top_y, PANEL_WIDTH, PANEL_HEIGHT);
        gc.clearRect(p_right_x + 50, p_top_y, 134, 344);
        gc.clearRect(p_left_x - 184, p_top_y, 134, 344);

        //Draw grid
        gc.setFill(Color.web("#FFA8B8"));
        gc.fillRoundRect(p_left_x - 4, p_top_y - 4, PANEL_WIDTH + 8, PANEL_HEIGHT + 8, 16, 16);
        gc.setStroke(Color.PINK);
        gc.setLineWidth(2);

        for(int x = p_left_x + BLOCK_SIZE; x < p_right_x; x += BLOCK_SIZE){
            gc.strokeLine(x, p_top_y, x, p_bottom_y);
        }
        for(int y = p_top_y + BLOCK_SIZE; y < p_bottom_y; y += BLOCK_SIZE){
            gc.strokeLine(p_left_x, y, p_right_x, y);
        }

        //Shadowed Text
        gc.setFill(Color.web("#8F6A7C"));
        gc.fillText("SCORE : " + score, p_left_x - 185, p_top_y + 50, 134);
        gc.fillText("LEVEL : " + level, p_left_x - 185, p_top_y + 186, 134 );
        gc.fillText("LINES : " + totalLinesCleared, p_left_x - 185, p_top_y + 327, 134);

        //Regular Text
        gc.setFill(Color.WHITE);
        gc.fillText("SCORE : " + score, p_left_x - 186, p_top_y + 45, 134);
        gc.fillText("LEVEL : " + level, p_left_x - 186, p_top_y + 181, 134 );
        gc.fillText("LINES : " + totalLinesCleared, p_left_x - 186, p_top_y + 322, 134);

        //Draw all Minos
        cur_mino.render(gc);
        next_mino1.render(gc);
        next_mino2.render(gc);
        next_mino3.render(gc);

        for(int i = 0; i < inactiveBlocks.size(); i++){
            inactiveBlocks.get(i).render(gc);
        }

        //Pause & play symbols
        if(KeyHandler.pausePressed){
            gc.clearRect(p_right_x + 170, p_top_y - 90, 40, 50);
            gc.setFill(Color.WHITE);
            double[] x_Paused = {p_right_x + 180, p_right_x + 180, p_right_x + 204};
            double[] y_Paused = {p_top_y - 80, p_top_y - 45, p_top_y - 62.5};
            gc.fillPolygon(x_Paused, y_Paused, 3);
        }

        else{
            gc.clearRect(p_right_x + 170, p_top_y - 90, 40, 50);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(4);
            gc.strokeRoundRect(p_right_x + 180, p_top_y - 80, 4, 35, 4, 4);
            gc.strokeRoundRect(p_right_x + 200, p_top_y - 80, 4, 35, 4, 4);
        }

        //Effects
        if(clearingCounterOn){

            clearingCounter++;
            gc.setFill(Color.WHITE);

            for(int i = 0; i < rowsforEffects.size(); i++){
                gc.fillRect(p_left_x, rowsforEffects.get(i), PANEL_WIDTH, BLOCK_SIZE);
            }

            if(clearingCounter == 5){
                
                clearingCounterOn = false;
                clearingCounter = 0;
                rowsforEffects.clear();
            }
        }

    }
    
}
