package com.tetrislite.Tetriminos;
import java.util.Random;

import com.tetrislite.UI.KeyHandler;
import com.tetrislite.UI.TetrisScene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public abstract class Mino{

    public Block blocks[] = new Block[4];
    Block temp_blocks[] = new Block[4];

    public static int dropTimer = 0;
    static int deactivationTimer = 0;

    public int direction = 1;

    boolean leftCollision, rightCollision, bottomCollision;
    public boolean active = true;
    boolean deactivating;


    public void make(Color colour){
        blocks[0] = new Block(colour); //All movements and rotations will revolve around the reference block 0
        blocks[1] = new Block(colour);
        blocks[2] = new Block(colour);
        blocks[3] = new Block(colour);

        temp_blocks[0] = new Block(colour);
        temp_blocks[1] = new Block(colour);
        temp_blocks[2] = new Block(colour);
        temp_blocks[3] = new Block(colour);
    }



    public void render(GraphicsContext gc){

        Color base = blocks[0].colour;
        Color highlight = Color.WHITE.deriveColor(1, 1, 1, 0.7);
        Color shadow = blocks[0].getShadow(base);

        int margin = 2;
        
        for(int i = 0; i < blocks.length; i++){

            gc.setFill(base);
            gc.fillRect(blocks[i].x_pos + margin, blocks[i].y_pos + margin, Block.M_SIZE - (margin * 2), Block.M_SIZE - (margin * 2));

            gc.setFill(highlight);
            double[] h_xPoints = {blocks[i].x_pos + margin, blocks[i].x_pos + 28.0,  blocks[i].x_pos + 25, blocks[i].x_pos + 5};
            double[] h_yPoints = {blocks[i].y_pos + margin, blocks[i].y_pos + margin, blocks[i].y_pos + 5, blocks[i].y_pos + 5};
            gc.fillPolygon(h_xPoints, h_yPoints,4);

            gc.setFill(shadow);
            double[] s_xPoints = {blocks[i].x_pos + margin, blocks[i].x_pos + 28.0,  blocks[i].x_pos + 25, blocks[i].x_pos + 5};
            double[] s_yPoints = {blocks[i].y_pos + 28, blocks[i].y_pos + 28, blocks[i].y_pos + 25, blocks[i].y_pos + 25};
            gc.fillPolygon(s_xPoints, s_yPoints,4);

            gc.setFill(shadow.brighter());
            double[] d1_xPoints = {blocks[i].x_pos + margin, blocks[i].x_pos + margin, blocks[i].x_pos + 5, blocks[i].x_pos + 5};
            double[] d1_yPoints = {blocks[i].y_pos + margin, blocks[i].y_pos + 28, blocks[i].y_pos + 25, blocks[i].y_pos + 5};
            double[] d2_xPoints = {blocks[i].x_pos + 28, blocks[i].x_pos + 28, blocks[i].x_pos + 25, blocks[i].x_pos + 25};
            double[] d2_yPoints = {blocks[i].y_pos + margin, blocks[i].y_pos + 28, blocks[i].y_pos + 25, blocks[i].y_pos + 5};
            gc.fillPolygon(d1_xPoints, d1_yPoints,4);
            gc.fillPolygon(d2_xPoints, d2_yPoints,4);

        }

    }


    public void setPosition(int x_pos, int y_pos){}
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}


    public void updateDirection(int direction){
        
        rotationCollision();

        if(leftCollision == false && rightCollision == false && bottomCollision == false){
            //note that all minos rotate clockwise
            this.direction = direction;

            blocks[0].x_pos = temp_blocks[0].x_pos;
            blocks[0].y_pos = temp_blocks[0].y_pos;
    
            blocks[1].x_pos = temp_blocks[1].x_pos;
            blocks[1].y_pos = temp_blocks[1].y_pos;
    
            blocks[2].x_pos = temp_blocks[2].x_pos;
            blocks[2].y_pos = temp_blocks[2].y_pos;
    
            blocks[3].x_pos = temp_blocks[3].x_pos;
            blocks[3].y_pos = temp_blocks[3].y_pos;
        }
    }


    public void translationCollision(){

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        inactiveCollision();

        for(int i = 0; i < blocks.length; i++){
            if(blocks[i].x_pos == TetrisScene.p_left_x){
                leftCollision = true;
            }
        }

        for(int i = 0; i < blocks.length; i++){
            if(blocks[i].x_pos + Block.M_SIZE == TetrisScene.p_right_x){
                rightCollision = true;
            }
        }

        for(int i = 0; i < blocks.length; i++){
            if(blocks[i].y_pos + Block.M_SIZE == TetrisScene.p_bottom_y){
                bottomCollision = true;
            }
        }
    }


    public void rotationCollision(){

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        inactiveCollision();

        for(int i = 0; i < temp_blocks.length; i++){
            if(temp_blocks[i].x_pos < TetrisScene.p_left_x){
                leftCollision = true;
            }
        }

        for(int i = 0; i < temp_blocks.length; i++){
            if(temp_blocks[i].x_pos + Block.M_SIZE > TetrisScene.p_right_x){
                rightCollision = true;
            }
        }

        for(int i = 0; i < temp_blocks.length; i++){
            if(temp_blocks[i].y_pos + Block.M_SIZE > TetrisScene.p_bottom_y){
                bottomCollision = true;
            }
        }
    }


    public void inactiveCollision(){
        //Check each deactivated block against each current block
        for(int i = 0; i < TetrisScene.inactiveBlocks.size(); i++){
            int inactive_x = TetrisScene.inactiveBlocks.get(i).x_pos;
            int inactive_y = TetrisScene.inactiveBlocks.get(i).y_pos;
            //Check if future collision would occur
            for(int j = 0; j < blocks.length; j++){
                //Bottom collision
                if(blocks[j].x_pos == inactive_x && blocks[j].y_pos + Block.M_SIZE == inactive_y){
                    bottomCollision = true;
                }
                //Left collision
                if(blocks[j].x_pos - Block.M_SIZE  == inactive_x && blocks[j].y_pos == inactive_y){
                    leftCollision = true;
                }
                //Right collision
                if(blocks[j].x_pos + Block.M_SIZE == inactive_x && blocks[j].y_pos == inactive_y){
                    rightCollision = true;
                }
            }
        }
    }

    public void deactivate(){ //Allows for 0.75s of sliding during a bottom collision before deactivating the mino
        deactivationTimer++;

        if(deactivationTimer == 45){
            deactivationTimer = 0;
            translationCollision();
            
            if(bottomCollision){
                active = false;
            }
        }
    }


    public void update(){

        if(deactivating){
            deactivate(); //begin deactivation
        }

        translationCollision();

        //Handling movement inputs
        if(KeyHandler.leftPressed){
            if(leftCollision == false){
                blocks[0].x_pos -= Block.M_SIZE;
                blocks[1].x_pos -= Block.M_SIZE;
                blocks[2].x_pos -= Block.M_SIZE;
                blocks[3].x_pos -= Block.M_SIZE;
            }
            KeyHandler.leftPressed = false;
        }

        if(KeyHandler.rightPressed){
            if(rightCollision == false){
                blocks[0].x_pos += Block.M_SIZE;
                blocks[1].x_pos += Block.M_SIZE;
                blocks[2].x_pos += Block.M_SIZE;
                blocks[3].x_pos += Block.M_SIZE;
            }
            KeyHandler.rightPressed = false;
        }

        if(KeyHandler.downPressed){
            if(bottomCollision == false){
                blocks[0].y_pos += Block.M_SIZE;
                blocks[1].y_pos += Block.M_SIZE;
                blocks[2].y_pos += Block.M_SIZE;
                blocks[3].y_pos += Block.M_SIZE;

                dropTimer = 0;
            }
            KeyHandler.downPressed = false;
        }

        if(KeyHandler.upPressed){
            switch(direction){
                case 1: getDirection2();break;
                case 2: getDirection3();break;
                case 3: getDirection4();break;
                case 4: getDirection1();break;
            }
            KeyHandler.upPressed = false;
        }

        //Mino dropping logic 
        if(bottomCollision){
            deactivating = true; 
        }
        else{
            dropTimer++;
            if(dropTimer == TetrisScene.dropSpeed){
                translationCollision();
                blocks[0].y_pos += Block.M_SIZE;
                blocks[1].y_pos += Block.M_SIZE;
                blocks[2].y_pos += Block.M_SIZE;
                blocks[3].y_pos += Block.M_SIZE;
    
                dropTimer = 0;
            }
        }
    }
}
