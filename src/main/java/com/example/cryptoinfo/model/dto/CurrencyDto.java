package com.example.cryptoinfo.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    private int id;
    private String symbol;
    private String name;
    private String nameid;
    private int rank;
    private BigDecimal price_usd;
    private BigDecimal percent_change_24h;
    private BigDecimal percent_change_1h;
    private BigDecimal percent_change_7d;
    private BigDecimal market_cap_uUsd;
    private BigDecimal volume24;
    private BigDecimal volume24_native;
    private BigDecimal csupply;
    private BigDecimal price_btc;
    private BigDecimal tsupply;
    private BigDecimal msupply;
}
