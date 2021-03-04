package com.capgemini.mrchecker.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
	private int		id;
	private String	name;
	private String	surname;
	private int		salary;
	
	public Employee(int id, String name, String surname, int salary) {
		super();
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
		this.setSalary(salary);
	}
	
	public Employee() {
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + getId() + ", name=" + getName() + ", surname=" + getSurname() + ", salary=" + getSalary() + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
