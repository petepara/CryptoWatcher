package com.example.cryptoinfo.model.dto.response;

import com.example.cryptoinfo.model.entity.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CurrenciesResponseDto {
    List<CurrencyResponseDto> availableCurrenciesList;
}
