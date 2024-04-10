package com.example.gachon.global.response.exception.handler;

import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.exception.GeneralException;

public class NotificationsHandler extends GeneralException {
    public NotificationsHandler(BaseErrorCode errorCode) {super(errorCode);}
}
