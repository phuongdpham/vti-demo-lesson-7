package vn.edu.vtiacademy.demolesson7.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentNotFoundException extends ErrorCodeException {
    public DepartmentNotFoundException(ErrorCode errorCode, String description) {
        super(errorCode, description);
    }
}
