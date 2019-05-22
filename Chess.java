import java.awt.Point;

import javax.swing.JFrame;

public class Chess {
	public static void main(String[] args) {
		print("running");
		Gui gui = new Gui();
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(300,200);
		gui.setVisible(true);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	static void print(String str) {
		System.out.print(str);
	}
	static void println(String str) {
		System.out.print("\n" + str);
	}
}
