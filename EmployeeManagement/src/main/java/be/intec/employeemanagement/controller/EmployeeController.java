package be.intec.employeemanagement.controller;

import be.intec.employeemanagement.model.Employee;
import be.intec.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") @Validated Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "new_employee"; // Terug naar het formulier als er validatiefouten zijn
        }

        if (!employeeService.isEmailUnique(employee.getEmail(), employee.getId())) {
            result.rejectValue("email", "error.employee", "Email already exists"); // Voeg foutmelding toe aan resultaat
            return "new_employee"; // Terug naar het formulier met foutmelding
        }

        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFromForUpdate/{id}")
    public String showFromForUpdate(@PathVariable (value = "id") Long id, Model model){
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") Long id){
        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}


