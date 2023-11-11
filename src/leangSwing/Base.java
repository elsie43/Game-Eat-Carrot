package leangSwing;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;

import base.Utils;

public class Base extends JPanel {
	protected MyFrame frame;
	protected Image backgroundImage;

	Base(MyFrame frame) {
		setSize(Info.FRAME_WIDTH, Info.FRAME_HEIGHT);
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setLayout(null);
		setOpaque(false);
		this.frame = frame;
	}

	public void switchPanel(JPanel showPanel) {
		frame.switchPanel(this, showPanel);
	}


	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, Info.FRAME_WIDTH, Info.FRAME_HEIGHT, this); // draw background
		super.paint(g);
	}
}
