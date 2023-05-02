package com.bankeng.transactionsvc.validator.impl;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;
import com.bankeng.transactionsvc.validator.TransactionValdation;

public class EmptyTransactionValidationBean extends TransactionValdation {

    @Override
    public  void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException{

    }
}
