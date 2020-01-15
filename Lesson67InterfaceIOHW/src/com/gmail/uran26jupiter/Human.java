package com.gmail.uran26jupiter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Human {

	private int year;
	private int month;
	private int day;
	private int age;
	private GenderType sex;

	public Human(int year, int month, int day, GenderType sex) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.age = calculateAge(LocalDate.of(year, month, day));
		this.sex = sex;
	}

	public Human() {
		super();

	}

	public enum GenderType {
		MALE, FEMALE
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getAge() {
		return age;
	}

	public GenderType getSex() {
		return sex;
	}

	public void setSex(GenderType sex) {
		this.sex = sex;
	}

	public static int calculateAge(LocalDate birthDate) {
		if ((birthDate != null)) {
			return Period.between(birthDate, LocalDate.now()).getYears();
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, day, month, sex, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		return age == other.age && day == other.day && month == other.month && sex == other.sex && year == other.year;
	}

	@Override
	public String toString() {
		return "Date of Birth - " + year + ":" + month + ":" + day + ", Age= " + age + ", " + "Sex= " + sex + ". ";
	}

}
