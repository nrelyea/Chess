import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
	
	Piece grid[][] = new Piece[8][8];
	
	List<Move> whiteLegalMoves = new ArrayList<>();
	List<Move> blackLegalMoves = new ArrayList<>();
	
	// arrays to store piece positions to avoid having to double-loop through the whole grid every time
	// indexes 0-7 are pawns
	// indexes 8 & 15 are rooks
	// indexes 9 & 14 are knights
	// indexes 10 & 13 are bishops
	// index 11 is queen
	// index 12 is king
	Point whitePiecePositions[] = new Point[16];
	Point blackPiecePositions[] = new Point[16];
	
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
		
		whitePiecePositions[0] = new Point(1,6);
		whitePiecePositions[8] = new Point(2,6);
		whitePiecePositions[9] = new Point(3,6);
		whitePiecePositions[10] = new Point(4,6);
		whitePiecePositions[11] = new Point(5,6);
		whitePiecePositions[12] = new Point(6,6);
		
		blackPiecePositions[0] = new Point(1,1);
		blackPiecePositions[8] = new Point(2,1);
		blackPiecePositions[9] = new Point(3,1);
		blackPiecePositions[10] = new Point(4,1);
		blackPiecePositions[11] = new Point(5,1);
		blackPiecePositions[12] = new Point(6,1);	
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
		
		for(int i = 0; i < 8; i++) {
			whitePiecePositions[i] = new Point(i,6);
		}		
		whitePiecePositions[8] = new Point(0,7);
		whitePiecePositions[9] = new Point(1,7);
		whitePiecePositions[10] = new Point(2,7);
		whitePiecePositions[11] = new Point(3,7);
		whitePiecePositions[12] = new Point(4,7);
		whitePiecePositions[13] = new Point(5,7);
		whitePiecePositions[14] = new Point(6,7);
		whitePiecePositions[15] = new Point(7,7);
		
		for(int i = 0; i < 8; i++) {
			blackPiecePositions[i] = new Point(i,1);
		}		
		blackPiecePositions[8] = new Point(0,0);
		blackPiecePositions[9] = new Point(1,0);
		blackPiecePositions[10] = new Point(2,0);
		blackPiecePositions[11] = new Point(3,0);
		blackPiecePositions[12] = new Point(4,0);
		blackPiecePositions[13] = new Point(5,0);
		blackPiecePositions[14] = new Point(6,0);
		blackPiecePositions[15] = new Point(7,0);
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
		this.grid[x][y] = piece;
	}
	
	public Point[] getWhitePiecePositions() {
		return whitePiecePositions;
	}
	
	public void setWhitePiecePosition(Point newPos, int index) {
		this.whitePiecePositions[index] = newPos;
	}
	
	public Point[] getBlackPiecePositions() {
		return blackPiecePositions;
	}
	
	public void setBlackPiecePosition(Point newPos, int index) {
		this.blackPiecePositions[index] = newPos;
	}
}
