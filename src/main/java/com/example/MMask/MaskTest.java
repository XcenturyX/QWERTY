package com.example.MMask;


import org.junit.Assert;
import org.junit.Test;

/**
 * Класс Junit Test для класса Mask.
 */
public class MaskTest {
    /**
     * Функция для тестирования метода getMask.
     */
    @Test
    public void getMaskTest(){
        Mask mask=new Mask();
        Assert.assertTrue(mask.getMask(0)!=null);
    }
}
