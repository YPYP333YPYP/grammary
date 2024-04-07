package com.example.gachon.domain.token;

import com.example.gachon.domain.token.dto.response.RegenerateTokenResponseDto;

public class RefreshTokenConverter {
    public static RegenerateTokenResponseDto toRegenerateTokenDto(String token){
        return RegenerateTokenResponseDto.builder()
                .accessToken(token)
                .build();
    }
}