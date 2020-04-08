package test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {

	@Pointcut("execution(* *.test(..))")
	public void test(){
	
	}
	
	@Before("test()")
	public void beforeTest(){
		System.out.println("beforeTest");
	}
	
	@After("test()")
	public void afterTest(){
		System.out.println("afterTest");
	}
	
	@Around("test()")
	public Object arountTest(ProceedingJoinPoint p ){
		System.out.println("before");
		Object o = null;
		try{
			o = p.proceed();
		}catch (Throwable e){
			e.printStackTrace();
		}
		System.out.println("after");
		return 0;
	}

}
