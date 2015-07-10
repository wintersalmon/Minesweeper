package myGameMineSearch;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MineGameGUI extends JFrame implements MouseListener {
	protected MineFieldController itsMineField;
	protected RandomMineMissionCreator itsRMMC;
	public MineGameGUI() {
		initGUI();
		initData();
	}
	protected void initGUI() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLUE);
		this.addMouseListener(this);
		this.setVisible(true);
	}
	protected void initData() {
		itsRMMC = new RandomMineMissionCreator();
		itsMineField = new MineFieldController();
	}
	protected void CreateNewMission(int x, int y, int count) {
		int [][] types = itsRMMC.Create(x, y, count);
		Field f = new Field(x,y,types);
		itsMineField.setMineField(f);
		repaint();
	}
	protected void StartGame() {
		
	}
	protected void StopGame() {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//itsMineField.getBounds().contains( e.getPoint() ) 
		if(true) {
			int x = e.getX();
			int y = e.getY();
			Point pos = e.getPoint();
			//SwingUtilities.convertPoint(this, pos, itsMineField);
			String str = "Clicked[" + x + "," + y + "]";
			if(e.getButton() == MouseEvent.BUTTON1) {
				System.out.println(str + "Left" + itsMineField.ClickTile(pos.x, pos.y, 1));
			}else if(e.getButton() == MouseEvent.BUTTON3) {
				System.out.println(str + "Right" + itsMineField.ClickTile(pos.x, pos.y, 2));
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}
