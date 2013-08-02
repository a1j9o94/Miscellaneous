public class RecursiveBottles {
	
	public static void main(String args[]){
		int beers = 99;
//		entire song is printed as one long string
	    System.out.println(bottles99(beers));		
	}
	
	public static String bottles99(int beers){
		String word = new String();
		String word2 = new String();
//		grammer check
		if( beers == 1);
		    word = " bottle";
		if( beers != 1)
			word = " bottles";
		if(beers-1 == 1)
			word2 = " bottle";
		if(beers-1 != 1)
		    word2 = " bottles";
//		end condition
		if (beers == 0) return "";
//		returns this verse and the next verse
		return String.valueOf(beers) + word + " of beer on the wall " + String.valueOf(beers) + word + " of beer. Take one down pass it around " +
		String.valueOf(beers - 1) + word2 + " of beer on the wall." + "\n" + bottles99( beers - 1);
	}
}
