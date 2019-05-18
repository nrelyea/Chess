import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class GraphPanel extends JPanel {

	public GraphPanel() {

		super();
		setBackground(new Color(153,204,255));
		setBounds(842, 16, 900, 900);

	}
	
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		g2d.setStroke(new BasicStroke(2));
		
		g2d.setFont(new Font("Tahoma", Font.PLAIN, 24));
		g2d.drawString("I LOVE RINA", 50, 50);
		
		Point startPoint = new Point(100,100);
		int squareSize = 60;
		
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File("src/PiecePics/WhitePawn.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("yo that didnt work homeboy");
		}
		
		DrawGrid(g2d, squareSize, startPoint);
		
		img = resizeImage(img, squareSize);
		g2d.drawImage(img, startPoint.x, startPoint.y, null);
		
	}
	
	
	
	public void DrawGrid(Graphics2D g2d, int squareSize, Point startPoint) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(startPoint.x, startPoint.y, squareSize * 8, squareSize * 8);
		
		//fill in light squares
		g2d.setColor(new Color(255,235,205));
		for(int i = 0; i < 8; i += 2) {
			for(int j = 0; j < 8; j += 2) {
				g2d.fillRect((i * squareSize) + startPoint.x, (j * squareSize) + startPoint.y, squareSize, squareSize);
				g2d.fillRect(((i+1) * squareSize) + startPoint.x, ((j+1) * squareSize) + startPoint.y, squareSize, squareSize);
			}
		}
		
		//fill in dark squares
		g2d.setColor(new Color(160,82,45));
		for(int i = 0; i < 8; i += 2) {
			for(int j = 0; j < 8; j += 2) {
				g2d.fillRect(((i+1) * squareSize) + startPoint.x, (j * squareSize) + startPoint.y, squareSize, squareSize);
				g2d.fillRect((i * squareSize) + startPoint.x, ((j+1) * squareSize) + startPoint.y, squareSize, squareSize);
			}
		}
		
		
		g2d.setColor(Color.BLACK);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				g2d.fillRect((i * squareSize) + startPoint.x, (j * squareSize) + startPoint.y, squareSize, (squareSize / 20));
				g2d.fillRect((i * squareSize) + startPoint.x, (j * squareSize) + startPoint.y, (squareSize / 20), squareSize);
			}
		}
		g2d.fillRect(startPoint.x, (8 * squareSize) + startPoint.y, (8 * squareSize), (squareSize / 20));
		g2d.fillRect((8 * squareSize) + startPoint.x, startPoint.y, (squareSize / 20), (8 * squareSize) + (squareSize / 20));

	}

	public BufferedImage resizeImage(BufferedImage img, int newSize) { 
	    Image tmp = img.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
}
