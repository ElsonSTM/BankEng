package com.bankeng.transactionsvc.exception;

public class DomainBusinessException extends Exception {
    public DomainBusinessException(String message) {
        super(message);
    }
}
