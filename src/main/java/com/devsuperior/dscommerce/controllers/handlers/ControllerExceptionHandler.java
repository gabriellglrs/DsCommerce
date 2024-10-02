package com.devsuperior.dscommerce.controllers.handlers;

import com.devsuperior.dscommerce.DTO.CustomErrorDTO;
import com.devsuperior.dscommerce.exceptions.DatabaseException;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

     // Tratando ResourceNotFoundException
     @ExceptionHandler({ResourceNotFoundException.class})
     public ResponseEntity<CustomErrorDTO> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.NOT_FOUND;
          CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
          return ResponseEntity.status(status).body(err);
     }

     // Tratando DatabaseException
     @ExceptionHandler({DatabaseException.class})
     public ResponseEntity<CustomErrorDTO> Database(DatabaseException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.BAD_REQUEST;
          CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
          return ResponseEntity.status(status).body(err);
     }
}
