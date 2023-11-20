package ru.skypro.collectionSetMap.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.collectionSetMap.model.Employee;
import ru.skypro.collectionSetMap.service.DepartmentService;
import ru.skypro.collectionSetMap.service.EmployeeService;
import ru.skypro.collectionSetMap.exception.EmployeeWithoutMinSalaryException;
import ru.skypro.collectionSetMap.exception.EmployeeWithoutMaxSalaryException;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeWithoutMaxSalaryException("Нет максимальной зарплаты"));
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeWithoutMinSalaryException("Нет минимальной зарплаты"));

    }

    @Override
    public List<Employee> getEmployeeAllByDepartment(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>>  getAllEmployees() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
