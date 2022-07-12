package com.example.cryptoinfo.service.impl;

import com.example.cryptoinfo.model.entity.CryptoCurrency;
import com.example.cryptoinfo.model.entity.User;
import com.example.cryptoinfo.model.entity.UserCurrency;
import com.example.cryptoinfo.repository.UserCurrencyRepository;
import com.example.cryptoinfo.repository.UserRepository;
import com.example.cryptoinfo.service.CryptoCurrencyService;
import com.example.cryptoinfo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCurrencyRepository userCurrencyRepository;
    private final CryptoCurrencyService cryptoCurrencyService;

    @Override
    public boolean registerUser(String username, String symbol) {
        if(userCurrencyRepository.findByUsernameAndCryptoCurrencySymbol(username, symbol).isPresent()){
            return false;
        }
        User user = userRepository.save(User.builder()
                .username(username)
                .isNotified(true)
                .build());
        userCurrencyRepository.save(UserCurrency.builder()
                        .user(user)
                        .cryptoCurrency(cryptoCurrencyService.findBySymbol(symbol))
                        .savedValue(cryptoCurrencyService.findBySymbol(symbol).getCurrencyExchangeRate())
                .build());
        return true;
    }
}
