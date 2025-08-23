package com.atalay.aopdemo.aspect;

import com.atalay.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.atalay.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String method = proceedingJoinPoint.getSignature().toShortString();

        System.out.println("\n=======> Executing @Around on method: " + method);

        long begin = System.nanoTime();

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception exc) {

            System.out.println(exc.getMessage());

            throw exc;

            //result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        long end = System.nanoTime();

        long duration = end - begin;

        System.out.println("\n=======> Duration: " + duration + " nanoseconds");

        return result;
    }

    @After("execution(* com.atalay.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n======> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.atalay.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exc) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n=======> Executing @AfterThrowing on method: " + method);

        System.out.println("\n=======> The exception is: " + exc);
    }

    @AfterReturning(
            pointcut = "execution(* com.atalay.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("\n======> Executing @AfterReturning on method :" + method);

        System.out.println("\n======> result is: " + result);

        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for (Account tempAccount : result) {

            String theUpperName = tempAccount.getName().toUpperCase();

            tempAccount.setName(theUpperName);
        }
    }

    @Before("com.atalay.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        System.out.println("\n=========> Executing @Before advice on method");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {
                Account tempAccount = (Account) tempArg;

                System.out.println("account name: " + tempAccount.getName());
                System.out.println("account level: " + tempAccount.getLevel());
            }
        }

    }
}
