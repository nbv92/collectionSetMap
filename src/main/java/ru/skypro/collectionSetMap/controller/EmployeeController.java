package ru.skypro.collectionSetMap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.collectionSetMap.model.Employee;
import ru.skypro.collectionSetMap.service.EmployeeService;
import java.util.Map;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping ("/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return employeeService.add(firstName,lastName);

    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return employeeService.remove(firstName,lastName);

    }
    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return employeeService.find(firstName,lastName);

    }
    @GetMapping()
    public Map<String,Employee> getAll(){
        return employeeService.getAll();

    }
}
