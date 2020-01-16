package com.gmail.uran26jupiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File copyFrom = new File("copyFrom\\");
		File putIn = new File("recipient");
		String format = "txt";
		
		ToCopy mFF = new ToCopy(format);
		
		if(!copyFrom.isDirectory()) {
			System.out.format("Error!", copyFrom);
			return;
		}
		putIn.mkdirs();
		File[] arrayFiles = copyFrom.listFiles(mFF);
		if(arrayFiles.length==0) {
			System.out.println("Nothing to copy!");
			return;
		}
		for (File file : arrayFiles) {		
		
			try {
				File copy = new File(putIn + "\\" + file.getName());
						copyFiles(file, copy);
				System.out.println("...\\" + file);
			} catch (IOException e) {
				System.out.println("Error!");
			}
		}
		
		System.out.format("Copied ", copyFrom, putIn);
	}

	public static void copyFiles(File in, File out) throws IOException {
		try (FileInputStream fis = new FileInputStream(in); FileOutputStream fos = new FileOutputStream(out)) {
			byte[] array = new byte[1024];
			for (int i = 0; (i = fis.read(array)) > 0;) {
				fos.write(array, 0, i);
			}
		} catch (IOException e) {
			throw e;
		}
	}

}