package com.rest.tutorial.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.tutorial.spring.dao.EmployeeDao;
import com.rest.tutorial.spring.model.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	@Transactional
	@Override
	public long save(Employee employee) {
		return empDao.save(employee);
	}

	@Override
	public Employee get(long id) {
		return empDao.get(id);
	}

	@Override
	public List<Employee> list() {
		return empDao.list();
	}

	@Transactional
	@Override
	public void update(long id, Employee employee) {
		empDao.update(id, employee);
	}

	@Transactional
	@Override
	public void delete(long id) {
		empDao.delete(id);
	}

}