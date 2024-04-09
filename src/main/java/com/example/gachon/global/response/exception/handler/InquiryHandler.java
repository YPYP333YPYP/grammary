package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class InquiryHandler extends GeneralException {
    public InquiryHandler(BaseErrorCode errorCode) {super(errorCode);}
}
