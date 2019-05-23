import java.awt.Point;

public class Board {
	Piece grid[][] = new Piece[8][8];
	
	public Board() {
		
	}
	
	public void setTestingGrid() {
		setPiece(1, 6, new Piece("white","pawn"));			
		setPiece(2, 6, new Piece("white","rook"));		
		setPiece(3, 6, new Piece("white","knight"));			
		setPiece(4, 6, new Piece("white","bishop"));
		
		setPiece(1, 1, new Piece("black","pawn"));
		setPiece(2, 1, new Piece("black","rook"));
		setPiece(3, 1, new Piece("black","knight"));		
		setPiece(4, 1, new Piece("black","bishop"));
	}
	
	
	
	// --- Getters and Setters
	
	public Piece[][] getGrid() {
		return grid;
	}

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
