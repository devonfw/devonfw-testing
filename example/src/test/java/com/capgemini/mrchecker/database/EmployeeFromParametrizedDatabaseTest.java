package com.capgemini.mrchecker.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.database.core.BasePageDatabase;
import com.capgemini.mrchecker.database.core.base.IDao;
import com.capgemini.mrchecker.database.entity.Employee;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class EmployeeFromParametrizedDatabaseTest extends BaseTest {
	
	private final BasePageDatabase			basePageDatabase	= PageFactory.getPageInstance(ParametrizedDatabase.class);
	private final IDao<Employee, Integer>	employeeDao			= basePageDatabase.createDao(Employee.class, Integer.class);
	
	@Test
	public void shouldFindAllReturnThreeObjects() {
		assertThat(employeeDao.findAll()
				.size(), is(equalTo(3)));
	}
	
	@Test
	public void shouldFirstEmployeeHaveValidData() {
		Employee employee = employeeDao.getOne(1);
		assertThat(employee.getName(), is(equalTo("name1")));
		assertThat(employee.getSurname(), is(equalTo("surname1")));
		assertThat(employee.getSalary(), is(equalTo(100)));
	}
	
	@Test
	public void shouldAddAndRemoveEmployee() {
		Employee employeeToAdd = new Employee(4, "name4", "surname4", 400);
		
		employeeDao.save(employeeToAdd);
		Employee employeeAdded = employeeDao.findOne(4);
		
		assertThat(employeeAdded, is(equalTo(employeeToAdd)));
		
		employeeDao.delete(employeeAdded);
		assertThat(employeeDao.findOne(4), is(equalTo(null)));
	}
}
