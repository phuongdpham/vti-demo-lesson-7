package vn.edu.vtiacademy.demolesson7.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    DEPARTMENT_NOT_FOUND(404, "DEP-001", "Department not found"),
    DEPARTMENT_EXISTED(409, "DEP-002", "Department existed"),
    METHOD_ARGUMENT_NOT_VALID(400, "DEP-003", "Method argument not valid"),
    ACCOUNT_NOT_FOUND(404, "ACC-001", "Account not found"),
    UNAUTHORIZED(401, "ACC-002", "Unauthorized"),
    FORBIDDEN(403, "ACC-003", "Forbidden"),
    ;

    int status;
    String code;
    String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
