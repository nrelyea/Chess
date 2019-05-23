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
		setPiece(5, 6, new Piece("white","queen"));
		setPiece(6, 6, new Piece("white","king"));
		
		setPiece(1, 1, new Piece("black","pawn"));
		setPiece(2, 1, new Piece("black","rook"));
		setPiece(3, 1, new Piece("black","knight"));		
		setPiece(4, 1, new Piece("black","bishop"));
		setPiece(5, 1, new Piece("black","queen"));
		setPiece(6, 1, new Piece("black","king"));
	}
	
	public void setStartingGrid() {
		
		for(int i = 0; i < 8; i++) {
			setPiece(i, 6, new Piece("white","pawn"));	
		}				
		setPiece(0, 7, new Piece("white","rook"));		
		setPiece(1, 7, new Piece("white","knight"));			
		setPiece(2, 7, new Piece("white","bishop"));
		setPiece(3, 7, new Piece("white","queen"));
		setPiece(4, 7, new Piece("white","king"));
		setPiece(5, 7, new Piece("white","bishop"));
		setPiece(6, 7, new Piece("white","knight"));
		setPiece(7, 7, new Piece("white","rook"));	
		
		for(int i = 0; i < 8; i++) {
			setPiece(i, 1, new Piece("black","pawn"));	
		}				
		setPiece(0, 0, new Piece("black","rook"));		
		setPiece(1, 0, new Piece("black","knight"));			
		setPiece(2, 0, new Piece("black","bishop"));
		setPiece(3, 0, new Piece("black","queen"));
		setPiece(4, 0, new Piece("black","king"));
		setPiece(5, 0, new Piece("black","bishop"));
		setPiece(6, 0, new Piece("black","knight"));
		setPiece(7, 0, new Piece("black","rook"));	
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
