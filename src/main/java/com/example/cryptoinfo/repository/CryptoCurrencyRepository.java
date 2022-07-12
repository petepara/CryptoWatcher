package com.example.cryptoinfo.repository;

import com.example.cryptoinfo.model.entity.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Integer> {
    CryptoCurrency findByCurrencySymbol(String symbol);
}
