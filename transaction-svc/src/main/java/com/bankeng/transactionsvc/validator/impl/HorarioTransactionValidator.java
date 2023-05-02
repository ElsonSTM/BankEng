package com.bankeng.transactionsvc.validator.impl;

import com.bankeng.transactionsvc.dto.RequestTransactionDto;
import com.bankeng.transactionsvc.exception.DomainBusinessException;
import com.bankeng.transactionsvc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(
        value = "{transaction.validation.horario}",
        havingValue = "true",
        matchIfMissing = false
)
public class HorarioTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

        if (LocalDateTime.now().getHour() > HOUR_END || requestTransactionDto.getData().getHour() > HOUR_END) {
            throw new DomainBusinessException("Horário após as 18");
        }
    }
}
