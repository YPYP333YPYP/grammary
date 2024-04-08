package com.example.gachon.domain.notice;

import com.example.gachon.domain.notice.dto.response.NoticeResponseDto;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.NoticesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticesService {

    private final NoticesRepository noticesRepository;

    NoticeResponseDto.NoticeInfoDto getNoticeInfo(Long noticeId) {
        return NoticesConverter.toNoticeInfoDto(noticesRepository.findById(noticeId).orElseThrow(() -> new NoticesHandler(ErrorStatus.NOTICE_NOT_FOUND)));
    }
}
