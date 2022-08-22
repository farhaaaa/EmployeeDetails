package com.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable (value = "id") Long employeeId, @RequestBody Employee employeeDetails) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		Employee employee1 = employee.get();
		if(employeeDetails.getEmail() == null) {
			employee1.setEmail(employee1.getEmail());
		}
		else {
			employee1.setEmail(employeeDetails.getEmail());
		}
		if(employeeDetails.getFirstName() == null) {
			employee1.setFirstName(employee1.getFirstName());
		}
		else {
			employee1.setFirstName(employeeDetails.getFirstName());
		}
		if(employeeDetails.getLastName() == null) {
			employee1.setLastName(employee1.getLastName());
		}
		else {
			employee1.setLastName(employeeDetails.getLastName());
		}
		
		return ResponseEntity.ok(employeeRepository.save(employee1));
	}
	
	@DeleteMapping("/employees/{id}")
	public String updateEmployee(@PathVariable (value = "id") Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		Employee employee1 = employee.get();
		
		employeeRepository.delete(employee1);
		return "Employee Deleted";
	}
}
