package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class UsersHandler extends GeneralException {
    public UsersHandler(BaseErrorCode errorCode) {super(errorCode);}

}
