package com.service.wallet.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDto {

    private String originPhone;

    private String destinPhone;

    private Double amount;
}
