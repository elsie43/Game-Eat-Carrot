package leangSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import base.Sound;
import base.Utils;

public class Start extends Base {
	private int width = 130;
	private int height = 50;
	private JButton single;
	private JLabel startLabel;
	private Sound startMusic;
	Start(MyFrame frame) {
		super(frame);
		// setting start button
		single = new JButton("START");
		single.setBounds(Info.FRAME_WIDTH / 2 - width / 2, 110, width, height);
		single.setForeground(Color.BLACK);
		//single.setBackground(Color.YELLOW);
		single.setContentAreaFilled(false);
		//single.setOpaque(false);//false for transparent
		single.setBorderPainted(true);
		single.setVerticalTextPosition(JButton.BOTTOM);
		//setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		
		
		startMusic = new Sound();
		startMusic.play("jazz.wav", true);
		
		startLabel = new JLabel();
		startLabel.setText("Click START or Press ENTER !");
		startLabel.setForeground(Color.WHITE);
		startLabel.setBounds(0, 90, Info.FRAME_WIDTH, 200);
		try {
			startLabel.setFont(Utils.getFont("apple.ttf", 15));
			//startLabel.setFont(Utils.getFont("buffalo.otf", 20));
			
			//single.setFont(Utils.getFont("apple.ttf", 30));
			single.setFont(Utils.getFont("buffalo.otf", 40));
		} catch (FontFormatException e) {
			startLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			single.setFont(new Font("Arial", Font.PLAIN, 30));
			e.printStackTrace();
		} catch (IOException e) {
			startLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			single.setFont(new Font("Arial", Font.PLAIN, 30));
			e.printStackTrace();
		}
		startLabel.setHorizontalAlignment(JLabel.CENTER);
		startLabel.setOpaque(false);
		startLabel.setBorder(null);
		
		startListener listener = new startListener();
		single.addActionListener(listener);
		addKeyListener(new tempAdapter());
		add(single);
		add(startLabel);
		
		backgroundImage = Utils.getImage("back_start.jpg");
	}

	private class tempAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				// press enter to change to countdown panel
				Info.FINAL_SCORE = 0;
				startMusic.stop();
				switchPanel(new Countdown(frame));
				// switchPanel(new Game(frame));
			}
		}
	}

	public class startListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == single) {
				// click start button to change to to countdown panel
				Info.FINAL_SCORE = 0;
				startMusic.stop();
				switchPanel(new Countdown(frame));
			}
		}
	}
	
	
}
