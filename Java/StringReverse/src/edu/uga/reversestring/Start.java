package edu.uga.reversestring;

import java.util.Scanner;

public class Start {
	public static void main(String args[]){
		StringReverser string = new StringReverser();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string to reverse");
		System.out.println(string.reverse(in.nextLine()));
		in.close();
	}
}
