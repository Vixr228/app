package main;

import main.model.entities.Department;
import main.model.entities.Employee;
import main.model.repositories.DepartmentRepository;
import main.model.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
        @Autowired
        private DepartmentRepository departmentRepository;

        @GetMapping("/departments/")
        public List<Department> list(){
            Iterable<Department> departmentIterable = departmentRepository.findAll();
            ArrayList<Department> departments = new ArrayList<>();
            for(Department department : departmentIterable){
                departments.add(department);
            }
            return departments;
        }

        @PostMapping("/departments/")
        public int add(Department department){
            Department newDepartment = departmentRepository.save(department);
            return newDepartment.getId();
        }

        @GetMapping("/departments/{id}")
        public ResponseEntity get(@PathVariable int id){
            Optional<Department> optionalDepartment = departmentRepository.findById(id);
            if(!optionalDepartment.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return new ResponseEntity(optionalDepartment.get(), HttpStatus.OK);
        }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity deleteDepartment(@PathVariable int id){
        if(departmentRepository.findById(id).isPresent()) {
            departmentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


