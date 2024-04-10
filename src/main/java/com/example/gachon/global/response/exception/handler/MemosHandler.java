package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class MemosHandler extends GeneralException {
    public MemosHandler(BaseErrorCode errorCode) {super(errorCode);}
}
