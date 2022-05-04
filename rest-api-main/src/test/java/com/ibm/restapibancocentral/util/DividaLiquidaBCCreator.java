package com.ibm.restapibancocentral.util;

import com.ibm.restapibancocentral.domain.DividaLiquidaBC;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DividaLiquidaBCCreator {

    public static DividaLiquidaBC createDividaToBeSaved(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DividaLiquidaBC.builder()
                .valor(0.27)
                .data(date)
                .build();
    }

    public static DividaLiquidaBC createValidDivida(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DividaLiquidaBC.builder()
                .id(1L)
                .data(date)
                .valor(0.15)
                .build();
    }

    public static DividaLiquidaBC createValidUpdatedDivida(){

        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        return DividaLiquidaBC.builder()
                .id(1L)
                .data(date)
                .valor(0.70)
                .build();
    }
}
