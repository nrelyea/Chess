import java.awt.Point;
import java.util.Arrays;

public class AttackEvaluation {	
	
	public AttackEvaluation() {
		
	}
	
	public boolean[][] AllSquaresAttacked(Board brd, String team){
		
		Piece[][] grid = brd.getGrid();
		
		boolean[][] squaresAttacked = new boolean[8][8];
		
		//indices for tracking attack paths
		int x, y;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {				
				if(grid[i][j] != null && grid[i][j].getTeam() == team) {
					
					// determine PAWN attacks
					if(grid[i][j].getType() == "pawn") {
						
						if(team == "white") {
							if(i > 0 && j > 0) {
								squaresAttacked[i-1][j-1] = true;
							}
							if(i < 7 && j > 0) {
								squaresAttacked[i+1][j-1] = true;
							}
						}
						else {
							if(i > 0 && j < 7) {
								squaresAttacked[i-1][j+1] = true;
							}
							if(i < 7 && j < 7) {
								squaresAttacked[i+1][j+1] = true;
							}
						}
						
					}
					
					// determine KNIGHT attacks
					if(grid[i][j].getType() == "knight") {
						
						if(i >= 1 && j >= 2) {
							squaresAttacked[i-1][j-2] = true;
						}
						if(i <= 6 && j >= 2) {
							squaresAttacked[i+1][j-2] = true;
						}
						if(i <= 5 && j >= 1) {
							squaresAttacked[i+2][j-1] = true;
						}
						if(i <= 5 && j <= 6) {
							squaresAttacked[i+2][j+1] = true;
						}
						if(i <= 6 && j <= 5) {
							squaresAttacked[i+1][j+2] = true;
						}
						if(i >= 1 && j <= 5) {
							squaresAttacked[i-1][j+2] = true;
						}
						if(i >= 2 && j <= 6) {
							squaresAttacked[i-2][j+1] = true;
						}
						if(i >= 3 && j >= 1) {
							squaresAttacked[i-2][j-1] = true;
						}
						
					}
					
					// determine BISHOP attacks
					if(grid[i][j].getType() == "bishop") {
						x=i+1;
						y=j+1;
						while(x < 8 && y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y++;
						}
						x=i-1;
						y=j+1;
						while(x > -1 && y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y++;
						}
						x=i-1;
						y=j-1;
						while(x > -1 && y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y--;
						}
						x=i+1;
						y=j-1;
						while(x < 8 && y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y--;
						}
						
						
					}
					
					// determine ROOK attacks
					if(grid[i][j].getType() == "rook") {
						x=i+1;
						y=j;
						while(x < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;							
						}
						x=i-1;
						while(x > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;							
						}
						x=i;
						y=j+1;
						while(y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							y++;							
						}
						y=j-1;
						while(y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							y--;							
						}
						
					}
					
					// determine QUEEN attacks
					if(grid[i][j].getType() == "queen") {
						
						// diagonal
						x=i+1;
						y=j+1;
						while(x < 8 && y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y++;
						}
						x=i-1;
						y=j+1;
						while(x > -1 && y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y++;
						}
						x=i-1;
						y=j-1;
						while(x > -1 && y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y--;
						}
						x=i+1;
						y=j-1;
						while(x < 8 && y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y--;
						}
						
						// horizontal / vertical
						x=i+1;
						y=j;
						while(x < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x++;							
						}
						x=i-1;
						while(x > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							x--;							
						}
						x=i;
						y=j+1;
						while(y < 8) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							y++;							
						}
						y=j-1;
						while(y > -1) {
							squaresAttacked[x][y] = true;
							if(grid[x][y] != null) {
								break;
							}
							y--;							
						}										
					}
					
					// determine KING attacks
					if(grid[i][j].getType() == "king") {
						
						if(i >= 1 && j >= 1) {
							squaresAttacked[i-1][j-1] = true;
						}
						if(j >= 1) {
							squaresAttacked[i][j-1] = true;
						}
						if(i <= 6 && j >= 1) {
							squaresAttacked[i+1][j-1] = true;
						}
						if(i <= 6) {
							squaresAttacked[i+1][j] = true;
						}
						if(i <= 6 && j <= 6) {
							squaresAttacked[i+1][j+1] = true;
						}
						if(j <= 6) {
							squaresAttacked[i][j+1] = true;
						}
						if(i >= 1 && j <= 6) {
							squaresAttacked[i-1][j+1] = true;
						}
						if(i >= 1) {
							squaresAttacked[i-1][j] = true;
						}
						
					}
					
				}
				
			}
		}
		
		return squaresAttacked;
	}
	
	public boolean SingleSquareAttacked(Board brd, String team, Point square){
		
		Piece[][] grid = brd.getGrid();
		
		int squareX = square.x;
		int squareY = square.y;
		
		//indices for tracking attack paths
		int x, y;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(grid[i][j] != null && grid[i][j].getTeam() == team) {
					
					// determine if PAWN is attacking the square
					if(grid[i][j].getType() == "pawn") {
						
						if(team == "white") {
							if(i-1 == squareX && j-1 == squareY) {
								return true;
							}
							if(i+1 == squareX && j-1 == squareY) {
								return true;
							}
						}
						else {
							if(i-1 == squareX && j+1 == squareY) {
								return true;
							}
							if(i+1 == squareX && j+1 == squareY) {
								return true;
							}
						}
						
					}
					
										
					// determine if KNIGHT is attacking the square
					else if(grid[i][j].getType() == "knight") {
						
						if(i-1 == squareX && j-2 == squareY) {
							return true;
						}
						if(i+1 == squareX && j-2 == squareY) {
							return true;
						}
						if(i+2 == squareX && j-1 == squareY) {
							return true;
						}
						if(i+2 == squareX && j+1 == squareY) {
							return true;
						}
						if(i+1 == squareX && j+2 == squareY) {
							return true;
						}
						if(i-1 == squareX && j+2 == squareY) {
							return true;
						}
						if(i-2 == squareX && j+1 == squareY) {
							return true;
						}
						if(i-2 == squareX && j-1 == squareY) {
							return true;
						}
						
					}
					
					// determine if BISHOP is attacking the square
					else if(grid[i][j].getType() == "bishop") {
						x=i+1;
						y=j+1;
						while(x < 8 && y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y++;
						}
						x=i-1;
						y=j+1;
						while(x > -1 && y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y++;
						}
						x=i-1;
						y=j-1;
						while(x > -1 && y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y--;
						}
						x=i+1;
						y=j-1;
						while(x < 8 && y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y--;
						}
						
						
					}
					
					// determine if ROOK is attacking the square
					else if(grid[i][j].getType() == "rook") {
						x=i+1;
						y=j;
						while(x < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;							
						}
						x=i-1;
						while(x > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;							
						}
						x=i;
						y=j+1;
						while(y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							y++;							
						}
						y=j-1;
						while(y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							y--;							
						}
						
					}
					
					// determine if QUEEN is attacking the square
					else if(grid[i][j].getType() == "queen") {
						
						// diagonal
						x=i+1;
						y=j+1;
						while(x < 8 && y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y++;
						}
						x=i-1;
						y=j+1;
						while(x > -1 && y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y++;
						}
						x=i-1;
						y=j-1;
						while(x > -1 && y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;
							y--;
						}
						x=i+1;
						y=j-1;
						while(x < 8 && y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;
							y--;
						}
						
						// horizontal / vertical
						x=i+1;
						y=j;
						while(x < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x++;							
						}
						x=i-1;
						while(x > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							x--;							
						}
						x=i;
						y=j+1;
						while(y < 8) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							y++;							
						}
						y=j-1;
						while(y > -1) {
							if(x == squareX && y == squareY) {
								return true;
							}
							if(grid[x][y] != null) {
								break;
							}
							y--;							
						}										
					}
					
					// determine if KING is attacking the square
					else if(grid[i][j].getType() == "king") {						
						if(Math.abs(i-squareX) <= 1 && Math.abs(j-squareY) <= 1) {
							return true;
						}						
					}
					
				}
				

			}
		}
		return false;
	}
}
