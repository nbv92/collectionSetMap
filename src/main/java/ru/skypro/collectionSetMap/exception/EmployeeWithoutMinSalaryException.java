package ru.skypro.collectionSetMap.exception;

public class EmployeeWithoutMinSalaryException extends RuntimeException {
   public EmployeeWithoutMinSalaryException (String message) {
        super(message);
    }
}
