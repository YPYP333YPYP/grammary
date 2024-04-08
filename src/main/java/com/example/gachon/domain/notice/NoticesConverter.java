package com.example.gachon.domain.notice;

import com.example.gachon.domain.notice.dto.response.NoticeResponseDto;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.dto.response.UserResponseDto;

public class NoticesConverter {

    public static NoticeResponseDto.NoticeInfoDto toNoticeInfoDto(Notices notices){
        return NoticeResponseDto.NoticeInfoDto.builder()
                .noticeId(notices.getId())
                .title(notices.getTitle())
                .content(notices.getContent())
                .category(notices.getCategory())
                .isPinned(notices.isPinned())
                .created_at(notices.getCreatedAt())
                .updated_at(notices.getUpdatedAt())
                .build();
    }
}
