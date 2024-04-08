package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class NoticesHandler extends GeneralException{
    public NoticesHandler(BaseErrorCode errorCode) {super(errorCode);}
}
