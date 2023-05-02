package com.bankeng.transactionsvc.validator;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;

public abstract class TransactionValdation {
    public abstract void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException;
}
