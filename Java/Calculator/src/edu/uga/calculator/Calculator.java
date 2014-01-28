package edu.uga.calculator;

public class Calculator {
	public  int exponentOf(String equation, int value) {
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

	public int getExponent(int base, int power) {
		if(power == 0) return 1;
		if( power == 1) return base;
		return base * getExponent(base, power - 1);
	}

	public int subtract(String equation, int value) {
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

	public  int add(String equation, int value) {
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

	public  int divide(String equation, int value) {
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

	public  int multiply(String equation, int value) {
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
