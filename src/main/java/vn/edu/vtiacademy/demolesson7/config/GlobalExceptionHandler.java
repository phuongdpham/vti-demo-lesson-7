package vn.edu.vtiacademy.demolesson7.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.vtiacademy.demolesson7.model.DepartmentExistedException;
import vn.edu.vtiacademy.demolesson7.model.DepartmentNotFoundException;
import vn.edu.vtiacademy.demolesson7.model.ErrorResponse;

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
}
