package com.example.ifiltrs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс Junit Test для класса MFilter.
 */
public class MFilterTest {
    /**
     * Функция для тестирования метода getArrFiltrs.
     */
    @Test
    public void getArrFiltrsTest(){
        MFilter mFilter=new MFilter();
        Assert.assertTrue(mFilter.getArrFiltrs()!=null);
    }
}
