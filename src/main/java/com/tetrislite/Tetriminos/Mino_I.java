package com.tetrislite.Tetriminos;

import javafx.scene.paint.Color;

public class Mino_I extends Mino{

    public Mino_I(){
        make(Color.CYAN);
    }

    public void setPosition(int x_pos, int y_pos){
        //  o  o  o  o
        //(1, 0, 2, 3)

        blocks[0].x_pos = x_pos;
        blocks[0].y_pos = y_pos;

        blocks[1].x_pos = blocks[0].x_pos - Block.M_SIZE;
        blocks[1].y_pos = blocks[0].y_pos;
        
        blocks[2].x_pos = blocks[0].x_pos + Block.M_SIZE;
        blocks[2].y_pos = blocks[0].y_pos;
        
        blocks[3].x_pos = blocks[0].x_pos + (2 * Block.M_SIZE);
        blocks[3].y_pos = blocks[0].y_pos;
    }


    public void getDirection1(){
        //  o  o  o  o
        //(1, 0, 2, 3)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;

        temp_blocks[1].x_pos = blocks[0].x_pos - Block.M_SIZE;
        temp_blocks[1].y_pos = blocks[0].y_pos;

        temp_blocks[2].x_pos = blocks[0].x_pos + Block.M_SIZE;
        temp_blocks[2].y_pos = blocks[0].y_pos;
        
        temp_blocks[3].x_pos = blocks[0].x_pos + (2 * Block.M_SIZE);
        temp_blocks[3].y_pos = blocks[0].y_pos;

        updateDirection(1);
    }


    public void getDirection2(){
        //  o (1)
        //  o (0)
        //  o (2)
        //  o (3)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;
        
        temp_blocks[1].x_pos = blocks[0].x_pos;
        temp_blocks[1].y_pos = blocks[0].y_pos - Block.M_SIZE;
        
        temp_blocks[2].x_pos = blocks[0].x_pos;
        temp_blocks[2].y_pos = blocks[0].y_pos + Block.M_SIZE;
        
        temp_blocks[3].x_pos = blocks[0].x_pos;
        temp_blocks[3].y_pos = blocks[0].y_pos + (2 * Block.M_SIZE);
        
        updateDirection(2);
    }


    public void getDirection3(){
        getDirection1();
    }


    public void getDirection4(){
        getDirection2();
    }
}
