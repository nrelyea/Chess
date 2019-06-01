import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LegalMoves {
	
	List<Move> whiteLegalMoves = new ArrayList<>();
	
	public LegalMoves() {
		
	}
	
	// OI MAKE LEGAL MOVE LISTS CLASS-WIDE SCOPE SO THAT WE CAN USE GENERAL PURPOSE VOID METHOD TO VERIFY AND SUBMIT EACH MOVE
	
	// OI! WE NEED ACCESS TO THE isKingAttacked() FUNCTION!!!
	
	public List<Move> GenerateLegalMovesForWhite(Board brd, Point[] whitePiecePositions){
		
		int x, y;
		
		Move newMove = new Move(new Point(0,0), new Point(0,0));
		
		for(int i = 0; i < 16; i++) {
			if(whitePiecePositions[i] != null) {
				x = whitePiecePositions[i].x;
				y = whitePiecePositions[i].y;							
				
				if(brd.grid[x][y].getType() == "pawn") {
					
					if(y >= 1 && brd.grid[x][y-1] == null) {
						VerifyAndSubmitMove(brd, "white", new Move(whitePiecePositions[i], new Point(x,y-1)));
						System.out.println(" y=" + y + ", x=" + x);
						if(y == 6 && brd.grid[x][y-2] == null && !brd.grid[x][y].getHasMoved()) {
							whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x,y-2)));
						}
					}
					if(x >= 1 && y >= 1 && brd.grid[x-1][y-1] != null && brd.grid[x-1][y-1].getTeam() == "black") {
						whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x-1,y-1)));
					}
					if(x <= 6 && y >= 1 && brd.grid[x+1][y-1] != null && brd.grid[x+1][y-1].getTeam() == "black") {
						whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x+1,y-1)));
					}
					
				}
				
				else if (brd.grid[x][y].getType() == "knight") {
					
					
					
				}
			}
			
			
			
			
			//whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(0,0)));
		}
		
		return whiteLegalMoves;
	}
	
	// verify that once the move is made, the friendly king will not be in checked
	public void VerifyAndSubmitMove(Board brd, String team, Move move){
		if(team == "white") {
			

			
			
			
			whiteLegalMoves.add(move);
		}
		else {
			
		}
	}
	
}
