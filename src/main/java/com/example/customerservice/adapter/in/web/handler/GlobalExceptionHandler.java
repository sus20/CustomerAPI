package com.example.customerservice.adapter.in.web.handler;

import com.example.customerservice.core.exception.address.AddressAlreadyExistsException;
import com.example.customerservice.core.exception.address.AddressIdMismatchException;
import com.example.customerservice.core.exception.address.AddressNotBelongToCustomerException;
import com.example.customerservice.core.exception.address.AddressNotFoundException;
import com.example.customerservice.core.exception.bank.BankAccountAlreadyExistsException;
import com.example.customerservice.core.exception.bank.BankAccountNotBelongToCustomerException;
import com.example.customerservice.core.exception.bank.BankAccountNotFoundException;
import com.example.customerservice.core.exception.bank.BankIdMismatchException;
import com.example.customerservice.core.exception.customer.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankAccountAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleBankAccountAlreadyExistsException(BankAccountAlreadyExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BankAccountNotBelongToCustomerException.class)
    public ResponseEntity<ErrorDetails> handleBankAccountNotBelongToCustomerException(BankAccountNotBelongToCustomerException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleBankAccountNotFoundException(BankAccountNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankIdMismatchException.class)
    public ResponseEntity<ErrorDetails> handleBankIdMismatchException(BankIdMismatchException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleAddressAlreadyExistsException(AddressAlreadyExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AddressIdMismatchException.class)
    public ResponseEntity<ErrorDetails> handleAddressIdMismatchException(AddressIdMismatchException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotBelongToCustomerException.class)
    public ResponseEntity<ErrorDetails> handleAddressNotBelongToCustomerException(AddressNotBelongToCustomerException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleAddressNotFoundException(AddressNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        Map<String, List<String>> errorsMap = getErrorsMap(errors);
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}