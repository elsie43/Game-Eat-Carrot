package leangSwing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import base.Utils;

public class Girl extends JLabel {
	Girl(int leftBound) {
		ImageIcon img1 = Utils.getResizeImage("girl.gif", 130, 130);
		setIcon(img1);
		setForeground(Color.YELLOW);
		setBounds(leftBound, Info.FRAME_HEIGHT - 135, 130, 130);
		// startLabel.setHorizontalAlignment(JLabel.CENTER);
		setOpaque(false);
		setBorder(null);
		// startLabel.setContentAreaFilled(false);
	}

}
