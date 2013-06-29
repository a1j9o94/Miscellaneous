package edu.uga.reversestring;

public class StringReverser{
	
	public String reverse(String str){
		if (str.length()  == 0)
			return "";
		return str.substring(str.length() - 1) + reverse(str.substring(0, str.length() -1));
	}
	
}
