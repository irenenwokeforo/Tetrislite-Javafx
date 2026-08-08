package com.tetrislite.Tetriminos;

import javafx.scene.paint.Color;

public class Mino_J extends Mino{

    public Mino_J(){
        make(Color.BLUE);
    }

    public void setPosition(int x_pos, int y_pos){
        //     o (1)
        //     o (0)
        //  o  o (3, 2)

        blocks[0].x_pos = x_pos;
        blocks[0].y_pos = y_pos;

        blocks[1].x_pos = blocks[0].x_pos;
        blocks[1].y_pos = blocks[0].y_pos - Block.M_SIZE;
        
        blocks[2].x_pos = blocks[0].x_pos;
        blocks[2].y_pos = blocks[0].y_pos + Block.M_SIZE;
        
        blocks[3].x_pos = blocks[0].x_pos - Block.M_SIZE;
        blocks[3].y_pos = blocks[0].y_pos + Block.M_SIZE; 
    }


    public void getDirection1(){
        //     o (1)
        //     o (0)
        //  o  o (3, 2)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;

        temp_blocks[1].x_pos = blocks[0].x_pos;
        temp_blocks[1].y_pos = blocks[0].y_pos - Block.M_SIZE;
        
        temp_blocks[2].x_pos = blocks[0].x_pos;
        temp_blocks[2].y_pos = blocks[0].y_pos + Block.M_SIZE;
        
        temp_blocks[3].x_pos = blocks[0].x_pos - Block.M_SIZE;
        temp_blocks[3].y_pos = blocks[0].y_pos + Block.M_SIZE; 

        updateDirection(1);
    }

    public void getDirection2(){
        //  o (3)
        //  o  o  o (2, 0, 1)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;

        temp_blocks[1].x_pos = blocks[0].x_pos + Block.M_SIZE;
        temp_blocks[1].y_pos = blocks[0].y_pos;
        
        temp_blocks[2].x_pos = blocks[0].x_pos - Block.M_SIZE;
        temp_blocks[2].y_pos = blocks[0].y_pos;
        
        temp_blocks[3].x_pos = blocks[0].x_pos - Block.M_SIZE;
        temp_blocks[3].y_pos = blocks[0].y_pos - Block.M_SIZE; 

        updateDirection(2);
    }
    
    public void getDirection3(){
        //  o  o (2,3)
        //  o  (0)
        //  o  (1)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;

        temp_blocks[1].x_pos = blocks[0].x_pos;
        temp_blocks[1].y_pos = blocks[0].y_pos + Block.M_SIZE;
        
        temp_blocks[2].x_pos = blocks[0].x_pos;
        temp_blocks[2].y_pos = blocks[0].y_pos - Block.M_SIZE;
        
        temp_blocks[3].x_pos = blocks[0].x_pos + Block.M_SIZE;
        temp_blocks[3].y_pos = blocks[0].y_pos - Block.M_SIZE; 

        updateDirection(3);
    }

    public void getDirection4(){
        //  o  o  o (1, 0, 2)
        //        o (3)

        temp_blocks[0].x_pos = blocks[0].x_pos;
        temp_blocks[0].y_pos = blocks[0].y_pos;

        temp_blocks[1].x_pos = blocks[0].x_pos - Block.M_SIZE;
        temp_blocks[1].y_pos = blocks[0].y_pos;
        
        temp_blocks[2].x_pos = blocks[0].x_pos + Block.M_SIZE;
        temp_blocks[2].y_pos = blocks[0].y_pos;
        
        temp_blocks[3].x_pos = blocks[0].x_pos + Block.M_SIZE;
        temp_blocks[3].y_pos = blocks[0].y_pos + Block.M_SIZE; 

        updateDirection(4);
    }
}
