import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
	
	Piece grid[][] = new Piece[8][8];
	
	int boardValue;
	
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
		
		AddPiece("white", "pawn", 1, 6);
		AddPiece("white", "rook", 2, 6);
		AddPiece("white", "knight", 3, 6);
		AddPiece("white", "bishop", 4, 6);
		AddPiece("white", "queen", 5, 6);
		AddPiece("white", "king", 6, 6);
		
		AddPiece("black", "pawn", 1, 1);
		AddPiece("black", "rook", 2, 1);
		AddPiece("black", "knight", 3, 1);
		AddPiece("black", "bishop", 4, 1);
		AddPiece("black", "queen", 5, 1);
		AddPiece("black", "king", 6, 1);
		
		AddPiece("black", "bishop", 2, 2);
		AddPiece("white", "pawn", 5, 5);
		
	}
	
	public void setStartingGrid() {
		
		for(int i = 0; i < 8; i++) {
			AddPiece("white", "pawn", i, 6);	
		}				
		AddPiece("white", "rook", 0, 7);		
		AddPiece("white", "knight", 1, 7);			
		AddPiece("white", "bishop", 2, 7);
		AddPiece("white", "queen", 3, 7);
		AddPiece("white", "king", 4, 7);
		AddPiece("white", "bishop", 5, 7);
		AddPiece("white", "knight", 6, 7);
		AddPiece("white", "rook", 7, 7);
		
		for(int i = 0; i < 8; i++) {
			AddPiece("black", "pawn", i, 1);	
		}				
		AddPiece("black", "rook", 0, 0);		
		AddPiece("black", "knight", 1, 0);			
		AddPiece("black", "bishop", 2, 0);
		AddPiece("black", "queen", 3, 0);
		AddPiece("black", "king", 4, 0);
		AddPiece("black", "bishop", 5, 0);
		AddPiece("black", "knight", 6, 0);
		AddPiece("black", "rook", 7, 0);		
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
		
		updateBoardValue();
	}
	
	public void updateLegalMoves(String team) {
		LegalMoves legalMoves = new LegalMoves();
		if(team == "white") {
			whiteLegalMoves = legalMoves.GenerateLegalMovesForWhite(this, whitePiecePositions);
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
	
	public void updateBoardValue() {
		int whiteValue = 0;
		for (int i = 0; i < 16; i++) {
			if(whitePiecePositions[i] != null) {
				whiteValue += grid[whitePiecePositions[i].x][whitePiecePositions[i].y].getValue();
			}
		}
		int blackValue = 0;
		for (int i = 0; i < 16; i++) {
			if(blackPiecePositions[i] != null) {
				blackValue += grid[blackPiecePositions[i].x][blackPiecePositions[i].y].getValue();
			}
		}
		
		boardValue = whiteValue - blackValue;
	}
	
	public boolean isKingAttacked(String team) {
		
		AttackEvaluation eval = new AttackEvaluation();
		
		if(team == "white") {
			return eval.SingleSquareAttacked(this, "black", getALLWhitePiecePositions()[12]);
		}
		else {
			return eval.SingleSquareAttacked(this, "white", getALLBlackPiecePositions()[12]);
		}
	}
	
	public void AddPiece(String team, String type, int x, int y) {
		setPiece(x, y, new Piece(team, type));
		if(type == "pawn") {
			for(int i = 0; i < 8; i++) {
				if(team == "white" && whitePiecePositions[i] == null) {
					whitePiecePositions[i] = new Point(x,y);
					break;
				}
				else if(team == "black" && blackPiecePositions[i] == null) {
					blackPiecePositions[i] = new Point(x,y);
					break;
				}
			}
		}
		else if(type == "knight") {
			if(team == "white" && whitePiecePositions[9] == null) {
				whitePiecePositions[9] = new Point(x,y);
			}
			else if(team == "white" && whitePiecePositions[14] == null) {
				whitePiecePositions[14] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[9] == null) {
				blackPiecePositions[9] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[14] == null) {
				blackPiecePositions[14] = new Point(x,y);
			}			
		}
		else if(type == "rook") {
			if(team == "white" && whitePiecePositions[8] == null) {
				whitePiecePositions[8] = new Point(x,y);
			}
			else if(team == "white" && whitePiecePositions[15] == null) {
				whitePiecePositions[15] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[8] == null) {
				blackPiecePositions[8] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[15] == null) {
				blackPiecePositions[15] = new Point(x,y);
			}			
		}
		else if(type == "bishop") {
			if(team == "white" && whitePiecePositions[10] == null) {
				whitePiecePositions[10] = new Point(x,y);
			}
			else if(team == "white" && whitePiecePositions[13] == null) {
				whitePiecePositions[13] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[10] == null) {
				blackPiecePositions[10] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[13] == null) {
				blackPiecePositions[13] = new Point(x,y);
			}			
		}
		else if(type == "queen") {
			if(team == "white" && whitePiecePositions[11] == null) {
				whitePiecePositions[11] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[11] == null) {
				blackPiecePositions[11] = new Point(x,y);
			}			
		}
		else if(type == "king") {
			if(team == "white" && whitePiecePositions[12] == null) {
				whitePiecePositions[12] = new Point(x,y);
			}
			else if(team == "black" && blackPiecePositions[12] == null) {
				blackPiecePositions[12] = new Point(x,y);
			}			
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
	
	public Point[] getALLWhitePiecePositions() {
		return whitePiecePositions;
	}
	
	public void setALLWhitePiecePositions(Point[] positions) {
		this.whitePiecePositions = positions;
	}
	
	public void setWhitePiecePosition(Point newPos, int index) {
		this.whitePiecePositions[index] = newPos;
	}
	
	public Point[] getALLBlackPiecePositions() {
		return blackPiecePositions;
	}
	
	public void setALLBlackPiecePositions(Point[] positions) {
		this.blackPiecePositions = positions;
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
	
	public int getBoardValue() {
		return boardValue;
	}
}
