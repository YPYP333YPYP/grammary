package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class GeneralHandler extends GeneralException {
    public GeneralHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}