package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class HistoriesHandler extends GeneralException {
    public HistoriesHandler(BaseErrorCode errorCode) {super(errorCode);}
}
