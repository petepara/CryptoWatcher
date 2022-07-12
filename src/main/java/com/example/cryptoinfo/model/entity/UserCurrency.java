package com.example.cryptoinfo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saved_users_currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @OneToOne
    @JoinColumn(name = "currency_id")
    private CryptoCurrency cryptoCurrency;
    @Column(name = "saved_value")
    private BigDecimal savedValue;
}
