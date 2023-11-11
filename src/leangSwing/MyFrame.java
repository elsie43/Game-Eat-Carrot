package leangSwing;

import base.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	private Start start;

	public void switchPanel(JPanel contentPanel, JPanel showPanel) {
		remove(contentPanel);
		contentPanel.setFocusable(false);

		add(showPanel);
		showPanel.requestFocus();

		revalidate();
		repaint();
	}

	MyFrame() throws FontFormatException, IOException {
		setTitle("EAT CARROTS !");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(Info.FRAME_WIDTH, Info.FRAME_HEIGHT);
		setResizable(false);
		Start start = new Start(this);
		add(start);

		//GameOver end = new GameOver(this);
		//add(end);
		setVisible(true);
	}
}
