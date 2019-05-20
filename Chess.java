import javax.swing.JFrame;

public class Chess {
	public static void main(String[] args) {
		print("running");
		Gui gui = new Gui();
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(300,200);
		gui.setVisible(true);
		
		Board brd = new Board();
		
		String type = brd.getGrid()[1][1].getType();
		print("\n" + type);
	}
	
	
	
	
	
	
	
	
	
	
	static void print(String str) {
		System.out.print(str);
	}
}
