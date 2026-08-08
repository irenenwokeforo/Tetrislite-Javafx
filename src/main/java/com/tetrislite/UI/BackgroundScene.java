package com.tetrislite.UI;

import java.util.ArrayList;
import java.util.Random;

import com.tetrislite.Tetriminos.Block;
import com.tetrislite.Tetriminos.Mino;

import javafx.scene.canvas.GraphicsContext;



public class BackgroundScene{

    public GraphicsContext gc;

    int x_pos;
    int y_pos;

    int dropTimer;
    final static int FPS = 10;

    private ArrayList<Mino> fallingMinos = new ArrayList<>();
    private Random random = new Random();



    public BackgroundScene(){

        for(int i = 0; i < 15; i++){
            fallingMinos.add(MinoGenerator.pickMino2());
        }

        randomizePosition();
        
    }

    public void update(){
        dropTimer++;
        if(dropTimer == FPS){
            for(int i = 0; i < fallingMinos.size(); i++){
                fallingMinos.get(i).blocks[0].y_pos += Block.M_SIZE;
                fallingMinos.get(i).blocks[1].y_pos += Block.M_SIZE;
                fallingMinos.get(i).blocks[2].y_pos += Block.M_SIZE;
                fallingMinos.get(i).blocks[3].y_pos += Block.M_SIZE;

                if(fallingMinos.get(i).blocks[0].y_pos > 800 + 30){
                    int curPos = fallingMinos.get(i).blocks[0].x_pos;
                    fallingMinos.remove(i);
                    fallingMinos.add(i, MinoGenerator.pickMino2());
                    fallingMinos.get(i).setPosition(curPos, 0);
                }
            }
            dropTimer = 0;
        }
    }

    public void randomizePosition(){  

        int count, x_pos, y_pos;

        count = 0;
        x_pos = -15;
        y_pos = 0;

        for(int i = 0; i < fallingMinos.size(); i++){
            if(count == 8){
                x_pos = -15;
                count = 0;
            }

            else{
                x_pos += random.nextInt(150 - 100) + 100; 
            }

            y_pos = -random.nextInt(801);
            fallingMinos.get(i).setPosition(x_pos, y_pos);
            count++;
        }
    }


    public void render(GraphicsContext background_gc){

        background_gc.clearRect(0, 0, 800, 800);

        for(int i = 0; i < fallingMinos.size(); i++){
            fallingMinos.get(i).render(background_gc);
        }
    }
}
