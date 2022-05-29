package com.example.MMask;

import com.example.coursework.MainController;
import javafx.scene.image.Image;

public class Mask {
    private Image[] mask;
    public Mask(){
        mask=new Image[4];
        mask[0]=new Image(getClass().getResourceAsStream("Fan.png"));
        mask[1]=new Image(getClass().getResourceAsStream("Net.png"));
        mask[2]=new Image(getClass().getResourceAsStream("Qw.png"));
        mask[3]=new Image(getClass().getResourceAsStream("Xexe.png"));
    }

    public Image getMask(int i) {
        return mask[i];
    }
}
