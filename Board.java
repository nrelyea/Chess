
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
}
