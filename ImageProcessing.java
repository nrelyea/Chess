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
			
			imageBank[6] = resizeImage(ImageIO.read(new File("src/PiecePics/BlackPawn.png")), squareSize);
			
			
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
