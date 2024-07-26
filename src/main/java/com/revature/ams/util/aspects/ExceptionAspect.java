package com.revature.ams.util.aspects;

import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.exceptions.UnauthorizedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAspect {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dataNotFound(DataNotFoundException dnf){
        return dnf.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> exceptions = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((exception) -> {
                    String field = ((FieldError) exception).getField();
                    String message = exception.getDefaultMessage();
                    exceptions.put(field, message);
                }
        );
        return exceptions;
    }

    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authHandler(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String devDidSomethingWrong(Exception e){
        return e.getMessage();
    }
}
