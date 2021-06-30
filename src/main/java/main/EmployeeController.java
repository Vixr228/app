package main;

import main.model.entities.Employee;
import main.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    //employees
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/")
    public List<Employee> list(){
        Iterable<Employee> bookIterable = employeeRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>();
        for(Employee employee : bookIterable){
            employees.add(employee);
        }
        return employees;
    }

    @PostMapping("/employees/")
    public int add(Employee employee){
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee.getId();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(!optionalEmployee.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalEmployee.get(), HttpStatus.OK);
    }

    @GetMapping("/organizations/employees/{orgId}")
    public ResponseEntity getEmployeeOfOrganization(@PathVariable int orgId){
        Iterable<Employee> iterable = employeeRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>();
        for(Employee employee : iterable){
            if (employee.getOrganization().getId() == orgId) {
                employees.add(employee);
            }
        }

        if(!employees.isEmpty()) return new ResponseEntity(employees, HttpStatus.OK);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
