package edu.uga.DP135easy;

import java.text.DecimalFormat;
import java.util.Scanner;

public class DP135easy{
    private static double min;
    private static double max;
    public static void main(String args[]){
        if(args.length != 2){
            System.out.println("Please input two doubles to use for a range");
        }
        try{
            min = Double.parseDouble(args[0]);
            max = Double.parseDouble(args[1]);
        }catch(Exception e){
            System.out.println("Please input two doubles to use for a range");
            return;
        }
        double[] doubles = new double[4];
        int[] operators = new int[3];
        Scanner in = new Scanner(System.in);
        Boolean stop = false;
        double solution = max*4+1;
        double answer = max*4+1;
        while(!stop){
            for (int i = 0; i < 4; i++){
                doubles[i] = min + (int)(Math.random() * ((max - min) + 1));
            }
            String beingEvaluated = new String();
            for(int i = 0; i < 3; i++){
                operators[i] = (int) (Math.random()*4);
            }
            beingEvaluated = doubles[0]+intToOperator(operators[0])+doubles[1]+intToOperator(operators[1])+doubles[2]+intToOperator(operators[2])+
            doubles[3];
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
                        answer = Double.parseDouble(input);
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
    private static double evaluate(String str){
        //System.out.println(str); used for testing
        //No more operators, therefore the final number
    	if(opCount(str) == 0){
            //System.out.println(roundTo2Decimals(Double.parseDouble(str))); testing
            return roundTo2Decimals(Double.parseDouble(str));
        }
    	//a negative number
        if(opCount(str) == 1 && str.startsWith("-")){
            //System.out.println((-1)*roundTo2Decimals(Double.parseDouble(str.substring(1)))); testing
            return (-1)*roundTo2Decimals(Double.parseDouble(str.substring(1)));
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
                    return -1*(Double.parseDouble(str.substring(1,str.lastIndexOf("-")))+
                    Double.parseDouble(str.substring(str.lastIndexOf("-")+1)));
                }
                else{
                	/*this takes the negative number and adds the remaining numbers to it.
                	 * I originally had it subtracting evaluate but that subtracts a negative
                	 * number which is really addition.
                	 */
                    return Double.parseDouble(str.substring(0,str.indexOf("-",1)))+
                    evaluate(str.substring(str.indexOf("-",1)));
                }
            }
        }
        if((str.indexOf("*") < str.indexOf("/") && str.indexOf("*") > 0) || (str.indexOf("*") > 0 && str.indexOf("/") == -1)){
        	//if the current * is at the first operator in the string
            if(lastOp(str.substring(0,str.indexOf("*"))).equals("")){
                //System.out.println("m1"); used for testing
            	//if there are more operators to work with
                if(opCount(str)!=1){
                    return evaluate((Double.parseDouble(str.substring(0,str.indexOf("*")))*
                                    Double.parseDouble(str.substring(str.indexOf("*")+1,str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1)));
                }
                //if the current operator is the only one
                else{
                    return evaluate(Double.parseDouble(str.substring(0,str.indexOf("*")))*Double.parseDouble(str.substring(str.indexOf("*")+1)) + "");
                }
            }
            //if it is NOT the last operator
            else if(!nextOp(str.substring(str.indexOf("*")+1)).equals("")){
                //System.out.println("m2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*"))),str.indexOf("*"))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*")-1)),str.indexOf("*"))+
                                    1,str.indexOf("*")))* Double.parseDouble(str.substring(str.indexOf("*")+1,
                                    str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1))))+
                                    str.substring(str.indexOf(nextOp(str.substring(str.indexOf("*")+1)),str.indexOf("*")+1)));
            }
            else{
                //System.out.println("m3"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*"))))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("*")-1)))+1,str.indexOf("*")))*
                                    Double.parseDouble(str.substring(str.indexOf("*")+1))));
            }
        }
        if((str.indexOf("/") < str.indexOf("*") && str.indexOf("/") > 0) || (str.indexOf("/") > 0 && str.indexOf("*") == -1)){
        	//if the current / is at the first operator in the string
            if(lastOp(str.substring(0,str.indexOf("/"))).equals("")){
                //System.out.println("m1"); used for testing
            	//if there are more operators to work with
                if(opCount(str)!=1){
                    return evaluate((Double.parseDouble(str.substring(0,str.indexOf("/")))/
                                    Double.parseDouble(str.substring(str.indexOf("/")+1,str.indexOf(nextOp(str.substring(str.indexOf("/")+1)),str.indexOf("/")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("/")+1)),str.indexOf("/")+1)));
                }
                //if the current operator is the only one
                else{
                    return evaluate(Double.parseDouble(str.substring(0,str.indexOf("/")))/Double.parseDouble(str.substring(str.indexOf("/")+1)) + "");
                }
            }
            //if it is NOT the last operator
            else if(!nextOp(str.substring(str.indexOf("/")+1)).equals("")){
                //System.out.println("m2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("/"))),str.indexOf("/"))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("/")-1)),str.indexOf("/"))+
                                    1,str.indexOf("/")))/ Double.parseDouble(str.substring(str.indexOf("/")+1,
                                    str.indexOf(nextOp(str.substring(str.indexOf("/")+1)),str.indexOf("/")+1))))+
                                    str.substring(str.indexOf(nextOp(str.substring(str.indexOf("/")+1)),str.indexOf("/")+1)));
            }
            else{
                //System.out.println("m3"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("/"))))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("/")-1)))+1,str.indexOf("/")))/
                                    Double.parseDouble(str.substring(str.indexOf("/")+1))));
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
                    return evaluate((Double.parseDouble(str.substring(0,str.indexOf("+")))+
                                    Double.parseDouble(str.substring(str.indexOf("+")+1,str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+1)));
                }
                else{
                    return evaluate(Double.parseDouble(str.substring(0,str.indexOf("+")))+Double.parseDouble(str.substring(str.indexOf("+")+1)) + "");
                }
            }
            else if(!nextOp(str.substring(str.indexOf("+")+1)).equals("")){
                //System.out.println("add2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("+"))),str.indexOf("+"))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("+")-1)),str.indexOf("+"))+
                                    1,str.indexOf("+")))+
                                    Double.parseDouble(str.substring(str.indexOf("+")+1,str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")
                                    +1))))+
                                    str.substring(str.indexOf(nextOp(str.substring(str.indexOf("+")+1)),str.indexOf("+")+1)));
            }
            else{
                //System.out.println("add3"); used for testing
                return evaluate(str.substring(0,str.indexOf(lastOp(str.substring(0,str.indexOf("+"))))+1)+
                                    (Double.parseDouble(str.substring(str.indexOf(lastOp(str.substring(0,str.indexOf("+")-1)))+1,str.indexOf("+")))+
                                    Double.parseDouble(str.substring(str.indexOf("+")+1))));
            }
        }
        else if((str.indexOf("-") < str.indexOf("+") && str.indexOf("-") > 0) || (str.indexOf("-") > 0 && str.indexOf("+") == -1)){
            if(lastOp(str.substring(0,str.indexOf("-"))).equals("")){
                //System.out.println("sub1"); used for testing
                if(opCount(str)!=1){
                    return evaluate((Double.parseDouble(str.substring(0,str.indexOf("-")))-
                                    Double.parseDouble(str.substring(str.indexOf("-")+1,str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+
                                    1))))+ str.substring(str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+1)));
                }
                else{
                    return evaluate(Double.parseDouble(str.substring(0,str.indexOf("-")))-Double.parseDouble(str.substring(str.indexOf("-")+1)) + "");
                }
            }
            else if(!nextOp(str.substring(str.indexOf("-")+1)).equals("")){
                //System.out.println("sub2"); used for testing
                return evaluate(str.substring(0,str.lastIndexOf(lastOp(str.substring(0,str.indexOf("-"))),str.indexOf("-"))+1)+
                                    (Double.parseDouble(str.substring(str.lastIndexOf(lastOp(str.substring(0,str.indexOf("-")-1)),str.indexOf("-"))+
                                    1,str.indexOf("-")))-Double.parseDouble(str.substring(str.indexOf("-")+1,str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),
                                    str.indexOf("-")+1))))+str.substring(str.indexOf(nextOp(str.substring(str.indexOf("-")+1)),str.indexOf("-")+1)));
            }
            else{
                //System.out.println("sub3"); used for testing
                return evaluate(str.substring(0,str.indexOf(lastOp(str.substring(0,str.indexOf("-"))))+1)+
                                    (Double.parseDouble(str.substring(str.indexOf(lastOp(str.substring(0,str.indexOf("-")-1)))+1,str.indexOf("-")))-
                                    Double.parseDouble(str.substring(str.indexOf("-")+1))));
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
            case 3: return "/";
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
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*") || currentSub.equals("/"))
                count += 1;
        }
        return count;
    }
    
    //returns true if the next operator is the one specified. Used for the leading - cases
    private static Boolean nextOpIs(String str, String op){
        String currentSub;
        for(int i = 0; i < str.length(); i++){
            currentSub = str.substring(i,i+1);
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*") || currentSub.equals("/")){
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
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*") || currentSub.equals("/")){
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
            if(currentSub.equals("-") || currentSub.equals("+") || currentSub.equals("*") || currentSub.equals("/")){
                return currentSub;
            }
        }
        return "";
    }
    
    //rounds division to two decimal places
    public static double roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
    }
}
