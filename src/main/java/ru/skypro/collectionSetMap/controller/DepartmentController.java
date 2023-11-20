package ru.skypro.collectionSetMap.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.collectionSetMap.model.Employee;
import ru.skypro.collectionSetMap.service.DepartmentService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> getEmployeeAllByDepartment (@RequestParam("departmentId") Integer departmentId){
        return departmentService.getEmployeeAllByDepartment(departmentId);
    }

    @GetMapping("all")
    public Map<Integer, List<Employee>> getAllEmployees (){
        return departmentService.getAllEmployees();
    }
}
