package com.example.cryptoinfo.service.impl;

import com.example.cryptoinfo.mapper.CryptoCurrencyMapper;
import com.example.cryptoinfo.model.dto.CurrencyDto;
import com.example.cryptoinfo.model.dto.response.CurrencyResponseDto;
import com.example.cryptoinfo.model.entity.CryptoCurrency;
import com.example.cryptoinfo.model.entity.UserCurrency;
import com.example.cryptoinfo.repository.CryptoCurrencyRepository;
import com.example.cryptoinfo.repository.UserCurrencyRepository;
import com.example.cryptoinfo.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository currencyRepository;
    private final UserCurrencyRepository userCurrencyRepository;
    private final CryptoCurrencyMapper currencyMapper;

    @Override
    public List<CurrencyResponseDto> showAvailableCryptoCurrencies() {
        return currencyRepository.findAll()
                .stream()
                .map(cryptoCurrency -> new CurrencyResponseDto(cryptoCurrency.getCurrencySymbol()))
                .collect(Collectors.toList());
    }

    @Override
    public CryptoCurrency showInfoAboutChosenCurrency(String symbol) {
        return currencyRepository.findByCurrencySymbol(symbol);
    }

    @Override
    @Transactional
    public List<CryptoCurrency> getInfoAboutCurrencies(List<CurrencyDto> currencyDtoList) {
        return currencyDtoList.stream().map(currencyMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CryptoCurrency> saveAllCurrencies(List<CurrencyDto> currencyList) {
        List<CryptoCurrency> currencies = currencyList.stream()
                .map(currencyDto -> currencyRepository.findById(currencyDto.getId()).get())
                .collect(Collectors.toList());
        currencies.forEach(cryptoCurrency -> currencyList.stream()
                .filter(currencyDto -> currencyDto.getId() == cryptoCurrency.getId())
                .forEach(currencyDto -> cryptoCurrency.setCurrencyExchangeRate(currencyDto.getPrice_usd())));
        return currencyRepository.saveAll(currencies);
    }

    @Override
    @Transactional
    public void warnCurrencyChanged() {
        int upperBound = 101;
        int lowerBound = 99;
        int hundredPercent = 100;
        List<UserCurrency> userCurrencies = userCurrencyRepository.findAll();
        List<UserCurrency> userCurrenciesToLog = userCurrencies.stream()
                .filter(userCurrency1 -> findDifferenceBetweenSavedValueAndNew(userCurrency1.getSavedValue(), userCurrency1.getCryptoCurrency().getCurrencyExchangeRate()) > upperBound ||
                        findDifferenceBetweenSavedValueAndNew(userCurrency1.getSavedValue(), userCurrency1.getCryptoCurrency().getCurrencyExchangeRate()) < lowerBound)
                .collect(Collectors.toList());

        userCurrenciesToLog
                .forEach(userCurrency1 -> log.warn(userCurrency1.getCryptoCurrency().getCurrencySymbol() + ", "
                        + userCurrency1.getUser().getUsername() + ", changing: " + ((findDifferenceBetweenSavedValueAndNew(userCurrency1.getSavedValue(), userCurrency1.getCryptoCurrency().getCurrencyExchangeRate()) - hundredPercent) + "%")));
    }

    private double findDifferenceBetweenSavedValueAndNew(BigDecimal value1, BigDecimal value2) {
        return value1.divide(value2, 3, RoundingMode.HALF_UP).doubleValue() * 100;
    }

    @Override
    public CryptoCurrency findBySymbol(String symbol) {
        return currencyRepository.findByCurrencySymbol(symbol);
    }
}
