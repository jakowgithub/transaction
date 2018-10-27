package com.infopulse.exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message){
        super(message);
    }
    public ConnectionException(Throwable exception){
        super(exception);
    }
}
