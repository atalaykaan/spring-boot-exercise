package com.atalay.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    @Pointcut("execution(* com.atalay.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.atalay.aopdemo.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.atalay.aopdemo.dao.*.set*(..))")
    public void setter () {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}
}
