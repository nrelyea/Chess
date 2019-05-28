import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
	
	Piece grid[][] = new Piece[8][8];
	
	boolean whitesTurn;
	
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
		whitesTurn = true;
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
	
	public void makeMove(Move move) {
		// first, vacate landing location of piece, removing any piece that is currently there if necessary
		if(grid[move.endPoint.x][move.endPoint.y] != null) {
			String endSquareTeam = grid[move.endPoint.x][move.endPoint.y].getTeam();
			if(endSquareTeam == "white") {
				for(int i = 0; i < 16; i++) {
					if(whitePiecePositions[i] != null && whitePiecePositions[i].x == move.endPoint.x && whitePiecePositions[i].y == move.endPoint.y) {
						whitePiecePositions[i] = null;
						break;
					}
				}
			}
			else {
				for(int i = 0; i < 16; i++) {
					if(blackPiecePositions[i] != null && blackPiecePositions[i].x == move.endPoint.x && blackPiecePositions[i].y == move.endPoint.y) {
						blackPiecePositions[i] = null;
						break;
					}
				}
			}
		}
		
		// next, update piece position in the appropriate piece position list		
		String startSquareTeam = grid[move.startPoint.x][move.startPoint.y].getTeam();
		if(startSquareTeam == "white") {
			for(int i = 0; i < 16; i++) {
				if(whitePiecePositions[i] != null && whitePiecePositions[i].x == move.startPoint.x && whitePiecePositions[i].y == move.startPoint.y) {
					whitePiecePositions[i] = new Point(move.endPoint.x, move.endPoint.y);
					break;
				}
			}
		}
		else {
			for(int i = 0; i < 16; i++) {
				if(blackPiecePositions[i] != null && blackPiecePositions[i].x == move.startPoint.x && blackPiecePositions[i].y == move.startPoint.y) {
					blackPiecePositions[i] = new Point(move.endPoint.x, move.endPoint.y);
					break;
				}
			}
		}
		
		// then update grid to physically move the piece to its new location
		grid[move.endPoint.x][move.endPoint.y] = grid[move.startPoint.x][move.startPoint.y];
		grid[move.startPoint.x][move.startPoint.y] = null;
		
		// finally, ensure the value of hasMoved for that piece is 'true'
		grid[move.endPoint.x][move.endPoint.y].setHasMoved(true);
	}
	
	public void updateLegalMoves(String team) {
		LegalMoves legalMoves = new LegalMoves();
		if(team == "white") {
			whiteLegalMoves = legalMoves.GenerateLegalMovesForWhite(grid, whitePiecePositions);
		}
		else {
			blackLegalMoves.clear();
			for(int i = 0; i < 16; i++) {
				if(blackPiecePositions[i] != null) {
					blackLegalMoves.add(new Move(blackPiecePositions[i],new Point(blackPiecePositions[i].x,blackPiecePositions[i].y + 1)));
				}
			}
		}
	}
	
	public boolean isKingAttacked(String team) {
		
		AttackEvaluation eval = new AttackEvaluation();
		
		if(team == "white") {
			return eval.SingleSquareAttacked(this, "black", getWhitePiecePositions()[12]);
		}
		else {
			return eval.SingleSquareAttacked(this, "white", getBlackPiecePositions()[12]);
		}
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
	
	public List<Move> getWhiteLegalMoves(){
		return whiteLegalMoves;
	}
	
	public List<Move> getBlackLegalMoves(){
		return blackLegalMoves;
	}
}
