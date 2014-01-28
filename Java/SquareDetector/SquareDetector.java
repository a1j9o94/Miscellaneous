/*
My solution to this( https://www.facebook.com/hackercup/problems.php?pid=318555664954399&round=598486203541358) problem.
The one I submitted had an error, that I was able to solve after it was to late to submit.
*/

import java.util.Scanner;

public class SquareDetector{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseCount = 1;
		int totalCases = Integer.parseInt(in.nextLine());
		StringBuilder results = new StringBuilder();
		
		for(; caseCount <= totalCases; caseCount++){
			int gridSize = Integer.parseInt(in.nextLine());
			String[][] grid = new String[gridSize][gridSize];
			int blackCount = 0;
			for (int i = 0; i < gridSize;i++) {
				String line = in.nextLine();
				for (int j = 0; j < gridSize;j++ ) {
					grid[i][j] = line.substring(j,j+1);
					if(grid[i][j].equals("#")){
						blackCount++;
					}
				}
			}
			if((blackCount == 1) || hasSquare(grid, caseCount)){
				results.append("Case #" + caseCount+ " YES");
			}else{
				results.append("Case #" + caseCount+ " NO");
			}
			if(caseCount != totalCases)
				results.append("\n");
		}
		System.out.print(results);
	}

	public static boolean hasSquare(String[][] grid, int caseCount){
		for (int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++){
				if(grid[i][j].equals("#")){
					if(hasNoSurroundingSquares(grid, i, j, caseCount))
						return false;
				}
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++){
				if(grid[i][j].equals("#")){
					return checkForSquare(grid,i,j);
				}
			}
		}

		return false;
	}

	public static boolean checkForSquare(String[][] grid, int startRow, int startColumn){
		int sideLength = 0;
		//gets the length of one of the sides
		for(int i = startRow; i < grid.length; i++ ){
			if(!grid[i][startColumn].equals("#")){
				break;
			}
			sideLength++;
		}
		int colLength = 0;
		//used to stop the count once it is observed that the next row doesn't have squares
		boolean breakHere = false;
		for(int i = startRow; i < grid.length; i++){
			//length of the currrent side
			int currentSide = 0;
			for(int j = startColumn; j < grid.length; j++){
				if(grid[i][j].equals("#")){
					currentSide++;
				}
			}
			//If any of the sides do not equal the first side, it is not a square
			if(currentSide != sideLength){
				return false;
			}
			colLength++;
			//stops check, if the next row doesn't have squares at least to the minimum side length;
			if(i != grid.length-1){
				int lengthCount = 0;
				for(int k = startColumn; k < grid.length; k++){
					if(lengthCount == sideLength){
						break;
					}
					if(!grid[i+1][k].equals("#")){
						breakHere = true;
						break;
					}
					lengthCount++;
				}
			}
			if(breakHere){
				break;
			}
		}
		return colLength == sideLength;
	}

	/*Checks every surrounding point to see if it is a square. If a space is a square and has no
		other surrounding squares. It can't be part of larger square.*/
	public static boolean hasNoSurroundingSquares(String[][] grid, int i, int j, int caseCount){
		if(j == 0 && i == 0){
			if(grid[i][j+1].equals(".") &&
			   grid[i+1][j].equals(".") &&
			   grid[i+1][j].equals(".")){
				return true;
			}
		}else if(i == 0 && j != grid.length-1){
			if(grid[i][j-1].equals(".") &&
			   grid[i][j+1].equals(".") &&
			   grid[i+1][j-1].equals(".") && 
			   grid[i+1][j].equals(".") &&
			   grid[i+1][j+1].equals(".")){
				return true;
			}
		}else if(j == grid.length-1 && i == 0){
			if(grid[i][j-1].equals(".") &&
			   grid[i+1][j-1].equals(".") &&
			   grid[i+1][j].equals(".")){
				return true;
			}
		}else if(i != 0 && i != grid.length-1 && j == 0){
			if(grid[i-1][j].equals(".") && 
			   grid[i- 1][j+1].equals(".") &&
			   grid[i][j+1].equals(".") &&
			   grid[i+1][j].equals(".") &&
			   grid[i+1][j+1].equals(".")){
				return true;
			}
		}else if(j == grid.length-1 && i != 0 && i != grid.length-1){
			if(grid[i+1][j-1].equals(".") &&
			   grid[i+1][j].equals(".") &&
			   grid[i][j-1].equals(".") &&
			   grid[i-1][j-1].equals(".") &&
			   grid[i-1][j].equals(".")){
				return true;
			}
		}else if(j == 0 && i == grid.length-1){
			if(grid[i-1][j].equals(".") &&
			   grid[i-1][j+1].equals(".") &&
			   grid[i][j+1].equals(".")){
				return true;
			}
		}else if(i == grid.length-1 && j != grid.length-1){
			if(grid[i-1][j-1].equals(".") &&
			   grid[i-1][j].equals(".") &&
			   grid[i-1][j+1].equals(".") && 
			   grid[i][j-1].equals(".") &&
			   grid[i][j+1].equals(".")){
				return true;
			}
		}else if(j == grid.length-1 && i == grid.length-1){
			if(grid[i-1][j-1].equals(".") &&
			   grid[i-1][j].equals(".") &&
			   grid[i][j-1].equals(".")){
				return true;
			}
		}else{
			if(grid[i-1][j-1].equals(".") &&
			   grid[i-1][j].equals(".") &&
			   grid[i-1][j+1].equals(".") &&
			   grid[i][j-1].equals(".") &&
			   grid[i][j+1].equals(".") &&
			   grid[i+1][j-1].equals(".") &&
			   grid[i+1][j].equals(".") &&
			   grid[i+1][j+1].equals(".")){
				return true;
			}
		}
		return false;
	}
}