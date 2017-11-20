package fr.esipe.ing3.pds.eight.restaccountservices;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Log
public class RestLogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void restAnnotation() {}

    @Around("restAnnotation() && execution(* *(..))")
    public Object restLog(ProceedingJoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();

        final Class<?> controllerClass = method.getDeclaringClass();
        final RequestMapping controllerAnnotation = controllerClass.getAnnotation(RequestMapping.class);
        final String controllerRestPath = (controllerAnnotation.path().length == 0) ? "" : controllerAnnotation.path()[0];

        final RequestMapping controllerMethodAnnotation = method.getAnnotation(RequestMapping.class);
        final String methodRestPath = (controllerMethodAnnotation.path().length == 0) ? "" : controllerMethodAnnotation.path()[0];
        final RequestMethod requestMethod = (controllerMethodAnnotation.method().length == 0) ? RequestMethod.GET : controllerMethodAnnotation.method()[0];

        final Instant start = Instant.now();

        // @Before code
        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.warning(String.format("[RestLogAspect] : %s", throwable.getMessage()));
        }
        // @After code

        log.info(String.format("[REST][%sms][%s][%s][%s][%s()] %s",
                Duration.between(start, Instant.now()).toMillis(),
                controllerClass.getSimpleName(),
                requestMethod,
                controllerRestPath + methodRestPath,
                method.getName(),
                Arrays.stream(joinPoint.getArgs())
                        .map(argument -> (argument != null) ? argument.toString() : "null")
                        .collect(Collectors.joining(", "))
        ));

        return returnValue;
    }

}
