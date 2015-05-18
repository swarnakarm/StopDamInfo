package com.manish.misc;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String args[]) {
		List<String> l1 = new ArrayList<String>();
		List<Integer> l2 = new ArrayList<Integer>();
		if(l1.getClass() == l2.getClass()){
			System.out.println("Hi");
		}
	}
}


abstract class GenericNumber{
	private String digits;
	private int base;
	public GenericNumber(){
		
	}
	public GenericNumber(int base){
		this.base = base;
		System.out.println("1");
	}
	public void setDigits(String digits){
		this.digits = digits;
	}
	public abstract int tobase10();
}