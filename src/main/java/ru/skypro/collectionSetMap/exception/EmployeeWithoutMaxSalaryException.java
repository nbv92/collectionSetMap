package ru.skypro.collectionSetMap.exception;

public class EmployeeWithoutMaxSalaryException extends RuntimeException {
    public EmployeeWithoutMaxSalaryException (String message) {
        super(message);
    }
}
