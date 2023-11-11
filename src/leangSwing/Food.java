package leangSwing;

import java.awt.event.KeyEvent;
import java.util.Random;

import base.Element;

public class Food extends Element {
	protected int x, y, dy;
	boolean ifMove, ifReset, ifJump, ifDrop;
	int score;
	Random rand;

	Food(int foodX, int foodY, String imageName) {
		super(foodX, foodY, imageName);
		rand = new Random();
		x = foodX;
		y = foodY;
		dy = (Info.CARROT_GAP);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		score = 0;
		if (!Info.ANY_JUMP) {
			if (key == KeyEvent.VK_A && x == 50 && y == 380) {
				ifReset = true;
				score = 1;
			} else if (key == KeyEvent.VK_S && x == (50 + 130) && y == 380) {
				ifReset = true;
				score = 1;
			} else if (key == KeyEvent.VK_D && x == (50 + 130 * 2) && y == 380) {
				ifReset = true;
				score = 1;
			} else if (y == 380) {
				if (key == KeyEvent.VK_A && x != 50) {
					ifJump = true;
					ifDrop = false;
				} else if (key == KeyEvent.VK_S && x != (50 + 130)) {
					ifJump = true;
					ifDrop = false;
				} else if (key == KeyEvent.VK_D && x != (50 + 130 * 2)) {
					ifJump = true;
					ifDrop = false;
				}
			} else
				ifMove = true;
		}

	}

	public void move(boolean ifHit) {

		if (y == 380 && ifHit && ifReset) {
			resetPosition();
		} else if (ifHit && ifMove && !Info.ANY_JUMP) {
			y += dy;
			ifMove = false;
		} else if (ifJump)
			jump();

	}

	public void jump() {
		Info.ANY_JUMP = true;
		if (355 < y && y <= 380 && ifJump && !ifDrop) {
			y -= 1;
		} else if (y == 355) {
			ifDrop = true;
			y += 1;

		} else if (ifDrop && y < (380-1)) {
			y += 1;
		} else {
			y = 380;
			ifJump = false;
			ifDrop = false;
			Info.ANY_JUMP = false;
			
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScore() {
		return score;
	}

	private void resetPosition() {
		int randX = rand.nextInt(3);
		x = 50 + 130 * randX;
		y = -40;
		ifMove = false;
		ifReset = false;
	}

}
