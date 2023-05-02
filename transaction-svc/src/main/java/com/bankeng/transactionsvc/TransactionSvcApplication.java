package com.bankeng.transactionsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.bankeng.transactionsvc.repository"})
public class TransactionSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSvcApplication.class, args);
	}

}
