package com.krisanov.codenest.common.exception;

public class ImageNotSavedException extends RuntimeException {

    public ImageNotSavedException(String message) {
        super(message);
    }

    public ImageNotSavedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
