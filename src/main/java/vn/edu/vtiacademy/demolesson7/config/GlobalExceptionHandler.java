package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.vtiacademy.demolesson7.model.*;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorCodeException ex) {
        var errCode = ex.getErrorCode();
        var errorResponse = new ErrorResponse(errCode.getStatus(), errCode.getCode(), errCode.getMessage(), ex.getDescription());
        return ResponseEntity
                .status(errCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentExistedException(ErrorCodeException ex) {
        return buildResponseEntity(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResp> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ValidationErrorResp.Violation.builder().fieldName(fieldError.getObjectName() + "." + fieldError.getField()).message(fieldError.getDefaultMessage()).build())
                .toList();

        var errCode = ErrorCode.METHOD_ARGUMENT_NOT_VALID;
        var error = new ValidationErrorResp(errCode.getStatus(), errCode.getCode(), errCode.getMessage(), violations);
        return ResponseEntity.of(Optional.of(error));
    }
}
