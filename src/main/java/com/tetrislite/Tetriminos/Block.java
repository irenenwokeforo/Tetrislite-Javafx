package com.tetrislite.Tetriminos;
import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle{

    public int x_pos;
    public int y_pos;
    int margin;
    public final static int M_SIZE = 30;

    //Block Rendering
    Color colour;
    Color highlight;
    Color shadow;
    
    public Block(Color c){
        this.colour = c;

    }

    public Color getShadow(Color c){

        if(c.equals(Color.RED)){
            
            shadow = c.deriveColor(
                -10,
                1.05,
                0.60,
                1.0
            );
        }

        else if(c.equals(Color.ORANGE)){ 

            shadow = c.deriveColor(
                -8,
                1.1,
                0.65,
                1.0
            );
        }

        else if(c.equals(Color.GOLD)){

            shadow = c.deriveColor(
                10,
                1.1,
                0.60,
                1.0
            );
        }

        else if(c.equals(Color.LIME)){

            shadow = c.deriveColor(
                -10,
                1.1,
                0.60,
                1.0
            );
        }

        else if(c.equals(Color.CYAN)){

            shadow = c.deriveColor(
                10,
                1.05,
                0.65,
                1.0
            );
        } 

        else if(c.equals(Color.BLUE)){
            shadow = c.deriveColor(
                -5,
                1.05,
                0.55,
                1.0
            );
        }

        else{
            shadow = c.deriveColor(
                -8,
                1.05,
                0.60,
                1.0
            );
        }

        return shadow;
    }

    public void render(GraphicsContext gc){
        Color base = this.colour;
        Color highlight = Color.WHITE.deriveColor(1, 1, 1, 0.7);
        Color shadow = this.getShadow(base);

        margin = 2;
        
        double[] h_xPoints = {x_pos + margin, x_pos + 28.0,  x_pos + 25, x_pos + 5};
        double[] h_yPoints = {y_pos + margin, y_pos + margin, y_pos + 5, y_pos + 5};
    
        double[] s_xPoints = {x_pos + margin, x_pos + 28.0,  x_pos + 25, x_pos + 5};
        double[] s_yPoints = {y_pos + 28, y_pos + 28, y_pos + 25, y_pos + 25};
    
        double[] d1_xPoints = {x_pos + margin, x_pos + margin, x_pos + 5, x_pos + 5};
        double[] d1_yPoints = {y_pos + margin, y_pos + 28, y_pos + 25, y_pos + 5};
        double[] d2_xPoints = {x_pos + 28, x_pos + 28, x_pos + 25, x_pos + 25};
        double[] d2_yPoints = {y_pos + margin, y_pos + 28, y_pos + 25, y_pos + 5};

        gc.setFill(base);
        gc.fillRect(x_pos + margin, y_pos + margin, M_SIZE - (margin * 2), M_SIZE - (margin * 2));

        gc.setFill(highlight);
        gc.fillPolygon(h_xPoints, h_yPoints,4);

        gc.setFill(shadow);
        gc.fillPolygon(s_xPoints, s_yPoints,4);

        gc.setFill(shadow.brighter());
        gc.fillPolygon(d1_xPoints, d1_yPoints,4);
        gc.fillPolygon(d2_xPoints, d2_yPoints,4);
    }
}

