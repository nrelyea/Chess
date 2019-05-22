import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JFrame{
	
	Point startPoint = new Point(100,100);
	int squareSize = 100;
	
	Board brd = new Board();
	
	private JLabel statusbar;
	private GraphicsPanel mainPanel = new GraphicsPanel();
	
	boolean dragging = false;
	
	Point mousePosition = new Point(600,600);
	
	public Gui() {
		super("Chess");
		
		initComponents();
	}
	
	private void initComponents() {
		//GraphicsPanel mainPanel = new GraphicsPanel();
		mainPanel.setBackground(Color.WHITE);
		add(mainPanel, BorderLayout.CENTER);
		
		statusbar = new JLabel("default");
		add(statusbar, BorderLayout.SOUTH);
		
		Handlerclass handler = new Handlerclass();
		mainPanel.addMouseListener(handler);
		mainPanel.addMouseMotionListener(handler);		
		
		String type = brd.getPiece(1,1).getType();
		println(type);
		
		brd.setPiece(3, 3, new Piece("black","knight"));
		println(brd.getPiece(3,3).getType());
	}
	
	public void print(String str) {
		System.out.print(str);
	}
	public void println(String str) {
		System.out.print("\n" + str);
	}
	
	private class Handlerclass implements MouseListener, MouseMotionListener{
		
		// Mouse Listener Methods
		
		public void mouseClicked(MouseEvent event) {
			statusbar.setText(String.format("Clicked at %d,%d", event.getX(), event.getY()));
		}
		public void mousePressed(MouseEvent event) {
			Point startSquare = positionToSquare(new Point(event.getX(),event.getY()));
			statusbar.setText("you pressed down the mouse button at square " + startSquare.x + "," + startSquare.y);			
		}
		
		public void mouseReleased(MouseEvent event) {
			Point endSquare = positionToSquare(new Point(event.getX(),event.getY()));
			statusbar.setText("you released the mouse button at square " + endSquare.x + "," + endSquare.y);			
			dragging = false;
		}
		public void mouseEntered(MouseEvent event) {
			statusbar.setText("you entered the area");
			mainPanel.setBackground(Color.WHITE);
		}
		public void mouseExited(MouseEvent event) {
			statusbar.setText("you exited the area");
			mainPanel.setBackground(Color.LIGHT_GRAY);
		}
		
		// Mouse MOTION event methods
		
		public void mouseDragged(MouseEvent event) {
			statusbar.setText("you are dragging the mouse");
			mousePosition = new Point(event.getX(),event.getY());
			dragging = true;
			
			mainPanel.repaint();
		}
		public void mouseMoved(MouseEvent event) {
			statusbar.setText("you are moving the mouse");
		}
	}
	
	private Point positionToSquare(Point mousePos) {
		
		Point square = mousePos.getLocation();
		square = new Point(square.x - startPoint.x, square.y - startPoint.y);
		if(square.x >= 0 && square.y >= 0) {
			System.out.println(square.x + "," + square.y);
			square = new Point(square.x / squareSize, square.y / squareSize);
			if(square.x < 8 && square.y < 8) {
				return square;
			}
		}		
		return new Point(-1,-1);
		
	}

	class GraphicsPanel extends JPanel {

		GraphicsPanel() {
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(420,420));
        }

        @Override
        public void paintComponent(Graphics g) {
        	Graphics2D g2d = (Graphics2D) g;
    		super.paintComponent(g2d);
    		g2d.setStroke(new BasicStroke(2));
    		
    		g2d.setFont(new Font("Tahoma", Font.PLAIN, 24));
    		g2d.drawString("I LOVE RINA", 50, 50);    	    		
    		
    		BufferedImage img = null;
    		
    		try {
    			img = ImageIO.read(new File("src/PiecePics/WhitePawn.png"));
    		} catch (IOException e) {
    			System.out.println("yo that didnt work homeboy");
    		}
    		
    		DrawGrid(g2d, squareSize, startPoint);
    		
    		DrawPieces(g2d, squareSize, startPoint, brd);
    		
    		/*
    		img = resizeImage(img, squareSize);
    		g2d.drawImage(img, startPoint.x, startPoint.y, null);
    		
    		if(dragging) {
    			g2d.drawImage(img, mousePosition.x - (squareSize / 2), mousePosition.y - (squareSize / 2), null);
    		}
    		*/
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
    		
    		//draw separating lines between squares
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

        public void DrawPieces(Graphics2D g2d, int squareSize, Point startPoint, Board board) {
        	for(int i = 0; i < 8; i++) {
        		for(int j = 0; j < 8; j++) {
        			Piece piece = board.getPiece(i, j);
        			if(piece != null) {
        				System.out.println("Piece at " + i + "," + j);
        				DrawPiece(g2d, i, j, piece);
        			}
        		}
        	}
        }
        
        public void DrawPiece(Graphics2D g2d, int x, int y, Piece piece) {
        	if(piece.getTeam() == "white" && piece.getType() == "pawn") {
        		try {
        			BufferedImage img = ImageIO.read(new File("src/PiecePics/WhitePawn.png"));
        			img = resizeImage(img, squareSize);
        			g2d.drawImage(img, startPoint.x + (x * squareSize), startPoint.y + (y * squareSize), null);
        		} catch (IOException e) {
        			System.out.println("yo that didnt work homeboy");
        		}
        	}
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
}
