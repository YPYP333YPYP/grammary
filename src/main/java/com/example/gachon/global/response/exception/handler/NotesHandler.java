package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class NotesHandler extends GeneralException {

    public NotesHandler(BaseErrorCode errorCode) {super(errorCode);}
}
