package br.com.bankeng.transactionbff.domain;

import br.com.bankeng.transactionbff.dto.RequestTransactionDto;
import br.com.bankeng.transactionbff.dto.TransactionDto;
import br.com.bankeng.transactionbff.exception.UnauthorizedException;
import br.com.bankeng.transactionbff.redis.TransactionRedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRedisRepository transactionRedisRepository;

    public TransactionService(TransactionRedisRepository transactionRedisRepository) {
        this.transactionRedisRepository = transactionRedisRepository;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransactionDto requestTransactionDto) {
        requestTransactionDto.setData(LocalDateTime.now());
        return Optional.of(transactionRedisRepository.save(requestTransactionDto));
    }

    public Optional<TransactionDto> findById(final String id) {
        if(id.equals("2")){
            throw new UnauthorizedException("Esse é um test");
        }

        return transactionRedisRepository.findById(id);
    }







}
