package com.ibm.restapibancocentral.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class DivLiqURL {

    private String div;
}
