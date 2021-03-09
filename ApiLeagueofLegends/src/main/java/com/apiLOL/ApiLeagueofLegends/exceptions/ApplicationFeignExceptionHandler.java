package com.apiLOL.ApiLeagueofLegends.exceptions;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApplicationFeignExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(FeignException.class)
    public int handleFeignStatusException(FeignException e, HttpServletResponse response) {
        return e.status();
    }
}
