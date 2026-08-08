package com.tetrislite.Tetriminos;

import javafx.scene.paint.Color;

public class Mino_O extends Mino{

    public Mino_O() {
        make(Color.GOLD);
    }

    
    public void setPosition(int x_pos, int y_pos){
        //  o  o (0, 2)
        //  o  o (1, 3)

        blocks[0].x_pos = x_pos;
        blocks[0].y_pos = y_pos;

        blocks[1].x_pos = blocks[0].x_pos;
        blocks[1].y_pos = blocks[0].y_pos + Block.M_SIZE;

        blocks[2].x_pos = blocks[0].x_pos + Block.M_SIZE;
        blocks[2].y_pos = blocks[0].y_pos;
        
        blocks[3].x_pos = blocks[0].x_pos + Block.M_SIZE;
        blocks[3].y_pos = blocks[0].y_pos + Block.M_SIZE;
    }


    public void updateDirection(int direction){}
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}
}
