package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//Used to handle exceptions across the whole application
public class GlobalException extends ResponseEntityExceptionHandler {

    /**
     * @param ex stores the TrackNotFoundException object,
     * @return returns the response entity with exception message.
     */
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<Object> trackNotFoundExceptionHandler(final TrackNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex stores the TrackAlreadyExistsException object,
     * @return returns the response entity with exception message.
     */
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<Object> trackAlreadyExistsExceptionHandler(final TrackAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * @param e stores the Exception object,
     * @return returns the response entity with exception message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(final Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
