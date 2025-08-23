package com.atalay.aopdemo;

import com.atalay.aopdemo.dao.AccountDAO;
import com.atalay.aopdemo.dao.MembershipDAO;
import com.atalay.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(
                                    AccountDAO accountDAO,
                                    MembershipDAO membershipDAO,
                                    TrafficFortuneService trafficFortuneService) {

        return runner -> {

            //demoTheBeforeAdvice(accountDAO, membershipDAO);
            //demoTheAfterReturningAdvice(accountDAO, membershipDAO);
            //demoTheAfterThrowingAdvice(accountDAO);
            //demoTheAfterAdvice(accountDAO);
            //demoTheAroundAdvice(trafficFortuneService);
            demoTheAroundAdviceHandleException(trafficFortuneService);
            //demoTheAroundAdviceRethrowException(trafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

        System.out.println("Calling getFortune()");

        boolean tripWire = true;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

        System.out.println("Calling getFortune()");

        boolean tripWire = true;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdvice");

        System.out.println("Calling getFortune()");

        String data = trafficFortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO accountDAO) {

        List<Account> accountList = null;

        try {
            boolean tripWire = false;
            accountList = accountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain program: ... caught exception: " + exc);
        }


        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("------");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {

        List<Account> accountList = null;

        try {
            boolean tripWire = true;
            accountList = accountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain program: ... caught exception: " + exc);
        }


        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("------");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        List<Account> accountList = accountDAO.findAccounts();

        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("------");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

        Account tempAccount = new Account();

        tempAccount.setName("Kaan");
        tempAccount.setLevel("Gold");

        accountDAO.addAccount(tempAccount, true);
        accountDAO.doWork();

        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();
        String service = accountDAO.getServiceCode();

        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();
    }
}
