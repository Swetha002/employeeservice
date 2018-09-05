package com.rest.tutorial.spring.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.tutorial.spring.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long save(Employee emp) {
		sessionFactory.getCurrentSession().save(emp);
		return emp.getId();
	}

	@Override
	public Employee get(long id) {
		return sessionFactory.getCurrentSession().get(Employee.class, id);
	}

	@Override
	public List<Employee> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		Query<Employee> query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void update(long id, Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		Employee emp_existing = session.byId(Employee.class).load(id);
		emp_existing.setName(emp.getName());
		emp_existing.setGender(emp.getGender());
		emp_existing.setJoinDate(emp.getJoinDate());
		emp_existing.setSalary(emp.getSalary());

		session.flush();
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.byId(Employee.class).load(id);
		session.delete(employee);
	}

}