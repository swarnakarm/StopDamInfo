package com.manish.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindUnique {

	public static void main(String args[]) throws Exception{
		File file = new File("csv/village.csv");
		FileReader fread = new FileReader(file);
		BufferedReader bfread = new BufferedReader(fread);
		String line = bfread.readLine();
		Set<Long> set = new LinkedHashSet<Long>();
		while((line = bfread.readLine())!=null){
			if(!set.add(Long.parseLong(line.split(",")[0]))){
				System.out.println(Long.parseLong(line.split(",")[0]));
			}
		}
		System.out.println(set.size());
		bfread.close();
		fread.close();
	}
}
