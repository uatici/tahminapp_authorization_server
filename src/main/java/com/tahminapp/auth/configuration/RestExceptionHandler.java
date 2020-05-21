package com.tahminapp.auth.configuration;

import com.tahminapp.auth.customexceptions.UserAlreadyExistException;
import com.tahminapp.auth.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserAlreadyExistException.class)
  protected ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistException ex) {
    return buildResponseEntity(new ApiErrorDto(HttpStatus.CONFLICT, ex));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> errors = ex.getConstraintViolations()
            .parallelStream()
            .map(e -> e.getInvalidValue() + " " + e.getMessage())
            .collect(Collectors.toList());

    return buildResponseEntity(
            new ApiErrorDto(HttpStatus.BAD_REQUEST, "Invalid request", errors, ex));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {


    List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getField() + " " + x.getDefaultMessage())
            .collect(Collectors.toList());

    return buildResponseEntity(
        new ApiErrorDto(HttpStatus.BAD_REQUEST, "Invalid request", errors, ex));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiErrorDto apiErrorDto) {
    return new ResponseEntity<>(apiErrorDto, apiErrorDto.getHttpStatus());
  }
}
