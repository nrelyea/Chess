import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing {

	int squareSize;
	
	public ImageProcessing(int size) {
		squareSize = size;
	}
	 
	public BufferedImage[] buildImageBank() {
		BufferedImage imageBank[] = new BufferedImage[12];
		
		try {
			imageBank[0] = resizeImage(ImageIO.read(new File("src/PiecePics/WhitePawn.png")), squareSize);
			imageBank[1] = resizeImage(ImageIO.read(new File("src/PiecePics/WhiteRook.png")), squareSize);
			imageBank[2] = resizeImage(ImageIO.read(new File("src/PiecePics/WhiteKnight.png")), squareSize);
			imageBank[3] = resizeImage(ImageIO.read(new File("src/PiecePics/WhiteBishop.png")), squareSize);
			imageBank[4] = resizeImage(ImageIO.read(new File("src/PiecePics/WhiteQueen.png")), squareSize);
			imageBank[5] = resizeImage(ImageIO.read(new File("src/PiecePics/WhiteKing.png")), squareSize);
			
			imageBank[6] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackPawn.png")), squareSize);
			imageBank[7] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackRook.png")), squareSize);
			imageBank[8] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackKnight.png")), squareSize);
			imageBank[9] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackBishop.png")), squareSize);
			imageBank[10] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackQueen.png")), squareSize);
			imageBank[11] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackKing.png")), squareSize);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageBank;
	}
	
	public int getImageIndex(Piece piece) {
		int index = 0;
		switch(piece.getType()) {
			case "pawn":
				index = 0;
				break;
			case "rook":
				index = 1;
				break;
			case "knight":
				index = 2;
				break;
			case "bishop":
				index = 3;
				break;
			case "queen":
				index = 4;
				break;
			case "king":
				index = 5;
				break;
		}
		if(piece.getTeam() == "white") {
			return index;
		}
		else {
			return (index + 6);
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
