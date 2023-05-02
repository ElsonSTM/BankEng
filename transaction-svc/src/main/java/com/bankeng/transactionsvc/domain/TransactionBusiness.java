package com.bankeng.transactionsvc.domain;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;
import com.bankeng.transactionsvc.repository.TransactionRepository;
import com.bankeng.transactionsvc.validator.TransactionValdation;
import com.bankeng.transactionsvc.validator.TransactionValidationBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionBusiness {

    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValdation transactionValidation) {
        this.transactionRepository = transactionRepository;
        this.transactionValdation = transactionValidation;
    }

    private TransactionRepository transactionRepository;
    private TransactionValdation transactionValdation;

    public void save(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (Objects.isNull(requestTransactionDto.getData()))
            requestTransactionDto.setData(LocalDateTime.now());

        transactionValdation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }
}
