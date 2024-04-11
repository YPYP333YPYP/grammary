package com.example.gachon.domain.user;

import com.example.gachon.domain.lmage.Images;
import com.example.gachon.domain.history.Histories;
import com.example.gachon.domain.history.HistoriesRepository;
import com.example.gachon.domain.lmage.ImagesRepository;
import com.example.gachon.domain.sentence.Sentences;
import com.example.gachon.domain.sentence.SentencesRepository;
import com.example.gachon.domain.user.dto.request.UserRequestDto;
import com.example.gachon.domain.user.dto.response.UserResponseDto;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.GeneralHandler;
import com.example.gachon.global.response.exception.handler.ImagesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import com.example.gachon.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.gachon.global.security.UserDetailsServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UserDetailsServiceImpl userDetailsService;
    private final UsersRepository usersRepository;
    private final HistoriesRepository historiesRepository;
    private final SentencesRepository sentencesRepository;
    private final ImagesRepository imagesRepository;

    public Long getUserId(UserDetails user){
        String username = user.getUsername();
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails.getUserId();
    }

    public UserResponseDto.UserInfoDto getUserInfo(String email){

        return UsersConverter.toUserInfoDto(usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND)));
    }

    public UserResponseDto.UserHistoryListDto getUserHistory(String email){
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        List<Histories> histories = historiesRepository.findAllByUser(user);

        List<Sentences> sentences = histories.stream()
                .map(history -> sentencesRepository.findById(history.getSentence().getId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();

        return UsersConverter.toUserHistoryListDto(sentences, user.getId());
    }

    @Transactional
    public void updateUserInfo(Long userId, UserRequestDto.UserInfoDto userInfoDto ) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        user.setPhoneNum(userInfoDto.getPhoneNum());
        user.setSocial(userInfoDto.getSocial());

        usersRepository.save(user);
    }

    @Transactional
    public void updateProfileImage(Long userId, UserRequestDto.UserProfileImageDto userProfileImageDto) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        Images image = imagesRepository.findById(userProfileImageDto.getImageId()).orElseThrow(() -> new ImagesHandler(ErrorStatus.IMAGE_NOT_PROVIDED));

        user.setProfileUrl(image.getUrl());
        usersRepository.save(user);

    }

    @Transactional
    public void updateUserNickname(Long userId, UserRequestDto.UserNicknameDto userNicknameDto) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        user.setNickname(userNicknameDto.getNickname());
        usersRepository.save(user);

    }

    @Transactional
    public void quitUser(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        user.setStatus(Status.DISABLED);
        usersRepository.save(user);
    }

    public UserResponseDto.UserInfoDto getUserInfoByAdmin(String email, Long userId) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
            return UsersConverter.toUserInfoDto(user);
        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

    }
}
