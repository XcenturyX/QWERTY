package com.example.ifiltrs;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Класс, который содержит массив фильтров и алгоритмы обработки изображения для этих фильтров.
 */
public class MFilter {
    private final String[] arrFiltrs={"Запад","Пальма","Луг","Румянец","Кинолента","Черно-Белый","Негатив"};
    private HashMap<String,Double[]> fil;

    /**
     * Конструктор без параметров, который заполняет HashMap.
     */
    public MFilter(){
        fil=new HashMap<>();
        fil.put(arrFiltrs[0],new Double[]{1.0,1.0,1.1});
        fil.put(arrFiltrs[1],new Double[]{1.1,1.0,1.0});
        fil.put(arrFiltrs[2],new Double[]{1.0,1.1,1.0});
        fil.put(arrFiltrs[3],new Double[]{1.25,1.0,1.1});
        fil.put(arrFiltrs[4],new Double[]{0.95,0.95,1.25});
        fil.put(arrFiltrs[5],new Double[]{0.0,0.0,0.0});
        fil.put(arrFiltrs[6],new Double[]{0.0,0.0,0.0});
    }

    /**
     * Функция, которая обрабатывает изображение по заданному фильтру. Накладывает фильтры "Негатив" или "Черно-Белый"
     * для наложения других фильтров передает входное изображение в другую функцию.
     * @param s - название фильтра.
     * @param image - входное изображение.
     * @return - изображение с фильтром.
     */
    public WritableImage setFiltrs(String s, WritableImage image){
        if(!s.equals("Негатив")&&!s.equals("Черно-Белый"))
            return filtrsMetod(image,fil.get(s));
        if(s.equals("Черно-Белый")){
            int width = (int) (image.getWidth());
            int height = (int) (image.getHeight());
            PixelWriter writer = image.getPixelWriter();
            PixelReader reader = image.getPixelReader();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = reader.getColor(x, y);
                    double red = color.getRed();
                    double green = color.getGreen();
                    double blue = color.getBlue();
                    double grey = red * 0.299 + green * 0.587 + blue * 0.114;
                    Color nColor = new Color(grey, grey, grey, 1.0);
                    writer.setColor(x, y, nColor);
                }
            }
            return image;
        }
        if(s.equals("Негатив")){

            int width = (int) (image.getWidth());
            int height = (int) (image.getHeight());
            PixelWriter writer = image.getPixelWriter();
            PixelReader reader = image.getPixelReader();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = reader.getColor(x, y);
                    double red = 1-color.getRed();
                    double green = 1-color.getGreen();
                    double blue = 1-color.getBlue();
                    Color nColor = new Color(red, green, blue, 1.0);
                    writer.setColor(x, y, nColor);
                }
            }
            return image;
        }

        return null;
    }

    /**
     * Функция, накладывает на изображение фильтры, кроме "Негатив" или "Черно-Белый".
     * @param image - входное изображение.
     * @param d - массив необходимый для алгоритма наложения фильтра.
     * @return - изображение с фильтром.
     */
    public WritableImage filtrsMetod(WritableImage image, Double[] d) {
        int width = (int) (image.getWidth());
        int height = (int) (image.getHeight());
        PixelWriter writer = image.getPixelWriter();
        PixelReader reader = image.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                double red = color.getRed()*d[0];
                double green = color.getGreen()*d[1];
                double blue = color.getBlue()*d[2];
                if(red>1){
                    red=1;
                }
                if (red<0){
                    red=0;
                }
                if(green>1){
                    green=1;
                }
                if (green<0){
                    green=0;
                }
                if(blue>1){
                    blue=1;
                }
                if (blue<0){
                    blue=0;
                }
                Color nColor = new Color(red, green, blue, 1.0);
                writer.setColor(x, y, nColor);
            }
        }
        return image;
    }

    /**
     * Геттер для массива с названиями фильтров.
     * @return - массив названия масок.
     */
    public String[] getArrFiltrs(){
        return arrFiltrs;
    }
}
