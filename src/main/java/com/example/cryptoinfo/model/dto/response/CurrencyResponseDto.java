package com.example.cryptoinfo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CurrencyResponseDto {
    private String currencySymbol;
}
