package base;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public abstract class Utils {

	public static ImageIcon getResizeImage(String imageName, int width, int height) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(getPath(imageName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizeImage);
	}

	public static String getPath(String fileName) {
		return ("data/" + fileName);
	}

	public static Image getImage(String fileName) {
		return new ImageIcon(getPath(fileName)).getImage();
	}

	public static Font getFont(String fontName, int fontSize) throws FontFormatException, IOException {
		Font font = null;
		font = Font.createFont(Font.TRUETYPE_FONT, new File(Utils.getPath(fontName)));
		font = font.deriveFont(java.awt.Font.PLAIN, fontSize);
		return font;
	}
	
	
}
