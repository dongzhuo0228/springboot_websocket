package com.example.aspect;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.annotation.LogAnnotation;

@Aspect
@Component
public class LogAspect {
	
	 //Service层切点  用于记录日志
	//@Pointcut("@annotation(com.example.annotation.LogAnnotation)") --基于自定义注解  切面为注解类 主要方法被此注解注解方法都会按照此切面执行
	@Pointcut("execution(* com.example.service.impl.*.*(..))")  //--基于具体方法  切面为类方法
    public void logAspect() {
    	
    }
    
    @Around("logAspect()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
    	 Object proceed = pjp.proceed();
    	 String targetName = pjp.getTarget().getClass().getName();  
         String methodName = pjp.getSignature().getName();    
         Object[] arguments = pjp.getArgs();    
         Class targetClass = Class.forName(targetName);  
         Method[] methods = targetClass.getMethods();    
         int role = -1;    
         for (Method method : methods) {  
             Annotation[] annotations = method.getAnnotations();  
             for (Annotation annotation : annotations) {  
                 // 获取注解的具体类型  
                 Class<? extends Annotation> annotationType = annotation.annotationType();  
                 if (LogAnnotation.class == annotationType) {  
                     System.out.println(method.getName()+"()\t" + LogAnnotation.class.getName());  
                     String desc = method.getAnnotation(LogAnnotation.class).description();
                     System.out.println("接口描述为"+desc);  
                     // 打印出java.lang.annotation.Annotation，注解类其实都实现了Annotation这个接口  
                     Class<?>[] interfaces = LogAnnotation.class.getInterfaces();  
                     System.out.println(interfaces[0].getName());  
                 }  
             }  
         }   
          return proceed;     
    } 
	

}
