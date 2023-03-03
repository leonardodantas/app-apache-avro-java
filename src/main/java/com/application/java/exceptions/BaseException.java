package com.application.java.exceptions;

import java.time.LocalDateTime;

public interface BaseException {

    Class getKlass();

    Throwable getException();

    String getMessage();
    String getMessageException();

    LocalDateTime getDate();
}
