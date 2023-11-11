package base;

import java.awt.Image;

import javax.swing.ImageIcon;

import leangSwing.Info;

public class Element {
	protected int x, y;
	protected int width, height; // width and height of image
	protected float currentSpeed; // the speed when jump up and current speed
	protected Image image;

	public Element() {
		width = Info.CARROT_WIDTH;
		height = Info.CARROT_HEIGHT;
	}

	public Element(int x, int y, String imageName) {
		width = Info.CARROT_WIDTH;
		height = Info.CARROT_HEIGHT;
		image = Utils.getImage(imageName);
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
