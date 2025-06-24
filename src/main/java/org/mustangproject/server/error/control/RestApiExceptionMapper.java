// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server.error.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mustangproject.server.error.ErrorId;
import org.mustangproject.server.error.entity.ApiErrorResponse;
import org.mustangproject.server.error.exception.InternalServerRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @Order(400)
@ControllerAdvice
public class RestApiExceptionMapper {

  private static final String ERROR_LOG_FORMAT = "[{}] [{}] error, address:{}, url:{}, message:{}";
  private static final Logger LOGGER = LogManager.getLogger(RestApiExceptionMapper.class);

  @ExceptionHandler
  public ResponseEntity<ApiErrorResponse> toResponse(
      final HttpServletRequest request, final Exception e) {
    String url = request != null ? request.getRequestURL().toString() : "";
    String address = request != null ? request.getRemoteAddr() : "";

    Throwable t =
        e instanceof UndeclaredThrowableException
            ? ((UndeclaredThrowableException) e).getUndeclaredThrowable()
            : e;

    if (t instanceof MethodArgumentNotValidException) {
      return handleMethodArgumentNotValidException(
          url, address, (MethodArgumentNotValidException) t);
    } else if (t instanceof ValidationException) {
      return handleValidationException(url, address, (ValidationException) t);
    } else if (t instanceof InternalServerRuntimeException) {
      return handleInternalServerRuntimeExceptions(
          url, address, (InternalServerRuntimeException) t);
    } else if (t instanceof IOException) {
      return handleIOException(url, address, (IOException) t);
    } else {
      return handleGenericExceptions(url, address, e);
    }
  }

  // Handles validation errors for @Valid or other bean validation
  private ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
      String url, String address, MethodArgumentNotValidException e) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    LOGGER.error(
        ERROR_LOG_FORMAT,
        ErrorId.VALIDATION_ERROR.getMessage(),
        status,
        address,
        url,
        e.getMessage());

    // Collect field errors and build a detailed error message
    Map<String, String> validationErrors =
        e.getBindingResult().getFieldErrors().stream()
            .collect(
                Collectors.toMap(
                    fieldError -> fieldError.getField(),
                    fieldError -> fieldError.getDefaultMessage(),
                    (existing, replacement) -> existing, // In case of duplicate keys
                    HashMap::new));

    ApiErrorResponse apiErrorResponse =
        buildErrorResponse(
            status,
            url,
            ErrorId.VALIDATION_ERROR.getMessage(),
            "Validation failed for one or more fields",
            validationErrors);

    return ResponseEntity.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiErrorResponse);
  }

  // Handles generic validation errors (like ValidationException)
  private ResponseEntity<ApiErrorResponse> handleValidationException(
      String url, String address, ValidationException e) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    LOGGER.error(
        ERROR_LOG_FORMAT,
        ErrorId.VALIDATION_ERROR.getMessage(),
        status,
        address,
        url,
        e.getMessage());

    ApiErrorResponse apiErrorResponse =
        buildErrorResponse(
            status, url, ErrorId.VALIDATION_ERROR.getMessage(), e.getMessage(), null);

    return ResponseEntity.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiErrorResponse);
  }

  private ResponseEntity<ApiErrorResponse> handleIOException(
      String url, String address, IOException e) {

    HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
    LOGGER.error(
        ERROR_LOG_FORMAT,
        ErrorId.INTERNAL_ERROR.getMessage(),
        status,
        address,
        url,
        e.getMessage());

    ApiErrorResponse apiErrorResponse =
        buildErrorResponse(status, url, ErrorId.INTERNAL_ERROR.getMessage(), e.getMessage(), null);

    return ResponseEntity.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiErrorResponse);
  }

  private ResponseEntity<ApiErrorResponse> handleGenericExceptions(
      String url, String address, Exception e) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    LOGGER.error(
        ERROR_LOG_FORMAT,
        ErrorId.INTERNAL_ERROR.getMessage(),
        status,
        address,
        url,
        e.getMessage());

    ApiErrorResponse apiErrorResponse =
        buildErrorResponse(status, url, ErrorId.INTERNAL_ERROR.getMessage(), e.getMessage(), null);

    return ResponseEntity.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiErrorResponse);
  }

  private ResponseEntity<ApiErrorResponse> handleInternalServerRuntimeExceptions(
      String url, String address, InternalServerRuntimeException e) {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    LOGGER.error(
        ERROR_LOG_FORMAT, e.getErrorId().getMessage(), status, address, url, e.getMessage());

    ApiErrorResponse apiErrorResponse =
        buildErrorResponse(status, url, e.getErrorId().getMessage(), e.getMessage(), null);

    return ResponseEntity.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiErrorResponse);
  }

  private ApiErrorResponse buildErrorResponse(
      HttpStatus status,
      String url,
      String errorCode,
      String message,
      Map<String, String> details) {
    return ApiErrorResponse.builder()
        .errorCode(errorCode)
        .requestUrl(url)
        .httpCode(status.value())
        .message(message)
        .details(details)
        .build();
  }
}
