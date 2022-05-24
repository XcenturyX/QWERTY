package com.example.MMask;

import javafx.scene.image.Image;

public class Mask {
    private Image[] mask;
    public Mask(){
        mask=new Image[4];
        mask[0]=new Image("src/main/resources/com/example/coursework/useFoto/MMask/Fan.png");
        mask[1]=new Image("src/main/resources/com/example/coursework/useFoto/MMask/Net.png");
        mask[2]=new Image("src/main/resources/com/example/coursework/useFoto/MMask/Qw.png");
        mask[3]=new Image("src/main/resources/com/example/coursework/useFoto/MMask/Xexe.png");
    }

    public Image getMask(int i) {
        return mask[i];
    }
}
