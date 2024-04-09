package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class SentencesHandler extends GeneralException{
    public SentencesHandler(BaseErrorCode errorCode) {super(errorCode);}
}
