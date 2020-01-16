package com.gmail.uran26jupiter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TextComposition {

	private File finalText = new File("result.docx");

	public TextComposition(File first, File second) {
		fileCreator(first, second);
	}

	private void fileCreator(File first, File second) {

		if (!first.exists() || !second.exists()) {
			System.out.println("File doesn't exist");
			return;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < getText(second).length; i++) {
				for (int j = 0; j < getText(first).length; j++) {
					if (getText(first)[j].equalsIgnoreCase(getText(second)[i])) {
						sb.append(getText(first)[j]);
						sb.append(" ");

					}
				}
			}
			try (PrintWriter pw = new PrintWriter(finalText)) {
				finalText.createNewFile();
				pw.println(sb.toString());

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String[] getText(File file) {
		String[] array = null;
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			array = bf.readLine().split(" ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return array;
	}
}
