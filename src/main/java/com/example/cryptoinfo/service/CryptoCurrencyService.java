package com.example.cryptoinfo.service;

import com.example.cryptoinfo.model.dto.CurrencyDto;
import com.example.cryptoinfo.model.dto.response.CurrencyResponseDto;
import com.example.cryptoinfo.model.entity.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyService {
    List<CurrencyResponseDto> showAvailableCryptoCurrencies();
    CryptoCurrency showInfoAboutChosenCurrency(String symbol);
    List<CryptoCurrency> getInfoAboutCurrencies(List<CurrencyDto> currencyDtoList);

    List<CryptoCurrency> saveAllCurrencies(List<CurrencyDto> currencyInfoDtoList);
    void warnCurrencyChanged();
    CryptoCurrency findBySymbol(String symbol);
}
