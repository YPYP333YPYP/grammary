package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.inquiry.dto.request.InquiryRequestDto;
import com.example.gachon.domain.inquiry.dto.response.InquiryResponseDto;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.InquiryHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiriesService {

    private final InquiriesRepository inquiriesRepository;
    private final UsersRepository usersRepository;

    public InquiryResponseDto.InquiryInfoDto getInquiryInfo(Long inquiryId) {
        Inquiries inquiry = inquiriesRepository.findById(inquiryId).orElseThrow(()-> new InquiryHandler(ErrorStatus.INQUIRY_NOT_FOUND));
        return InquiriesConverter.toInquiryInfoDto(inquiry);
    }

    public List<InquiryResponseDto.InquiryInfoDto> getInquiryList(String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        List<Inquiries> inquiries = inquiriesRepository.findAllByUser(user);

        return inquiries.stream()
                .map(InquiriesConverter::toInquiryInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createInquiry(String email, InquiryRequestDto.InquiryDto inquiryDto) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        Inquiries inquiry = Inquiries.builder()
                .user(user)
                .requestMessage(inquiryDto.getRequest())
                .requestTimestamp(LocalDateTime.now())
                .build();

        inquiriesRepository.save(inquiry);
    }
}
