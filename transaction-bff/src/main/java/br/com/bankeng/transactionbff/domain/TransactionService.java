package br.com.bankeng.transactionbff.domain;

import br.com.bankeng.transactionbff.dto.RequestTransactionDto;
import br.com.bankeng.transactionbff.dto.TransactionDto;
import br.com.bankeng.transactionbff.exception.UnauthorizedException;
import br.com.bankeng.transactionbff.redis.TransactionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    private TransactionRedisRepository transactionRedisRepository;
    private RetryTemplate retryTemplate;

    public TransactionService(TransactionRedisRepository transactionRedisRepository, RetryTemplate retryTemplate) {
        this.transactionRedisRepository = transactionRedisRepository;
        this.retryTemplate = retryTemplate;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransactionDto requestTransactionDto) {
        requestTransactionDto.setData(LocalDateTime.now());
        return Optional.of(transactionRedisRepository.save(requestTransactionDto));
    }

//    Duas formas de usar o Retry abaixo:

//    @Retryable(value = QueryTimeoutException.class, maxAttempts = 5,backoff = @Backoff(delay = 100))
//    public Optional<TransactionDto> findById(final String id) {
//        log.info("Consultando Redis");
//        return transactionRedisRepository.findById(id);
//    }

    public Optional<TransactionDto> findById(final String id) {

        return retryTemplate.execute(ret -> {
            log.info("Consultando Redis");
            return transactionRedisRepository.findById(id);
        });
    }

}
