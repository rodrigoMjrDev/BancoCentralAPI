package com.ibm.restapibancocentral.util;

import com.ibm.restapibancocentral.domain.DivLiqURL;
import com.ibm.restapibancocentral.domain.DividaLiquidaBC;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DivLiqURLCreator {

    public static DivLiqURL createDivLiqURLToBeSaved(){

        return DivLiqURL.builder()
                .div("https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json")
                .build();
    }
}
