package com.gmail.uran26jupiter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.gmail.uran26jupiter.Human.GenderType;

public class Group implements Voencom {

	private Student[] person = new Student[10];
	private int num = 0;
	private String groupNumber;

	public Group(Student[] person) {
		super();
		this.person = person;
	}

	public Group(String groupNumber) {
		super();
		this.groupNumber = groupNumber;
	}

	public Group() {
		super();

	}

	public Student[] getPerson() {
		return person;
	}

	public void setPerson(Student[] person) {
		this.person = person;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	// строка для интерактивного добавления
	public String addStringLine(String s) {
		String stringLine = null;
		boolean b = true;
		while (b) {
			try {
				stringLine = JOptionPane.showInputDialog(s);
				b = false;
				if (stringLine == null || stringLine.isEmpty()) {
					b = true;
				}

			} catch (NullPointerException e) {
				System.out.println("String cannot be empty");
				b = true;
			}
		}
		return stringLine;

	}

	// строка int для интерактивного добавления
	public int addIntLine(String s) {
		int intLine = 0;
		boolean b = true;
		while (b) {
			try {
				intLine = Integer.valueOf(JOptionPane.showInputDialog(s));
				b = false;

			} catch (NumberFormatException e) {
				System.out.println("number format error");
				b = true;
			}
		}
		return intLine;
	}

	public void toAddStudentInteractiv() throws GroupException {
		Scanner sc = new Scanner(System.in);
		int a = Integer.valueOf(JOptionPane.showInputDialog("How many students would you add? - " + person.length));

		for (int i = 1; i <= a; i++) {
			try {
				String fullName = addStringLine("Input full name of the student # " + i);
				String[] fN = fullName.split(" ");
				String firstName = fN[0];
				String lastName = fN[1];
				String birthdayDate = addStringLine("Input birthday date. Format dd MM yyyy");
				String[] bD = birthdayDate.split(" ");
				int yearDate = Integer.parseInt(bD[2]);
				int monthDate = Integer.parseInt(bD[1]);
				int dayDate = Integer.parseInt(bD[0]);
				GenderType gender = addSex();
				String satisfy = this.groupNumber;
				int id = addIntLine("Input record number");
				Student st = new Student(yearDate, monthDate, dayDate, gender, firstName, lastName, id, satisfy);

				addStudent(st);

			} catch (DateTimeException e) {
				System.out.println("Invalid value for month of the year");
				i--;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ArrayIndexOutOfBoundsException");
				i--;
			}
		}
	}

	public void addStudent(Student student) {
		try {
			if (num > 9) {

			}
			for (int i = 0; i < person.length; i++) {
				if (person[i] == null) {
					person[i] = student;
					num++;
					System.out.println("The candidat " + student.toString());
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error, don't enter more then 10 people");

		}
	}

	public void deleteStudent(Student student) {
		for (int i = 0; i < person.length; i++) {
			if (person[i] != null && person[i].equals(student)) {
				person[i] = null;
				num--;
				System.out.println("The candidat " + student.toString() + "  was deleted from group .");
				// break;
			}
		}
	}

	public Optional<Student> findByLastName(String lastName) {
		for (Student student : person) {
			if (null != student && student.getLastName().equals(lastName)) {
				return Optional.of(student);

			}
		}
		return Optional.empty();

	}

	@Override
	public String toString() {
		Arrays.sort(person, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if (o1 == null && o2 == null) {
					return 0;
				}
				if (o1 == null) {
					return 1;
				}
				if (o2 == null) {
					return -1;
				}
				return o1.getLastName().compareTo(o2.getLastName());
			}
		});

		StringBuilder personAfterSort = new StringBuilder();
		for (Student student : person) {
			if (student != null) {
				personAfterSort.append("\n").append(student.toString());
			}
		}
		return personAfterSort.toString();

	}

	public static GenderType addSex() {
		GenderType[] sexOption = { Human.GenderType.FEMALE, Human.GenderType.MALE };
		GenderType gender = (GenderType) JOptionPane.showInputDialog(null, "Select gender student", "gender",
				JOptionPane.QUESTION_MESSAGE, null, sexOption, sexOption[1]);
		return gender;

	}

	@Override
	public Student[] armyGroup() {
		int count = 0;

		for (int i = 0; i < person.length; i++) {
			if (person[i] != null) {
				if ((person[i].getAge() >= 18) && (person[i].getSex() == Human.GenderType.MALE)) {
					count++;
				}
			}
		}

		Student[] guys = new Student[count];
		int point = 0;
		for (int i = 0; i < person.length; i++) {
			if (person[i] != null) {
				if ((person[i].getAge() >= 18) && (person[i].getSex() == Human.GenderType.MALE)) {
					guys[point] = person[i];
					point++;
				}
			}
		}

		return guys;

	}

//
	public void saveNewGroup(File file) {
		try (PrintWriter list = new PrintWriter(file)) {
			list.println(groupNumber);

			for (int i = 0; i < person.length; i++) {
				if (person[i] != null) {
					list.println(person[i].getLastName() + "," + person[i].getFirstName() + "," + person[i].getAge()
							+ "," + person[i].getSex() + "," + person[i].getId() + "," + person[i].getSatisfy());
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Group loadGroupByName(File file) {
		Group groupNew = new Group();
		try (BufferedReader list = new BufferedReader(new FileReader(file))) {

			String text = "";
			if ((text = list.readLine()) != null)
				groupNew.setGroupNumber(text);
			while ((text = list.readLine()) != null) {

				try {

					String[] fullName = text.split(" ");
					String firstName = fullName[0];
					String lastName = fullName[1];

					String[] bD = text.split(" ");
					int yearDate = Integer.parseInt(bD[4]);
					int monthDate = Integer.parseInt(bD[3]);
					int dayDate = Integer.parseInt(bD[2]);
					GenderType gender = addSex();
					String satisfy = groupNew.groupNumber;
					int id = Integer.parseInt(bD[5]);
					Student st = new Student(yearDate, monthDate, dayDate, gender, firstName, lastName, id, satisfy);

					groupNew.addStudent(st);

				} catch (DateTimeException e) {
					e.printStackTrace();

				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();

				}
			}
			return groupNew;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
