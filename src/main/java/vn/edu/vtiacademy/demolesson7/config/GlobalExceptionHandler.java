package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.vtiacademy.demolesson7.model.DepartmentExistedException;
import vn.edu.vtiacademy.demolesson7.model.DepartmentNotFoundException;
import vn.edu.vtiacademy.demolesson7.model.ErrorResponse;
import vn.edu.vtiacademy.demolesson7.model.ValidationErrorResp;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        var errorResponse = new ErrorResponse(ex.getStatus(), ex.getCode(), ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(DepartmentExistedException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentExistedException(DepartmentExistedException ex) {
        var errorResponse = new ErrorResponse(ex.getStatus(), ex.getCode(), ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResp> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ValidationErrorResp.Violation.builder().fieldName(fieldError.getObjectName() + "." + fieldError.getField()).message(fieldError.getDefaultMessage()).build())
                .toList();

        var error = new ValidationErrorResp(400, violations);
        return ResponseEntity.of(Optional.of(error));
    }

}
