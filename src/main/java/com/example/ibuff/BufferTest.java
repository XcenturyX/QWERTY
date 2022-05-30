package com.example.ibuff;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс Junit Test для класса Buffer.
 */
public class BufferTest {
    /**
     * Функция для тестирования метода getSize.
     */
    @Test
    public void getSizeTest(){
        Buffer buffer=new Buffer();
        Assert.assertFalse(buffer.getSize()<0);
    }
}
