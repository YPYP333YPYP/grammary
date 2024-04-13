package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.inquiry.dto.response.InquiryResponseDto;

public class InquiriesConverter {

    public static InquiryResponseDto.InquiryInfoDto toInquiryInfoDto(Inquiries inquiries) {
        return InquiryResponseDto.InquiryInfoDto.builder()
                .inquiryId(inquiries.getId())
                .userId(inquiries.getUser().getId())
                .requestMessage(inquiries.getRequestMessage())
                .responseMessage(inquiries.getResponseMessage())
                .requestTimestamp(inquiries.getRequestTimestamp())
                .responseTimestamp(inquiries.getResponseTimestamp())
                .build();
    }
}
