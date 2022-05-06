package com.ibm.restapibancocentral.util;

import com.ibm.restapibancocentral.entities.DivLiqURL;


public class DivLiqURLCreator {

    public static DivLiqURL createDivLiqURLToBeSaved(){

        return DivLiqURL.builder()
                .div("https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json")
                .build();
    }
}
