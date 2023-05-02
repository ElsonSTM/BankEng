package com.bankeng.transactionsvc.validator;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;
import com.bankeng.transactionsvc.validator.impl.BancoTransactionValidator;
import com.bankeng.transactionsvc.validator.impl.HorarioTransactionValidator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
@Slf4j
public class TransactionValidationBean implements TransactionValidator {

    private List<TransactionValidator> transactionValidatorsList  = new ArrayList<>();

    @PostConstruct
    public void addBeans() {
        transactionValidatorsList.add(new BancoTransactionValidator());
        transactionValidatorsList.add(new HorarioTransactionValidator());

    }

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) {
        transactionValidatorsList.stream().forEach(transactionValidator -> {
            try {
                transactionValidator.validate(requestTransactionDto);
            } catch (DomainBusinessException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
