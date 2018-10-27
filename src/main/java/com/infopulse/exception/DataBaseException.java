package com.infopulse.exception;

public class DataBaseException extends RuntimeException {
    public DataBaseException(Throwable exception){
        super(exception);
    }
}
