package vn.edu.vtiacademy.demolesson7.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.domain.Pageable;

public class PageableValidator implements ConstraintValidator<PageableCheck, Pageable> {
    int minPage;
    int minSize;
    int maxSize;

    @Override
    public void initialize(PageableCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        minPage = constraintAnnotation.minPage();
        minSize = constraintAnnotation.minSize();
        maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        if (value.getPageNumber() < minPage) {
            return false;
        }

        return maxSize >= value.getPageSize() && value.getPageSize() >= minSize;
    }
}
