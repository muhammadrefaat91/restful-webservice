package com.rest.webservices.restfulrestwebservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


/*
* it is a class used to provide centralized exception handling across all methods(request mappings)
* */
@ControllerAdvice //provided across all controllers
@RestController //provide response back while an exception thrown
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex,
                                                        WebRequest request) throws Exception {
            //request.getDescription(false) include info about the client
            ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());

            return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex,
                                                     WebRequest request) throws Exception {
        //request.getDescription(false) include info about the client
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //this method called while binding method arguments failed
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       //ex.getBindingResult().toString() show error messages against validation
        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation Failed", ex.getBindingResult().toString(), new Date());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
