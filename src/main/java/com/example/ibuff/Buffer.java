package com.example.ibuff;

import javafx.scene.image.WritableImage;

import java.util.Stack;

/**
 * Класс, который хранит в себе буффер изображений.
 */
public class Buffer {
    private Stack<WritableImage> BufferImige;

    /**
     * Конструктор, который инциализирует Stack.
     */
    public Buffer(){
        BufferImige=new Stack<>();
    }

    /**
     * Функция, которая возвращает последнее изображение и удаляет его из Stack.
     * @return - последнее изображение.
     */
    public WritableImage ReturnImeginn(){
        return BufferImige.pop();
    }

    /**
     * Функция, которая возвращает последнее изображение, но не удаляет его из Stack.
     * @return - последнее изображение.
     */
    public WritableImage ReturnNotDelet(){return  BufferImige.peek();}

    /**
     * Функция, которая помещает изображение в буффер.
     * @param image - входное изображение.
     */
    public void PutImeginn(WritableImage image){
        BufferImige.push(new WritableImage(image.getPixelReader(),(int)image.getWidth(),(int)image.getHeight()));
    }

    /**
     * Функция, которая проверяет пуст ли Stack.
     * @return - true - если пуст, false - в противном случае.
     */
    public boolean isNullImageStack(){
        return BufferImige.empty();

    }

    /**
     * Функция, которая удаляет изображение с верхушки Stack.
     */
    public void delete(){
        BufferImige.remove(BufferImige.size()-1);
    }

    /**
     * Функция, которая возвращает размер Stack.
     * @return - размер Stack.
     */
    public int getSize(){
        return BufferImige.size();
    }

    /**
     * Функция, которая чистит Stack.
     */
    public void clean(){
        BufferImige.clear();
    }
}
