import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LegalMoves {
	
	public LegalMoves() {
		
	}
	
	public List<Move> GenerateLegalMovesForWhite(Piece[][] grid, Point[] whitePiecePositions){
		
		List<Move> whiteLegalMoves = new ArrayList<>();
		
		int x, y;
		
		for(int i = 0; i < 16; i++) {
			if(whitePiecePositions[i] != null) {
				x = whitePiecePositions[i].x;
				y = whitePiecePositions[i].y;
				
				if(grid[x][y].getType() == "pawn") {
					
					if(y >= 1 && grid[x][y-1] == null) {
						whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x,y-1)));
						if(y >= 2 && grid[x][y-2] == null && !grid[x][y].getHasMoved()) {
							whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x,y-2)));
						}
					}
					if(x >= 1 && y >= 1 && grid[x-1][y-1] != null && grid[x-1][y-1].getTeam() == "black") {
						whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x-1,y-1)));
					}
					if(x <= 6 && y >= 1 && grid[x+1][y-1] != null && grid[x+1][y-1].getTeam() == "black") {
						whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(x+1,y-1)));
					}
					
				}
				
				else if (grid[x][y].getType() == "knight") {
					
					
					
				}
			}
			
			
			
			
			//whiteLegalMoves.add(new Move(whitePiecePositions[i], new Point(0,0)));
		}
		
		return whiteLegalMoves;
	}
}
