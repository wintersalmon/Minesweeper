package minesweeper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelGameControl extends MyPanel {
	protected JLabel lblMineCount;
	protected JLabel lblTimeCount;
	protected JButton btnStartButton;
	protected JPanel [] panels;
	protected MineFieldHandler itsMineFieldHandler;
	PanelGameControl(int width, int height) {
		super(width,height);
		
		int contentWidth = width/3;
		int contentHeight = height-10;
		
		lblMineCount = new JLabel("0", SwingConstants.CENTER);
		lblTimeCount = new JLabel("0", SwingConstants.CENTER);
		btnStartButton = new JButton("START");

		Component [] componetArray;
		componetArray = new Component[3];
		componetArray[0] = lblMineCount;
		componetArray[1] = btnStartButton;
		componetArray[2] = lblTimeCount;

		itsMineFieldHandler = null;
		setLayout(new GridLayout(1,3));
		panels = new JPanel[3];
		for(int i=0; i<3; i++) {
			panels[i] = new JPanel();
			componetArray[i].setMinimumSize(new Dimension(contentWidth, contentHeight));
			componetArray[i].setPreferredSize(new Dimension(contentWidth, contentHeight));
			componetArray[i].setMaximumSize(new Dimension(contentWidth, contentHeight));
			panels[i].add(componetArray[i]);
			add(panels[i]);
		}
	}
	public void setMineFieldHandler(MineFieldHandler field) {
		itsMineFieldHandler = field;
		updateStatus();
		revalidate();
		repaint();
	}
	public void addStartButtonListener(ActionListener listener) {
		btnStartButton.addActionListener(listener);
	}
	public void updateStatus() {
		updateLabel(lblMineCount, itsMineFieldHandler.getRemainingMineCount());
		updateLabel(lblTimeCount, itsMineFieldHandler.getTimeCount());
	}
	protected void updateLabel(JLabel label, int count) {
		String str = Integer.toString(count);
		label.setText(str);
	}
}