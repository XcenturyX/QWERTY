package com.example.logic;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


/**
 * Класс логики, в который вынесены функции обработки изображения
 */
public class Logica {
    /**
     * Функция, которая устанавливает координаты у холста
     * @param canvas - холст
     * @param x - координата Х
     * @param y - координата У
     */
    public void moveCanvas(Canvas canvas, int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }

    /**
     * Функция, которая возвращает изображение с холста
     * @param canvas - холст
     * @return - картинка
     */
    public WritableImage getWritableImageFromCanvas(Canvas canvas){
        WritableImage writableImage=new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        SnapshotParameters spa = new SnapshotParameters();
        return canvas.snapshot(spa, writableImage);
    }

    /**
     * Функция, которая изменяет значение rgb у каждого пикселя изображения
     * @param color - алгоритм изменения rgb
     * @param image - входное изображение
     * @param temp - величина, на которую будет увеличиваться значение rgb
     * @return - измененное изображение
     */
    public WritableImage customColor(String color, WritableImage image,double temp){
        int width=(int)(image.getWidth());
        int height=(int) (image.getHeight());
        PixelWriter writer=image.getPixelWriter();
        PixelReader reader=image.getPixelReader();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color lcolor = reader.getColor(x, y);
                double red=lcolor.getRed();
                double green=lcolor.getGreen();
                double blue=lcolor.getBlue();
                if(color=="Red") {
                    if (red + temp < 1) red += temp;
                    else red = 1.0;
                    if (red + temp < 0) red = 0;
                }
                if(color=="Blue"){
                    if (blue + temp < 1) blue += temp;
                    else red = 1.0;
                    if (blue + temp < 0) blue = 0;
                }
                if(color=="Green"){
                    if (green + temp < 1) green += temp;
                    else red = 1.0;
                    if (green + temp < 0) green = 0;
                }
                if(color=="Light"){
                    if(red+temp<1) red+=temp; else red=1.0;
                    if(red+temp<0) red=0;
                    if(green+temp<1) green+=temp; else green=1.0;
                    if(green+temp<0) green=0;
                    if(blue+temp<1) blue+=temp; else blue=1.0;
                    if(blue+temp<0) blue=0;
                }
                if(color=="Sut"){
                    if(red>green){
                        if(red>blue){
                            if(red+temp<1) red+=temp; else red=1.0;
                            if(red+temp<0) red=0;
                        }
                        else {
                            if(blue+temp<1) blue+=temp; else blue=1.0;
                            if(blue+temp<0) blue=0;
                        }
                    }
                    else {
                        if(green>blue){
                            if(green+temp<1) green+=temp; else green=1.0;
                            if(green+temp<0) green=0;
                        }
                        else {
                            if(blue+temp<1) blue+=temp; else blue=1.0;
                            if(blue+temp<0) blue=0;
                        }
                    }
                }
                if(color=="Warm"){
                    if(temp<0){
                        if(blue+temp<1) blue+=temp; else blue=1.0;
                        if(blue+temp<0) blue=0;
                    }
                    else {
                        if(red-temp<1) red-=temp; else red=1.0;
                        if(red-temp<0) red=0;
                    }
                }
                Color nColor=new Color(red, green, blue,1.0);
                writer.setColor(x, y, nColor);
            }
        }
        return image;

    }

    /**
     * Функция, которая заполняет указанную область цветом
     * @param pixelWriter - объект PixelWriter
     * @param x - координата Х области
     * @param y - координата У области
     * @param c - размер области
     * @param color - цвет области
     */
    public void drow(PixelWriter pixelWriter, int x, int y, int c,Color color){
        for(int i=0;i<c;i++){
            for(int j=0;j<c;j++){
                if(x-(c/2)+j>0&&y-(c/2)+i>0) {
                    pixelWriter.setColor(x - (c / 2) + j, y - (c / 2) + i, color);
                }
            }
        }
    }

}
