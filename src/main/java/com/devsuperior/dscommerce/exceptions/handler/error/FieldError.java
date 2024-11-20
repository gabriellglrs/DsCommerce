package com.devsuperior.dscommerce.exceptions.handler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FieldError {
     private String fieldName;
     private String errorMessage;
}
