package com.example.tryhart11.aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
@Configuration
@ControllerAdvice
public class Aspects {

    @Before(value = "executeController()")
    public void beforeExecuteController(){
        System.out.println("Trước phương thức ");
    }

    @After(value = "executeController()")
    public void afterExecuteController(){
        System.out.println("Sau phương thức ");
    }

    @AfterThrowing(value = "executeController()" , throwing = "ex")
    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception ex){
        System.out.println("Lỗi "+ ex);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Pointcut(value = "within(com.example.tryhart11.controller.*)")
    public void executeController(){
    }
}
