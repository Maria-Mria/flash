package com.gmail.uran26jupiter;

import java.io.File;

import javax.naming.spi.DirStateFactory.Result;

public class Main {

	public static void main(String[] args) {

		File first = new File("A.txt");
		File second = new File("B.txt");
		TextComposition finalText = new TextComposition(first, second);
	}

}
