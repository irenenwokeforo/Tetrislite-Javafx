package com.tetrislite.UI;
import java.util.Random;

import com.tetrislite.Tetriminos.*;

public class MinoGenerator{
    
    public static Mino pickMino2(){
        Mino mino = null;
        int minoValue = new Random().nextInt(7);

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
}