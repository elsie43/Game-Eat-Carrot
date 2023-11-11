package leangSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import base.Sound;

//import com.sun.media.sound.Toolkit;

import base.Utils;
import leangSwing.Countdown.timerTask;

public class Game extends Base {
	private Food carrots[];
	private Random rand;
	private JLabel countdownLabel;
	private Timer timerGame, timerCountdown;
	private String counterString;
	private int targetX, keyCode, record_i = 0;
	private int score = 0;
	boolean ifHit;
	private Sound gameMusic;

	Game(MyFrame frame) {
		super(frame);
		Info.ANY_JUMP = false;
		timerGame = new Timer();
		timerCountdown = new Timer();
		rand = new Random();
		backgroundImage = Utils.getImage("back1.jpg");

		gameMusic = new Sound();
		gameMusic.play("happy.wav", true);
		countdownLabel = new JLabel();
		countdownLabel.setHorizontalAlignment(JLabel.CENTER);
		countdownLabel.setVerticalAlignment(JLabel.CENTER);
		countdownLabel.setOpaque(false);
		countdownLabel.setBorder(null);
		countdownLabel.setForeground(new Color(0x690D0D));
		// countdownLabel.setBounds(5, -10, 55, 55);
		countdownLabel.setBounds(5, 0, 55, 55);

		Girl girls[] = new Girl[3];
		for (int i = 0; i < 3; i++) {
			girls[i] = new Girl(130 * i);
			add(girls[i]);
		}

		carrots = new Food[7];
		for (int i = 0; i < 7; i++) {
			int randX = rand.nextInt(3);
			// System.out.println(randX);
			carrots[i] = new Food(50 + 130 * randX, -40 + i * Info.CARROT_GAP, "food1.png");
		}

		try {
			// countdownLabel.setFont(Utils.getFont("sketch.otf", 40));//buffalo.otf
			countdownLabel.setFont(Utils.getFont("buffalo.otf", 40));// buffalo.otf
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(countdownLabel);
		addKeyListener(new foodAdapter());
		timerGame.scheduleAtFixedRate(new timerGameTask(), 0, 12);
		timerCountdown.scheduleAtFixedRate(new timerCountdownTask(), 0, 1000);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, 1200, 700, this); // draw background
		super.paint(g);

		Graphics2D g2D = (Graphics2D) g.create();
		for (int i = 0; i < 7; i++) {
			g2D.drawImage(carrots[i].getImage(), carrots[i].getX(), carrots[i].getY(), Info.CARROT_WIDTH,
					Info.CARROT_HEIGHT, this);
		}

		// Toolkit.getDefaultToolkit().sync();
		g2D.dispose(); // close the g2D draw
		g.dispose();
	}

	public boolean getHit() {
		return ifHit;
	}

	private class foodAdapter extends KeyAdapter { // Keyboard listener
		public void keyPressed(KeyEvent e) {
			keyCode = e.getKeyCode();
			ifHit = false;
			if (keyCode == KeyEvent.VK_A && targetX == 50) {
				ifHit = true;
			}

			else if (keyCode == KeyEvent.VK_S && targetX == (50 + 130)) {
				ifHit = true;
			}

			else if (keyCode == KeyEvent.VK_D && targetX == (50 + 130 * 2)) {
				ifHit = true;
			} else
				ifHit = false;

			for (int i = 0; i < 7; i++) {
				carrots[i].keyPressed(e);
				score += carrots[i].getScore();
			}

		}
	}

	class timerGameTask extends TimerTask {

		public void run() {
			for (int i = 0; i < 7; i++) {
				if (carrots[i].getY() == 380) {
					targetX = carrots[i].getX();
					record_i = i;
					break;
				}
			}

			if (carrots[record_i].getY() == 380 && ifHit)
				for (int i = 0; i < 7; i++) {
					carrots[i].move(ifHit);
			}
			else
				carrots[record_i].move(ifHit);

			ifHit = false;
			repaint();
			// counterString = String.valueOf(counter);

		}
	}

	class timerCountdownTask extends TimerTask {
		int counter = Info.ROUND_TIME;

		public void run() {
			if (counter > 0) {
				counterString = String.valueOf(counter);
				countdownLabel.setText(counterString);
				counter--;
			}

			else {
				System.out.println(score);
				Info.FINAL_SCORE = score;
				timerGame.cancel();
				timerCountdown.cancel();
				gameMusic.stop();
				switchPanel(new GameOver(frame));
			}
		}

	}

}
