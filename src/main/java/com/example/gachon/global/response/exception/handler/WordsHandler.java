package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class WordsHandler extends GeneralException {
    public WordsHandler(BaseErrorCode errorCode) {super(errorCode);}
}
