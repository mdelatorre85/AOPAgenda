package com.ttf.Agenda.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LogAspect {

	@Around("@annotation(com.ttf.Agenda.server.Log)")
	public void arroud(ProceedingJoinPoint pjp) {
		try {
			System.out.println("@ before");
			pjp.proceed();
			System.out.println("@ after");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Before("execution(* com.ttf.Agenda.server.ServiceImpl.*(..))")
	public void beforeCompra() {
		System.out.println("@ Before");
	}

	@Before("execution(* com.ttf.Agenda.server.ServiceImpl.doLogin(String, String))")
	public void beforedoLogin() {
		System.out.println("@ Before");
	}

	@Before("within(com.ttf.server.ServiceImpl)")
	public void before() {
		System.out.println("Within all ServiceImpl");
	}

}
