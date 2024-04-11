package com.example.gachon.domain.notice;

import com.example.gachon.domain.history.Histories;
import com.example.gachon.domain.notice.dto.response.NoticeResponseDto;
import com.example.gachon.domain.sentence.Sentences;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.GeneralHandler;
import com.example.gachon.global.response.exception.handler.NoticesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticesService {

    private final NoticesRepository noticesRepository;
    private final UsersRepository usersRepository;

    NoticeResponseDto.NoticeInfoDto getNoticeInfo(Long noticeId) {
        return NoticesConverter.toNoticeInfoDto(noticesRepository.findById(noticeId).orElseThrow(() -> new NoticesHandler(ErrorStatus.NOTICE_NOT_FOUND)));
    }

    List<NoticeResponseDto.NoticePreviewDto> getNoticePreviewList() {
        List<Notices> notices = noticesRepository.findAll();

        return notices.stream()
                .map(NoticesConverter::toNoticePreviewDto)
                .collect(Collectors.toList());
    }

    public NoticeResponseDto.NoticeInfoDto getNoticeInfoByAdmin(String email, Long noticeId) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Notices notice = noticesRepository.findById(noticeId).orElseThrow(()->new NoticesHandler(ErrorStatus.NOTICE_NOT_FOUND));

            return NoticesConverter.toNoticeInfoDto(notice);
        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

    }
}
