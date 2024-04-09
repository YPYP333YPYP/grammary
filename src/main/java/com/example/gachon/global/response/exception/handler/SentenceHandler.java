package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class SentenceHandler extends GeneralException{
    public SentenceHandler(BaseErrorCode errorCode) {super(errorCode);}
}
