package com.example.ibuff;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.Stack;

public class Buffer {
    private Stack<WritableImage> BufferImige;
    public Buffer(){
        BufferImige=new Stack<>();
    }
    public WritableImage ReturnImeginn(){
        return BufferImige.pop();
    }
    public WritableImage ReturnNotDelet(){return  BufferImige.peek();}
    public void PutImeginn(WritableImage image){
        BufferImige.push(image);
    }
    public boolean isNullImageStack(){
        if(BufferImige.size()!=1&&BufferImige.size()!=0) return true;
        else return false;
    }
}
