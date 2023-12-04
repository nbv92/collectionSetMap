package ru.skypro.collectionSetMap.service.impl;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.collectionSetMap.exception.EmployeeAlreadyAddedException;
import ru.skypro.collectionSetMap.exception.EmployeeNotFoundException;
import ru.skypro.collectionSetMap.exception.EmployeeStorageIsFullException;
import ru.skypro.collectionSetMap.exception.InvalidNameException;
import ru.skypro.collectionSetMap.model.Employee;
import ru.skypro.collectionSetMap.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int STORAGE_SIZE = 5;
    @PostConstruct
    public void initEmployees() {
        add("Ivan","Ivanov", 50000,1);
        add("Petr","Petrov", 50001,1);
        add("Timofey","Timohin", 49999,1);
    }
    private final Map <String, Employee> employees = new HashMap<>();
    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }

        if(employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }

        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
        employees.put(getKey(employee), employee);
        return null;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName,lastName))) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + "и с фамилией " + lastName + "не найден в базе");
        }
        return employees.remove(getKey(firstName,lastName));

    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName,lastName));
        if(employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + "и с фамилией " + lastName + "не найден в базе");
        }
        return employee;
    }
    @Override
    public Map<String,Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static  String getKey (String firstName, String lastName) {
        return firstName + lastName ;
    }

    private static String getKey (Employee employee) {
        return employee.getFirstName() + employee.getLastName() ;
    }

    private void validateNames (String ... names) {
        for (String name: names) {
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException (name + " is invalid");
            }
        }
    }

}
