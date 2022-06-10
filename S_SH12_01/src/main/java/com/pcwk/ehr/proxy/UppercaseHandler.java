package com.pcwk.ehr.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//다이내믹 프록시로부터 요청을 전달 받으려면 InvocationHandler를 구현해야 한다.
//메소드는 invoke()하나만 있다.

public class UppercaseHandler implements InvocationHandler {
	final Logger LOG = LogManager.getLogger(getClass());
	Object target;//모든 인터페이스를 수용 가능 하도록 수정
	
	
	//다이내믹 프록시로 부터 전달 받은 요청을 다시 타킷 오브젝트에
	//위임하기 위해 주입 받는다.
	public UppercaseHandler(Hello target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//타킷으로 위임, 인터페이스의 메소드 호출에 모두 적용.
		Object ret = (String)method.invoke(target, args);
		LOG.debug("++++++++++++++++++++++++++++++++++");
		LOG.debug("+invoke()+"+method.getName());
		LOG.debug("++++++++++++++++++++++++++++++++++");
		//sayH 경우만 대문자로 변환
		if(ret instanceof String && method.getName().startsWith("sayH")) {
			return ((String) ret).toUpperCase();//부가기능
		}else {
			return ret;
		}
		
	}

}
