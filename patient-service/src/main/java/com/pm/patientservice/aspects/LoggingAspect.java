package com.pm.patientservice.aspects;

import com.pm.patientservice.controller.PatientController;
import com.pm.patientservice.model.PatientRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private final Logger patientControllerLogger;

    public LoggingAspect() {
        this.patientControllerLogger = LoggerFactory.getLogger(PatientController.class);
    }

    @Before("execution(* com.pm.patientservice.controller.PatientController.*(..))")
    public void logControllerRequest(JoinPoint jp) {
        patientControllerLogger.info("Executing {} method", jp.getSignature().getName());

        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof PatientRequest)
                patientControllerLogger.debug("Request - {}", arg);
            else
                patientControllerLogger.debug("Requested for id - {}", arg);
        }
    }
}
