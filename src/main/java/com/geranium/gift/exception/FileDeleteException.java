package com.geranium.gift.exception;

public class FileDeleteException extends StorageException {

    public FileDeleteException(String message) {
        super(message);
    }

    public FileDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}