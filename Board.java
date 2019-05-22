import java.awt.Point;

public class Board {
	Piece grid[][] = new Piece[8][8];
	
	public Board() {
		grid[1][1] = new Piece("white","pawn");
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
