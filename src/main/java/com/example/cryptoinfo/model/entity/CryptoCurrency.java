package com.example.cryptoinfo.model.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "crypto_currencies")
public class CryptoCurrency {
    @Id
    @ToString.Exclude
    private int id;
    @Column(name = "symbol")
    private String currencySymbol;
    @Column(name = "exchange_rate")
    private BigDecimal currencyExchangeRate;
}
