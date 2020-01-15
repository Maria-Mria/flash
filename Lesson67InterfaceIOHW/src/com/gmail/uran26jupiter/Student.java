package com.gmail.uran26jupiter;

import java.util.Objects;

public class Student extends Human implements Comparable<Student> {

	private String firstName;
	private String lastName;
	private int id;
	private String satisfy;

	public Student(int year, int month, int day, GenderType sex, String firstName, String lastName, int id,
			String satisfy) {
		super(year, month, day, sex);
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.satisfy = satisfy;
	}

	public Student() {
		super();

	}

	public String getSatisfy() {
		return satisfy;
	}

	public void setSatisfy(String satisfy) {
		this.satisfy = satisfy;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student " + this.firstName + " " + this.lastName + ", id=  " + this.id + ", " + super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(firstName, lastName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);

	}

	@Override
	public int compareTo(Student o) {
		if (o.id != this.id) {
			return this.id - o.id;
		} else {
			return this.lastName.toLowerCase().compareTo(o.lastName.toLowerCase());
		}
	}

}