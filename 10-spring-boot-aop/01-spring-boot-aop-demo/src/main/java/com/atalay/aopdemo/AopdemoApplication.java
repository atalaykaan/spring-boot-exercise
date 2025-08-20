package com.atalay.aopdemo;

import com.atalay.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {

        return runner -> {

            demoTheBeforeAdvice(accountDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO) {

        accountDAO.addAccount();

        System.out.println("\nLet's call it again!\n");

        accountDAO.addAccount();
    }
}
