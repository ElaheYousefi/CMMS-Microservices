package ir.dadeandish.application;

import ir.dadeandish.domain.EmployeeDto;
import ir.dadeandish.domain.EmployeeRepository;

public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean validateEmployee(int employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeDto getEmployee(int employeeId){
        return new EmployeeDto();
    }
}
