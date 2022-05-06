package com.ibm.restapibancocentral.util;

import com.ibm.restapibancocentral.entities.DadosDividaLiquida;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DividaLiquidaBCCreator {

    public static DadosDividaLiquida createDividaToBeSaved(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DadosDividaLiquida.builder()
                .valor(0.27)
                .data(date)
                .build();
    }

    public static DadosDividaLiquida createValidDivida(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DadosDividaLiquida.builder()
                .id(1L)
                .data(date)
                .valor(0.15)
                .build();
    }

    public static DadosDividaLiquida createValidUpdatedDivida(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DadosDividaLiquida.builder()
                .id(1L)
                .data(date)
                .valor(0.70)
                .build();
    }
}
