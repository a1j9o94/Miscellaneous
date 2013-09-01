package edu.uga.DP135easy;

import java.util.Scanner;

public class DP135easy{
    private static int min;
    private static int max;
    public static void main(String args[]){
        if(args.length != 2){
            System.out.println("Please input two integers to use for a range");
        }
        try{
            min = Integer.parseInt(args[0]);
            max = Integer.parseInt(args[1]);
        }catch(Exception e){
            System.out.println("Please input two integers to use for a range");
            return;
        }
        int[] integers = new int[4];
        int[] operators = new int[3];
        Scanner in = new Scanner(System.in);
        Boolean stop = false;
        int solution = max*4+1;
        int answer = max*4+1;
        while(!stop){
            for (int i = 0; i < 4; i++){
                integers[i] = min + (int)(Math.random() * ((max - min) + 1));
            }
            String beingEvaluated = new String();
            for(int i = 0; i < 3; i++){
                operators[i] = (int) (Math.random()*3);
            }
            beingEvaluated = integers[0]+intToOperator(operators[0])+integers[1]+intToOperator(operators[1])+integers[2]+intToOperator(operators[2])+
            integers[3];
            System.out.println(beingEvaluated);
            solution = evaluate(beingEvaluated);
            do{
                String input = in.next();
                //String input = (solution + ""); used for testing
                if(input.equalsIgnoreCase("Q")){
                    stop = true;
                    in.close();
                    return;
                }
                else{
                    try{    
                        answer = Integer.parseInt(input);
                    }catch(Exception e){
                        answer = (max*4)+1;
                        //System.out.println("failed"); used for testing
                    }
                }
                if(answer != solution)
                        System.out.println("Incorrect...");
            }while(answer != solution && !stop);
            System.out.println("Correct!");
        }
        in.close();
    }
    private static int evaluate(String str){
        //System.out.println(str); used for testing
        //No more operators, therefore the final number
    	if(opCount(str) == 0){
            return Integer.parseInt(str);
        }
    	//a negative number
        if(opCount(str) == 1 && str.startsWith("-")){
            return (-1)*Integer.parseInt(str.substring(1));
        }
        /*it reduces to a negative at the front while still having other operators.
         * Have to handle each successive operator separately.
         * There will never be a * with a leading negative because multiplication
         * is handled before any subtraction
         */
        if(str.startsWith("-")){
            if(nextOpIs(str.substring(1),"+")){
            	//if doing addition simply move the negative to the back to subtract
                return evaluate(str.substring(str.indexOf("+")+1) + "-" + str.substring(1,str.indexOf("+")));
            }
            if(nextOpIs(str.substring(1),"-")){
                if(opCount(str) == 2){
                	//factor out the -1
                    return -1*(Integer.parseInt(str.substring(1,str.lastIndexOf("-")))+
                    Integer.parseInt(str.substring(str.lastIndexOf("-")+1)));
                }
                else{
                	/*this takes the negative number and adds the remaining numbers to it.
                	 * I originally had it subtracting evaluate but that subtracts a negative
                	 * number which is really addition.
                	 */
                    return Integer.parseInt(str.substring(0,str.indexOf("-",1)))+
                    evaluate(str.substring(str.indexOf("-",1)));
                }
            }
        }
        if(str.contains("*")){
        	//if the current * is at the first operator in the string
            if(lastOp(str.substring(0,str.indexOf("*"))).equals("")){
                //System.out.println("m1"); used for testing
            	//if there are more operators to work with
                if(opCount(str)!=1){
                    return evaluate((Integer.parseInt(str.substring(0,str.indexOf("*")))*
                                    Integer.parseInt(str.substring(str.indexOf("*")+1,str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1)));
                }
                //if the current operator is the only one
                else{
                    return evaluate(Integer.parseInt(str.substring(0,str.indexOf("*")))*Integer.parseInt(str.substring(str.indexOf("*")+1)) + "");
                }
            }
            //if it is NOT the last operator
            else if(!nextOp(str.substring(str.indexOf("*")+1)).equals("")){
                //System.out.println("m2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*"))),str.indexOf("*"))+1)+
                                    (Integer.parseInt(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*")-1)),str.indexOf("*"))+
                                    1,str.indexOf("*")))* Integer.parseInt(str.substring(str.indexOf("*")+1,
                                    str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1))))+
                                    str.substring(str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1)));
            }
            else{
                //System.out.println("m3"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*"))))+1)+
                                    (Integer.parseInt(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*")-1)))+1,str.indexOf("*")))*
                                    Integer.parseInt(str.substring(str.indexOf("*")+1))));
            }
        }
        /*
         * The logic for the + and - if statements test to see which one should be
         * done first.
         * Also I used the same logic and cases for the - and + that I did for the *.
         * I feel like some of the logic is redundant since the - and + will always be the
         * first operator while the * may have to be pulled out of the middle. However
         * I am not completely sure which parts are not needed.
         */
        if((str.indexOf("+") < str.indexOf("-") && str.indexOf("+") > 0) || (str.indexOf("+") > 0 && str.indexOf("-") == -1)){
            if(lastOp(str.substring(0,str.indexOf("+"))).equals("")){
                //System.out.println("add1"); used for testing
                if(opCount(str)!=1){
                    return evaluate((Integer.parseInt(str.substring(0,str.indexOf("+")))+
                                    Integer.parseInt(str.substring(str.indexOf("+")+1,str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+1)));
                }
                else{
                    return evaluate(Integer.parseInt(str.substring(0,str.indexOf("+")))+Integer.parseInt(str.substring(str.indexOf("+")+1)) + "");
                }
            }
            else if(!nextOp(str.substring(str.indexOf("+")+1)).equals("")){
                //System.out.println("add2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("+"))),str.indexOf("+"))+1)+
                                    (Integer.parseInt(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("+")-1)),str.indexOf("+"))+
                                    1,str.indexOf("+")))+
                                    Integer.parseInt(str.substring(str.indexOf("+")+1,str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")
                                    +1))))+
                                    str.substring(str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+1)));
            }
            else{
                //System.out.println("add3"); used for testing
                return evaluate(str.substring(0,str.indexOf(lastOp(str.substring(0,str.indexOf("+"))))+1)+
                                    (Integer.parseInt(str.substring(str.indexOf(lastOp(str.substring(0,str.indexOf("+")-1)))+1,str.indexOf("+")))+
                                    Integer.parseInt(str.substring(str.indexOf("+")+1))));
            }
        }
        else if((str.indexOf("-") < str.indexOf("+") && str.indexOf("-") > 0) || (str.indexOf("-") > 0 && str.indexOf("+") == -1)){
            if(lastOp(str.substring(0,str.indexOf("-"))).equals("")){
                //System.out.println("sub1"); used for testing
                if(opCount(str)!=1){
                    return evaluate((Integer.parseInt(str.substring(0,str.indexOf("-")))-
                                    Integer.parseInt(str.substring(str.indexOf("-")+1,str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+1)));
                }
                else{
                    return evaluate(Integer.parseInt(str.substring(0,str.indexOf("-")))-Integer.parseInt(str.substring(str.indexOf("-")+1)) + "");
                }
            }
            else if(!nextOp(str.substring(str.indexOf("-")+1)).equals("")){
                //System.out.println("sub2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("-"))),str.indexOf("-"))+1)+
                                    (Integer.parseInt(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("-")-1)),str.indexOf("-"))+
                                    1,str.indexOf("-")))-Integer.parseInt(str.substring(str.indexOf("-")+1,str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),
                                    str.indexOf("-")+1))))+str.substring(str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+1)));
            }
            else{
                //System.out.println("sub3"); used for testing
                return evaluate(str.substring(0,str.indexOf(lastOp(str.substring(0,str.indexOf("-"))))+1)+
                                    (Integer.parseInt(str.substring(str.indexOf(lastOp(str.substring(0,str.indexOf("-")-1)))+1,str.indexOf("-")))-
                                    Integer.parseInt(str.substring(str.indexOf("-")+1))));
            }
        }
        //System.out.println("failed"); used for testing
        return 0;
    }
    //takes in an int from 1 to 3 and returns an operator so they can be randomly placed
    private static String intToOperator(int i){
        if(i != 0 && i != 1 && i != 2){
            //System.out.println("incorrect value for int to operator"); used for testing
        }
        switch (i){
            case 0: return "+";
            case 1: return "-";
            case 2: return "*";
            default: System.out.println("incorrect value for int to operator");
                     break;
        }
        return "";
    }
    
    //counts how many operators are left
    private static int opCount(String str){
        int count = 0;
        String currentSub = new String();
        for(int i = 0; i < str.length(); i++){
            currentSub = str.substring(i,i+1);
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*"))
                count += 1;
        }
        return count;
    }
    
    //returns true if the next operator is the one specified. Used for the leading - cases
    private static Boolean nextOpIs(String str, String op){
        String currentSub;
        for(int i = 0; i < str.length(); i++){
            currentSub = str.substring(i,i+1);
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*")){
                if(currentSub.equals(op)){
                    //System.out.println("nextopis match"); used for testing
                    return true;
                }
                else
                    break;
            }
        }
        return false;
    }
    
    //finds what the next operator is
    private static String nextOp(String str){
        for(int i = 0; i < str.length(); i++){
           String currentSub = str.substring(i,i+1);
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*")){
                //System.out.println("match"); used for testing
                return currentSub;
            }
        }
        return "";
    }
    
    //finds the previous operator
    private static String lastOp(String str){
        String currentSub;
        for(int i = str.length(); i > 0; i--){
           currentSub = str.substring(i-1,i);
           //System.out.println(currentSub); used for testing
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*")){
                return currentSub;
            }
        }
        return "";
    }
}
