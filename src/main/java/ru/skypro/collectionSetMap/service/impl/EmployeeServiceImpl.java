package ru.skypro.collectionSetMap.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.collectionSetMap.exception.EmployeeAlreadyAddedException;
import ru.skypro.collectionSetMap.exception.EmployeeNotFoundException;
import ru.skypro.collectionSetMap.exception.EmployeeStorageIsFullException;
import ru.skypro.collectionSetMap.model.Employee;
import ru.skypro.collectionSetMap.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int STORAGE_SIZE = 5;
    private final List<Employee> employees = new ArrayList<>();
    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирмеdfs");
        }
        if(employees.contains(employees)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        Employee employee = new Employee(firstName,lastName);
        employees.add(new Employee(firstName,lastName));
        return null;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee (firstName,lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + "и с фамилией " + lastName + "не найден в базе");
        }
        employees.remove(new Employee(firstName, lastName));
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee requestedEmployee = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(requestedEmployee)) {
                return requestedEmployee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + "и с фамилией " + lastName + "не найден в базе");

    }
    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
