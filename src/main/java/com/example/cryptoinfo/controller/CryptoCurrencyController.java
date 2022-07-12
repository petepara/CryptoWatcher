package com.example.cryptoinfo.controller;

import com.example.cryptoinfo.model.dto.CurrencyDto;
import com.example.cryptoinfo.model.dto.request.SignUpRequestDto;
import com.example.cryptoinfo.model.dto.response.CurrenciesResponseDto;
import com.example.cryptoinfo.model.entity.CryptoCurrency;
import com.example.cryptoinfo.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
@Slf4j
public class CryptoCurrencyController {
    private final RestTemplate restTemplate;
    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/show")
    @Scheduled(fixedRate = 60000)
    public void updateCurrenciesRate() {
        String currenciesUrl = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        ResponseEntity<List<CurrencyDto>> response
                = restTemplate.exchange(currenciesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CurrencyDto> currencyDtos = response.getBody();
        List<CryptoCurrency> currencies = cryptoCurrencyService.saveAllCurrencies(currencyDtos);
        cryptoCurrencyService.warnCurrencyChanged();
    }

    @GetMapping
    public ResponseEntity<CurrenciesResponseDto> showAvailableCurrencies() {

        return ResponseEntity.ok().body(new CurrenciesResponseDto(cryptoCurrencyService.showAvailableCryptoCurrencies()));
    }

    @GetMapping("/")
    public ResponseEntity<?> showCurrencyInfo(@RequestParam String currencyCode) {
        return ResponseEntity.ok().body(cryptoCurrencyService.showInfoAboutChosenCurrency(currencyCode));
    }



}
