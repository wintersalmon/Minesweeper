package minesweeper;

import java.awt.Dimension;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	public MyPanel(int width, int height) {
		setMinimumSize(new Dimension(width,height));
		setPreferredSize(new Dimension(width,height));
		setMaximumSize(new Dimension(width,height));
	}
}
