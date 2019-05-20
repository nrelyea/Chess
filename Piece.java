
public class Piece {
	
	String team;
	String type;
	
	public Piece(String pieceTeam, String pieceType) {
		team = pieceTeam;
		type = pieceType;
	}
	
	
	
	
	
	
	
	
	public String getTeam() {
		return team;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String newType) {
		this.type = newType;
	}
	
}
