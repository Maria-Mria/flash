package com.gmail.uran26jupiter;

import java.io.File;
import java.io.FileFilter;

public class ToCopy implements FileFilter{
	private String[] arr;

	
	public ToCopy(String... arr) {
		super();
		this.arr = arr;
	}
	private boolean check (String exist) {
		for (String string : arr) {
			if(string.equals(exist)) {
				return true;
			}
			
		}
		return false;	
		
	}
	@Override
	public boolean accept(File pathname) { 
		int pointIndex = pathname.getName().lastIndexOf(".");
		if (pointIndex==-1) {
		return false;
	}
		String ext = pathname.getName().substring(pointIndex +1);
		return check(ext);
	}
	}