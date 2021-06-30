package main;

import main.model.entities.Employee;
import main.model.entities.Organization;
import main.model.repositories.EmployeeRepository;
import main.model.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/organizations/")
    public List<Organization> list(){
        Iterable<Organization> organizationIterable = organizationRepository.findAll();
        ArrayList<Organization> organizations  = new ArrayList<>();
        for(Organization organization : organizationIterable){
            organizations.add(organization);
        }
        return organizations;
    }

    @PostMapping("/organizations/")
    public int add(Organization organization){
        System.out.println("org: " + organization.getName());
        Organization newOrganization = organizationRepository.save(organization);
        return newOrganization.getId();
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        if(!optionalOrganization.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalOrganization.get(), HttpStatus.OK);
    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity deleteDepartment(@PathVariable int id){
        if(organizationRepository.findById(id).isPresent()) {
            organizationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
