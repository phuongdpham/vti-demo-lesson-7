package vn.edu.vtiacademy.demolesson7.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentNotFoundException extends RuntimeException {
    int status;
    String code;
    String message;

    public DepartmentNotFoundException(int status, String code, String message) {
        super(code + ": " + message);

        this.status = status;
        this.code = code;
        this.message = message;
    }
}
