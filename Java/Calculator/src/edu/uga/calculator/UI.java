package edu.uga.calculator;

import java.util.Scanner;

public class UI {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean stop = false;
		int value = 0;
		Calculator calc = new Calculator();
		System.out.println("This Calculator allows for addition, subtraction, multiplication, and exponents of whole numbers\n"
				+ "You can use the answer from the previous computation by simply starting with the sign you want to use.\n"
				+ "To end the program, type \"stop\".\n");
		System.out.println("Enter a simple equation and press enter. ie; a + b or a^b");
		while(!stop){
			String equation = in.nextLine().toLowerCase().trim();
			if(equation.contains("*"))
				value = calc.multiply(equation, value);
			else if(equation.contains("/"))
				value = calc.divide(equation, value);
			else if(equation.contains("+"))
				value = calc.add(equation, value);
			else if(equation.contains("-"))
				value = calc.subtract(equation, value);
			else if(equation.contains("^"))
				value = calc.exponentOf(equation, value);
			else if(equation.contains("stop"))
				stop = true;
			else
				System.out.println("Invalide equation");
		}
		in.close();
	}
}