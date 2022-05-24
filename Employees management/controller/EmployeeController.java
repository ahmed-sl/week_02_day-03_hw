package com.example.springday03.controller;

import com.example.springday03.model.Employees;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
// return ResponseEntity.status(400).body("Couldn't find Employee by ID");
    private ArrayList<Employees> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Employees>> getEmployee(){
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody @Valid Employees employee, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        employees.add(employee);
        return ResponseEntity.status(200).body("add employee");
    }

    @PutMapping("/{index}")
    public ResponseEntity<String> updateEmployees(@PathVariable @Valid Integer index,
                                                  @RequestBody @Valid Employees employee,
                                                  Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body("update employee");
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int index){
        if (index > employees.size()-1 || index < 0 ){
            return ResponseEntity.status(400).body("index out of range " + employees.size());
        }
        employees.remove((int)index);
        return ResponseEntity.status(200).body("update employees");
    }

    @PutMapping("/apply/{id}")
    public ResponseEntity<String> applyEmployee(@PathVariable String id){
        Employees employeesApply = null;
        for (Employees employees1:employees){
            if(employeesApply.getId().equals(id)){
                employeesApply = employees1;
                }
            }
             if (employeesApply.isOnLeave()){
                 return ResponseEntity.status(400).body("employee leave");
        }
             if (employeesApply.getAnnualLeave() > 0){
                 employeesApply.setOnLeave(true);
                 return ResponseEntity.status(200).body("employee now leave");
             }else {
                 return ResponseEntity.status(400).body("Employee doesn't have annual leave");
             }
    }
}
