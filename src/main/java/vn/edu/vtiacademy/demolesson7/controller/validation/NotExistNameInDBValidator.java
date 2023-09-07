package vn.edu.vtiacademy.demolesson7.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotExistNameInDBValidator implements ConstraintValidator<NotExistNameInDB, String> {
    DepartmentRepository departmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value))
            return true;

        return !departmentRepository.existsByName(value);
    }
}
