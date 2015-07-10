package minesweeper;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MinesweeperGUI extends JFrame implements ActionListener, MouseListener {
	protected JButton btnStartGame;
	protected PanelGameControl itsControllPanel;
	protected PanelMineField itsMineFieldPanel;
	protected MineField itsMineField;
	protected MineFieldHandler itsMineFieldHandler;
	
	MinesweeperGUI() {
		super("Minesweeper");
		this.setLayout(new BoxLayout(this.getContentPane() ,BoxLayout.PAGE_AXIS ));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initScreen(500,600);
		initData();
	}
	protected void initData() {
		itsMineField = null;
		itsMineFieldHandler = new MineFieldHandler();
	}
	protected void initScreen(int screen_size_x, int screen_size_y) {
		//set Screen Size
		//this.setMinimumSize(new Dimension(500,600));
		//this.setPreferredSize(new Dimension(screen_size_x,screen_size_y));
		//this.setMaximumSize(new Dimension(400,500));
		this.setSize(screen_size_x, screen_size_y);
		
		// add Panels to Screen
		itsControllPanel = new PanelGameControl(screen_size_x, screen_size_y/6);
		itsMineFieldPanel = new PanelMineField(screen_size_x, screen_size_y/6 * 5);
	
		add(itsControllPanel);
		add(itsMineFieldPanel);
		
		// add Components to itsControllPanel
		btnStartGame = new JButton("START");
		itsControllPanel.setStartButton(btnStartGame);
		btnStartGame.addActionListener(this);
		
		// add Components to itsMineFieldPanel
		itsMineFieldPanel.setLayout(new GridLayout(1,1));
		JPanel placeHolder = new JPanel();
		itsMineFieldPanel.add(placeHolder);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	public static void main(String [] args) {
		MinesweeperGUI gui = new MinesweeperGUI();
		gui.getBackground();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartGame) {
			System.out.println("newGame");
			
			int max_x = 8;
			int max_y = 8;
			int mine_count = 8;
			itsMineField = MineField.GenerateRandomMineField(max_x, max_y, mine_count);
			if(itsMineField == null) {
				System.out.println("ERROR");
				return;
			}
			itsMineFieldHandler.setMineField(itsMineField);
			itsMineFieldPanel.setMineField(itsMineField);

			this.setVisible(true);
			this.addMouseListener(this);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if( itsMineFieldPanel.getBounds().contains(e.getPoint()) ) {
			Point pos = e.getPoint();
			System.out.println("before" + e.getX() + "," + e.getY());
			Point convertPos = SwingUtilities.convertPoint(this, pos, itsMineFieldPanel);
			int pos_x = (int)Math.floor( convertPos.getX() );
			int pos_y = (int)Math.floor( convertPos.getY() );
			System.out.println("after" + pos_x + "," + pos_y);
			
			System.out.println(pos_x + "," + itsMineFieldPanel.getWidth() + "," + itsMineField.getMaxX());
			int idx_y = pos_x / (itsMineFieldPanel.getWidth() / itsMineField.getMaxX() );
			int idx_x = pos_y / (itsMineFieldPanel.getHeight() / itsMineField.getMaxY() );
			int click_type = e.getButton();
			System.out.println("Clicked at(" + idx_x + "," + idx_y + ") with" + click_type); 
			if( itsMineFieldHandler.ClickTile(idx_x, idx_y, click_type) != 0 ) {
				for(int x=0; x<itsMineField.getMaxX(); x++) {
					for(int y=0; y<itsMineField.getMaxY(); y++) {
						itsMineFieldPanel.updateTile(x, y);
					}
				}
				//itsMineFieldPanel.updateAlltile();
				int maxMineCount = itsMineFieldHandler.getMaxMineCount();
				int curMineCount = itsMineFieldHandler.getCurMineCount();
				itsControllPanel.updateMineCount("" + (maxMineCount - curMineCount));
				System.out.println("Click Success");
			}
			this.setVisible(true);
		}
		//if(e.getPoint(). == itsMineFieldPanel)
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
