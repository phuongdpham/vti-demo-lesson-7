package vn.edu.vtiacademy.demolesson7.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountNotFoundException extends ErrorCodeException {
    public AccountNotFoundException(ErrorCode errorCode, String description) {
        super(errorCode, description);
    }
}
