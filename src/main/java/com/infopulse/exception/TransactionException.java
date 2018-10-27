package com.infopulse.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(Throwable exception){
        super(exception);
    }
    public TransactionException(String message){
        super(message);
    }
}
