package minesweeper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGameControl extends JPanel {
	JLabel lblMineCount;
	JLabel lblTimeCount;
	PanelGameControl() {
		this(500,500);
	}
	PanelGameControl(int size_width, int size_height) {
		initPanelMineField(size_width, size_height);
		lblMineCount = new JLabel("0");
		lblTimeCount = new JLabel("0");
	}
	protected void initPanelMineField(int size_width, int size_height) {
		this.setMinimumSize(new Dimension(500,100));
		this.setPreferredSize(new Dimension(size_width, size_height));
		this.setMaximumSize(new Dimension(1000,200));
		this.setBackground(Color.BLUE);
	}
	public void setStartButton(JButton btnStartGame) {
	// add Components to itsControllPanel
		this.setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS ));
		btnStartGame.setMinimumSize(new Dimension(100,50));
		btnStartGame.setPreferredSize(new Dimension(100,50));
		btnStartGame.setMaximumSize(new Dimension(100,50));
		btnStartGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnStartGame.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.add(btnStartGame);
		this.add(lblMineCount);
		this.add(lblTimeCount);
	}
	public void updateMineCount(String str) {
		lblMineCount.setText(str);
	}
	public void updateTimeCount(String str) {
		lblTimeCount.setText(str);
	}
}
