package ir.dadeandish.application;

import ir.dadeandish.domain.Employee;
import ir.dadeandish.dto.EmployeeDto;
import ir.dadeandish.domain.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean validateEmployee(int employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeDto getEmployeeById(int employeeId){
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("employee with this id doesn't exist"));
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setMobile(employee.getMobile());
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        return employeeDto;
    }
}
