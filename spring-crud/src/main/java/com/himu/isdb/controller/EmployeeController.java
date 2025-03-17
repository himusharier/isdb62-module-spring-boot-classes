package com.himu.isdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himu.isdb.model.Employee;
import com.himu.isdb.service.EmployeeService;

//@Controller
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public Employee saveEmp(@RequestBody Employee employee) {
		Employee savedEmp = service.saveEmployee(employee);
		return savedEmp;
	}

//	@GetMapping("/{id}")
//	public Employee getEmpById(@PathVariable int id) {
//		Employee empById = service.getEmpById(id);
//		return empById;
//	}
	@GetMapping("/{id}")
	public Employee getEmpById(@PathVariable("id") int id) {
		Employee empById = service.getEmpById(id);
		return empById;
	}

	@GetMapping
	public List<Employee> getAllEmp() {
		List<Employee> allEmp = service.getAllEmp();
		return allEmp;
	}

	@DeleteMapping("/{id}")
	public String DelEmpById(@PathVariable("id") int id) {
		int empById = service.DelEmpById(id);
		if (empById > 0) {
			return "Deleted!";
		} else {
			return "Error!";
		}
	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable("id") int id, @RequestBody Employee employee) {
		Employee update = service.updateEmp(id, employee);
		return update;
	}

	@GetMapping("find/{name}")
	public List<Employee> findByName(@PathVariable("name") String name) {
		return service.getEmpByName(name);
	}

}
