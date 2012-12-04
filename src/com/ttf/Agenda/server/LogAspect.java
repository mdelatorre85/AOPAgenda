package com.ttf.Agenda.server;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.ttf.Agenda.server.util.RegisterUtil;
import com.ttf.Agenda.shared.LogInstance;

@Aspect
public class LogAspect {

	@Around("@annotation(com.ttf.Agenda.server.Log)")
	public Object arroud(ProceedingJoinPoint pjp) {
		try {

			Objectify ofy = ObjectifyService.begin();
			RegisterUtil.registerClassInObjectify(LogInstance.class);

			StringBuilder retorno = new StringBuilder();

			retorno.append("signature : ");
			retorno.append(pjp.getSignature());
			retorno.append("/n");

			retorno.append("time : ");
			retorno.append(new Date().getTime());
			retorno.append("/n");

			if (pjp.getArgs() != null) {
				retorno.append("args : ");
				for (int i = 0; i < pjp.getArgs().length; i++) {
					retorno.append("/t");
					retorno.append(pjp.getArgs()[i]);
					retorno.append("/n");

				}
			}

			LogInstance logInstance = new LogInstance(retorno.toString());
			ofy.put(logInstance);

			return pjp.proceed();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

}
