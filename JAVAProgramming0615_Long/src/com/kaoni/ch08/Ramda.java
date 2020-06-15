package com.kaoni.ch08;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ramda {
	public static void main(String[] args) {
		String regex="[0-9]{3,4}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher("123");
		System.out.println(m.matches());
		boolean a = regex.matches("0");
		System.out.println(a);

	}
}
