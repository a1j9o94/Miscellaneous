import java.util.*;

public class NthIndexTest{
    /*while I was sitting my csci
    class today I thought of a method
    to count the nth index of a substring
    within a string. This is just to test that*/
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String full = input.nextLine();
        String sub = input.nextLine();
        int n = input.nextInt();
        
        System.out.println(nthIndex(full,sub,n));
    }
    
    public static int nthIndex(String str, String sub, int n){
        if(n <= 1){
            return str.indexOf(sub);
        }
        else{
            return str.indexOf(sub,nthIndex(str,sub,n-1)+1);
        }
    }
}