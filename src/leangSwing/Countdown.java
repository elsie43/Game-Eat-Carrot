package leangSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import base.Utils;

public class Countdown extends Base {
	private Timer timer;
	private JLabel counterShow;
	private Font dFont;

	Countdown(MyFrame frame) {
		super(frame);
		timer = new Timer();

		try {
			dFont = Utils.getFont("apple.ttf", 85);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			dFont = new Font("Arial", Font.PLAIN, 80);
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dFont = new Font("Arial", Font.PLAIN, 80);
			// e.printStackTrace();
		}
		backgroundImage = Utils.getImage("back1.jpg");
		Girl girls[] = new Girl[3];
		for (int i = 0; i < 3; i++) {
			girls[i] = new Girl(130 * i);
			add(girls[i]);
		}

		counterShow = new JLabel("", SwingConstants.CENTER);
		counterShow.setFont(dFont);
		counterShow.setForeground(new Color(0x690D0D));// 661212
		counterShow.setBounds(0, Info.FRAME_HEIGHT / 2 - 20, Info.FRAME_WIDTH, 100);
		add(counterShow);

		// timerTask timerTask1 = new timerTask();
		timer.scheduleAtFixedRate(new timerTask(), 0, 1000);

	}

	class timerTask extends TimerTask {
		int counter = 3;
		String counterString;

		public void run() {
			if (counter > 0) {
				counterString = String.valueOf(counter);
				counterShow.setText(counterString);
				counter--;
			}

			else {
				timer.cancel();
				switchPanel(new Game(frame));
			}
		}
	}

}
