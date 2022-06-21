package com.cognixia.jump.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	// What team they play for
	private String team;
	
	
	@Column(name = "fname")
	private String firstName;
	
	@Column(name = "lname")
	private String lastName;
	
	private Double salary;
	
	private LocalDate dob;
	
	// The position they are ex. Power forward, center, point guard, etc. 
	private String position;
	
	// Jersey number
	@Column( columnDefinition = "varchar(2)")
	private Integer jNumber;
	
	
	
	public Player() {
		
	}

	

	public Player(Integer id, String team, String firstName, String lastName, Double salary, LocalDate dob,
			String position, Integer jNumber) {
		super();
		this.id = id;
		this.team = team;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.dob = dob;
		this.position = position;
		this.jNumber = jNumber;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getTeam() {
		return team;
	}



	public void setTeam(String team) {
		this.team = team;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Double getSalary() {
		return salary;
	}



	public void setSalary(Double salary) {
		this.salary = salary;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public Integer getJnumber() {
		return jNumber;
	}



	public void setJnumber(Integer jNumber) {
		this.jNumber = jNumber;
	}



	@Override
	public String toString() {
		return "Player [id=" + id + ", team=" + team + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", dob=" + dob + ", position=" + position + ", jNumber=" + jNumber + "]";
	}
	
	
	
}
