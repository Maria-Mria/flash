package com.gmail.uran26jupiter;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import com.gmail.uran26jupiter.Group;
import com.gmail.uran26jupiter.Human.GenderType;

public class Main {

	public static void main(String[] args) {

		Student sd1 = new Student(2001, 03, 23, GenderType.MALE, "Andrey", "Niza", 23, "Candidate");
		Student sd2 = new Student(2007, 07, 23, GenderType.MALE, "Vitaly", "Volokh", 18, "Candidate");
		Student sd3 = new Student(1998, 03, 12, GenderType.MALE, "Andrey", "Voznichiy", 119, "Candidate");
		Student sd4 = new Student(1998, 04, 23, GenderType.FEMALE, "Helen", "Voinich", 19, "Candidate");

		Group g = new Group("Candidate");
		System.out.println("              List candidates  in text file" + g.toString());

		try {
			g.addStudent(sd1);
			g.addStudent(sd2);
			g.addStudent(sd3);
			g.addStudent(sd4);

			g.toAddStudentInteractiv();
		} catch (GroupException e) {
			e.printStackTrace();
		}

		g.saveNewGroup(new File(g.getGroupNumber() + ".txt"));

		Student[] recrut = g.armyGroup();
		System.out.println("             List candidates  passed the check");
		for (Student st : recrut) {
			System.out.println(st);
		}

		Group g2 = Group.loadGroupByName(new File("Candidate.txt"));

		System.out.println(g2);

		System.out.println("             List candidates after sort by Last Name " + g.toString());
		Student[] recrut2 = g.armyGroup();
		System.out.println("             List candidates  passed the check");
		for (Student st : recrut2) {
			System.out.println(st);

		}

		System.out.println("             List of candidates after sort by ID");
		Arrays.sort(recrut, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getId() > o2.getId()) {
					return 1;
				}
				if (o1.getId() < o2.getId()) {
					return -1;
				}
				return 0;
			}
		});

		for (Student student2 : recrut) {
			System.out.println(student2);

		}

		System.out.println("             Var#2 sort by ID");
		Student a[] = g.armyGroup();
		Arrays.sort(a, Comparator.comparing(Student::getLastName).thenComparing(Student::getId));
		Arrays.sort(a, Collections.reverseOrder());
		System.out.println(Arrays.toString(a));

	}
}
