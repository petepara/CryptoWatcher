package com.example.cryptoinfo.mapper;

import com.example.cryptoinfo.model.dto.CurrencyDto;
import com.example.cryptoinfo.model.entity.CryptoCurrency;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyMapper {

    public CryptoCurrency mapToDto(CurrencyDto cryptoCurrency){
        return CryptoCurrency.builder()
                .currencySymbol(cryptoCurrency.getSymbol())
                .currencyExchangeRate(cryptoCurrency.getPrice_usd())
                .build();
    }
}
