package com.bankeng.transactionsvc.config;

import com.bankeng.transactionsvc.validator.impl.EmptyTransactionValidationBean;
import com.bankeng.transactionsvc.validator.TransactionValdation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSevenTeen.class)
    public TransactionValdation emptyTransactionEnableNewerThanJavaSevenTeenValidation(){
       return new EmptyTransactionValidationBean();
   }

   @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.SEVENTEEN)
   @Bean
   @ConditionalOnMissingBean
   @Conditional(TransactionEnableNewerThanJavaSevenTeen.class)
   public TransactionValdation emptyTransactionEnableNewerTheanJavaSevenTeenValidation(){
        return new EmptyTransactionValidationBean();
   }
}
