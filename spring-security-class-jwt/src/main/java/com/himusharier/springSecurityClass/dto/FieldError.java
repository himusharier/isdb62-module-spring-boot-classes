package com.himusharier.springSecurityClass.dto;
 
 public record FieldError(
         String field,
         String errorCode,
         String errorMessage
 ) {
 }