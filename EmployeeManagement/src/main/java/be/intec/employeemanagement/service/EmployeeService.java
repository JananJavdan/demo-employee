package be.intec.employeemanagement.service;

import be.intec.employeemanagement.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    boolean isEmailUnique(String email, Long idToExclude);
}
