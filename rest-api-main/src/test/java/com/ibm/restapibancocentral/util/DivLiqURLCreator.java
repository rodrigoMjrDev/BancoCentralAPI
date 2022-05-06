package com.ibm.restapibancocentral.util;

import com.ibm.restapibancocentral.entities.URLDiv;


public class DivLiqURLCreator {

    public static URLDiv createDivLiqURLToBeSaved(){

        return URLDiv.builder()
                .div("https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json")
                .build();
    }
}
