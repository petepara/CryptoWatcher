package com.example.cryptoinfo.repository;

import com.example.cryptoinfo.model.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {

        @Query("SELECT uc FROM UserCurrency uc JOIN CryptoCurrency cc on uc.cryptoCurrency.id = cc.id " +
                "WHERE uc.user.username=:username and cc.currencySymbol=:currencySymbol")
        Optional<UserCurrency> findByUsernameAndCryptoCurrencySymbol(String username, String currencySymbol);
}
