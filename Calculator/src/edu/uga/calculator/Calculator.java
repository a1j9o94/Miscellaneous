package edu.uga.calculator;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean stop = false;
		int value;
		System.out.println("This Calculator allows for addition, subtraction, multiplication, and exponents of whole numbers\n"
				+ "You can use the answer from the previous computation by simply starting with the sign you want to use.\n"
				+ "To end the program, type \"stop\".\n");
		System.out.println("Enter a simple equation and press enter. ie; a + b or a^b");
		while(!stop){
			String equation = in.nextLine().toLowerCase().trim();
			if(equation.contains("*"))
				value = multiply(equation, value);
			else if(equation.contains("/"))
				value = divide(equation, value);
			else if(equation.contains("+"))
				value = add(equation, value);
			else if(equation.contains("-"))
				value = subtract(equation, value);
			else if(equation.contains("^"))
				value = exponentOf(equation, value);
			else if(equation.contains("stop"))
				stop = true;
			else
				System.out.println("Invalide equation");
		}
		in.close();
	}

	private static int exponentOf(String equation, int value) {
		try {
			int base;
			int index = equation.indexOf("^");
			if(index != 0)
				base = Integer.parseInt(equation.substring(0, index).trim());
			else
				base = value;
			int power = Integer.parseInt(equation.substring(index + 1).trim());
			int product = getExponent(base, power);
			System.out.println(product);
			return product;
		} catch (NumberFormatException e) {
			System.out.println("Invalide equation");
			return value;
		}
	}

	private static int getExponent(int base, int power) {
		if(power == 0) return 1;
		if( power == 1) return base;
		return base * getExponent(base, power - 1);
	}

	private static int subtract(String equation, int value) {
		try {
			int subtractedFrom;
			int index = equation.indexOf("-");
			if( index != 0)
				subtractedFrom = Integer.parseInt(equation.substring(0, index).trim());
			else
				subtractedFrom = value;
			int subtracting = Integer.parseInt(equation.substring(index + 1).trim());
			int difference = subtractedFrom - subtracting;
			System.out.println(difference);
			return difference;
		} catch (NumberFormatException e) {
			System.out.println("Invalide equation");
			return value;
		}
		
	}

	private static int add(String equation, int value) {
		try {
			int add1;
			int index = equation.indexOf("+");
			if(index != 0)
				add1 = Integer.parseInt(equation.substring(0, index).trim());
			else
				add1 = value;
			int add2 = Integer.parseInt(equation.substring(index + 1).trim());
			int sum = add1 + add2;
			System.out.println(sum);
			return sum;
		} catch (NumberFormatException e) {
			System.out.println("Invalide equation");
			return value;
		}
	}

	private static int divide(String equation, int value) {
		try {
			int dividend;
			int index = equation.indexOf("/");
			if(index != 0)
				dividend = Integer.parseInt(equation.substring(0, index).trim());
			else
				dividend = value;
			int divisor = Integer.parseInt(equation.substring(index + 1).trim());
			int quotient = dividend / divisor;
			System.out.println(quotient);
			return quotient;
		} catch (NumberFormatException e) {
			System.out.println("Invalide equation");
			return value;
		}
		
	}

	private static int multiply(String equation, int value) {
		try {
			int factor1;
			int index = equation.indexOf("*");
			if(index != 0)
				factor1 = Integer.parseInt(equation.substring(0, index).trim());
			else
				factor1 = value;
			int factor2 = Integer.parseInt(equation.substring(index + 1).trim());
			int product = factor1 * factor2;
			System.out.println(product);
			return product;
		} catch (NumberFormatException e) {
			System.out.println("Invalide equation");
			return value;
		}
		
	}

}
