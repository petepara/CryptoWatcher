package com.example.cryptoinfo.model.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String symbol;
}
