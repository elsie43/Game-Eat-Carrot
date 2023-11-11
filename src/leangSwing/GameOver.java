package leangSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import base.Sound;
import base.Utils;
import leangSwing.Start.startListener;

public class GameOver extends Base {
	private JButton home, restart;
	private int width = 140;
	private int height = 25;
	private Sound cheer;
	GameOver(MyFrame frame) {
		super(frame);
		backgroundImage = Utils.getImage("back_end.jpg");
		JLabel endLabel = new JLabel();
		endLabel.setText("YOUR SCORE");
		endLabel.setHorizontalAlignment(JLabel.CENTER);
		endLabel.setOpaque(false);
		endLabel.setBorder(null);
		endLabel.setForeground(Color.WHITE);
		endLabel.setBounds(0, 40, Info.FRAME_WIDTH, 200);
		
		cheer = new Sound();
		cheer.play("cheer.wav", false);
		JLabel scoreLabel = new JLabel();
		scoreLabel.setText(String.valueOf(Info.FINAL_SCORE));
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setOpaque(false);
		scoreLabel.setBorder(null);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(0, 70, Info.FRAME_WIDTH, 300);
		
		home = new JButton("HOME PAGE");
		home.setBounds(Info.FRAME_WIDTH / 4 - width / 2, 300, width, height);
		
		restart = new JButton("RESTART");
		restart.setBounds(Info.FRAME_WIDTH / 4 * 3 - width / 2 , 300, width, height);
		try {
			endLabel.setFont(Utils.getFont("apple.ttf", 20));
			scoreLabel.setFont(Utils.getFont("apple.ttf", 80));

		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new tempAdapter());
		startListener listener = new startListener();
		home.addActionListener(listener);
		restart.addActionListener(listener);
		add(endLabel);
		add(scoreLabel);
		add(home);
		add(restart);
	}

	private class tempAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				// press enter to change to countdown panel
				cheer.stop();
				switchPanel(new Countdown(frame));
			}
			else if (key == KeyEvent.VK_Q) {
				// press enter to change to countdown panel
				cheer.stop();
				switchPanel(new Start(frame));
			}
		}
	}
	
	public class startListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == restart) {
				// click start button to change to to countdown panel
				Info.FINAL_SCORE = 0;
				cheer.stop();
				switchPanel(new Countdown(frame));
			}
			else if(e.getSource() == home) {
				// click start button to change to to countdown panel
				Info.FINAL_SCORE = 0;
				cheer.stop();
				switchPanel(new Start(frame));
			}

		}
	}
}
