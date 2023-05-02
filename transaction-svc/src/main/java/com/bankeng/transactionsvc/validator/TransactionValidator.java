package com.bankeng.transactionsvc.validator;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;
}
