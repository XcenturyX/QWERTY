package com.example.MMask;

import javafx.scene.image.Image;

/**
 * Класс, который хранит в себе массив с масками.
 */
public class Mask {
    private Image[] mask;

    /**
     * Конструктор без параметров, который создает и инициализирует массив с масками.
     */
    public Mask(){
        mask=new Image[4];
        mask[0]=new Image(getClass().getResourceAsStream("Fan.png"));
        mask[1]=new Image(getClass().getResourceAsStream("Net.png"));
        mask[2]=new Image(getClass().getResourceAsStream("Qw.png"));
        mask[3]=new Image(getClass().getResourceAsStream("Xexe.png"));
    }

    /**
     * Геттер, возвращает эллемент массива.
     * @param i - номер эллемента массива.
     * @return - эллемент массива.
     */
    public Image getMask(int i) {
        return mask[i];
    }
}
