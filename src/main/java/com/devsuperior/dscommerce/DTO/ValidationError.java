package com.devsuperior.dscommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
public class ValidationError extends CustomError {

     @Builder.Default
     private List<FieldError> errors = new ArrayList<>();

     public void addError(String fieldName, String message) {
          errors.add(new FieldError(fieldName, message));
     }

     @Getter
     @NoArgsConstructor
     @AllArgsConstructor
     public static class FieldError {
          private String fieldName;
          private String errorMessage;
     }

}
