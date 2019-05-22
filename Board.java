import java.awt.Point;

public class Board {
	Piece grid[][] = new Piece[8][8];
	
	public Board() {
		
	}
	
	public Piece[][] getGrid() {
		return grid;
	}

	  // Setter
	public void setGrid(Piece[][] newGrid) {
		this.grid = newGrid;
	}
	
	public Piece getPiece(int x, int y) {
		return grid[x][y];
	}
	
	public void setPiece(int x, int y, Piece piece) {
		grid[x][y] = piece;
	}
}
