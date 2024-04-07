package com.example.gachon.domain.user;

import com.example.gachon.domain.user.dto.response.UserResponseDto;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import com.example.gachon.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.gachon.global.security.UserDetailsImpl;
import com.example.gachon.global.security.UserDetailsServiceImpl;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UserDetailsServiceImpl userDetailsService;
    private final UsersRepository usersRepository;

    public Long getUserId(UserDetails user){
        String username = user.getUsername();
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails.getUserId();
    }

    public UserResponseDto.UserInfoDto getUserInfo(String email){

        return UsersConverter.toUserInfoDto(usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND)));
    }
}
