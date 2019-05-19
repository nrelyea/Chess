import javax.swing.JFrame;

public class Chess {
	public static void main(String[] args) {
		System.out.println("running");
		Gui gui = new Gui();
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(300,200);
		gui.setVisible(true);
	}
}
