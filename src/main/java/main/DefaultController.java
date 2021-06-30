package main;

import main.model.entities.Employee;
import main.model.entities.Organization;
import main.model.repositories.EmployeeRepository;
import main.model.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;

@Controller
public class DefaultController {


    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String index(Model model){


        Iterable<Organization> organizationIterable = organizationRepository.findAll();
        ArrayList<Organization> organizations = new ArrayList<>();
        for(Organization organization : organizationIterable){
            organizations.add(organization);
        }

        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        ArrayList<Employee> employees = new ArrayList<>();
        for(Employee employee : employeeIterable){
            employees.add(employee);
        }

               model.addAttribute("organizations", organizations);
        model.addAttribute("employees", employees);

        return "index";
    }
}
