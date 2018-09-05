package com.rest.tutorial.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.tutorial.spring.model.Employee;
import com.rest.tutorial.spring.service.EmployeeService;

@RestController
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService empService;

	/*---Add new employee---*/
	@PostMapping("/employee")
	public ResponseEntity<?> save(@RequestBody Employee employee) {

		long id = empService.save(employee);

		logger.info(" insert employee successful" + id);

		return ResponseEntity.ok().body("New Employee has been saved with ID:" + id);
	}

	/*---Get a employee by id---*/
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> get(@PathVariable("id") long id) {
		Employee employee = empService.get(id); // == 45
		logger.info(" fetching employee " + id + " successful");
		return ResponseEntity.ok().body(employee);
	}

	/*---get all employees---*/
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> list() {
		List<Employee> employees = empService.list();
		logger.info(" fetching all employees ");
		return ResponseEntity.ok().body(employees);
	}

	/*---Update a employee by id---*/
	@PutMapping("/employee/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Employee employee) {
		empService.update(id, employee);
		logger.info(" updating employee " + id + " successful");
		return ResponseEntity.ok().body("Employee has been updated successfully.");
	}

	/*---Delete a employee by id---*/
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		empService.delete(id);
		logger.info(" deleting employee " + id + " successful");
		return ResponseEntity.ok().body("Employee has been deleted successfully.");
	}
}