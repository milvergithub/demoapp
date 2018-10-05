package com.milver.web.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Locale;


@RestControllerAdvice
public class ExceptionHandlers {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse processConcurrencyError(ConcurrencyFailureException ex) {
        return new ErrorResponse("ERR_CONCURRENCY_FAILURE", "The user was not found");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorResponse dto = new ErrorResponse("ERR_VALIDATION","Invalid imputs");
        for (FieldError fieldError : fieldErrors) {
            dto.addError(messageSource.getMessage(fieldError.getDefaultMessage(),null, Locale.US));
        }
        return dto;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processParameterizedValidationError(RuntimeException ex) {
        return new ErrorResponse("PARAM", "Anunexpected internal server error occured");
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse processAccessDeniedException(AccessDeniedException ex) {
        return new ErrorResponse("ERR_ACCESS_DENIED", "An unexpected internal server error occured");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ErrorResponse("METHOD_NOT_ALLOWED", "An unexpected internal server error occured");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorDetails = new ErrorResponse( "NOT FOUND EXCEPTION", "Not Found " + ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> processException(Exception ex) {
        ResponseEntity.BodyBuilder builder;
        ErrorResponse errorResponse;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorResponse = new ErrorResponse("error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "Internal server error");
        }
        return builder.body(errorResponse);
    }


}