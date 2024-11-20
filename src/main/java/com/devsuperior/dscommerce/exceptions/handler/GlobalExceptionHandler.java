package com.devsuperior.dscommerce.exceptions.handler;

import com.devsuperior.dscommerce.exceptions.handler.error.CustomError;
import com.devsuperior.dscommerce.exceptions.handler.error.ValidationError;
import com.devsuperior.dscommerce.exceptions.DatabaseException;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

     // Tratando ResourceNotFoundException
     @ExceptionHandler({ResourceNotFoundException.class})
     public ResponseEntity<CustomError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.NOT_FOUND;

          CustomError err = CustomError.builder()
                  .error(e.getMessage())
                  .timestamp(Instant.now())
                  .status(status.value())
                  .path(request.getRequestURI())
                  .build();

          return ResponseEntity.status(status).body(err);
     }

     // Tratando DatabaseException
     @ExceptionHandler({DatabaseException.class})
     public ResponseEntity<CustomError> Database(DatabaseException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.BAD_REQUEST;

          CustomError err = CustomError.builder()
                  .error(e.getMessage())
                  .timestamp(Instant.now())
                  .path(request.getRequestURI())
                  .status(status.value())
                  .build();

          return ResponseEntity.status(status).body(err);
     }

     @ExceptionHandler({MethodArgumentNotValidException.class})
     public ResponseEntity<CustomError> MethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

          ValidationError err = ValidationError.builder()
                  .error("Erro de validação")
                  .timestamp(Instant.now())
                  .status(status.value())
                  .path(request.getRequestURI())
                  .build();

          // Extraindo erros reais do MethodArgumentNotValidException
          e.getBindingResult().getFieldErrors()
                  .forEach(fieldError -> err.addError(fieldError.getField(), fieldError.getDefaultMessage())
          );

          return ResponseEntity.status(status).body(err);
     }
}