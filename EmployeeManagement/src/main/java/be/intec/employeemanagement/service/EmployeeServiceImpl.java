package be.intec.employeemanagement.service;

import be.intec.employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.intec.employeemanagement.repository.*;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        Employee employee=null;
        if(optionalEmployee.isPresent()){
            employee=optionalEmployee.get();
        }else {
            throw new RuntimeException("Employee not found id"+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
       employeeRepository.deleteById(id);
    }

    @Override
    public boolean isEmailUnique(String email, Long idToExclude) {
        // Check if any employee with the given email exists
        Employee existingEmployee = employeeRepository.findByEmail(email);

        // If no employee found or the found employee is the one being updated (idToExclude),
        // then the email is considered unique
        return existingEmployee == null || Long.valueOf(idToExclude).equals(existingEmployee.getId());
    }

}
