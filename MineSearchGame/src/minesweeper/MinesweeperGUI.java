package minesweeper;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MinesweeperGUI extends JFrame implements ActionListener, MouseListener {
	protected final static int DEFAULT_TOP_PANEL_HEIGHT = 50;
	protected final static int DEFAULT_TILE_SIZE = 50;
	protected final static int DEFAULT_FIELD_COUNT_X = 9;
	protected final static int DEFAULT_FIELD_COUNT_Y = 9;
	protected final static int DEFAULT_MINE_COUNT = 10;
	
	protected int fieldCountX;
	protected int fieldCountY;
	protected int mineCount;
	protected JButton btnStartGame;
	protected PanelGameControl itsControllPanel;
	protected PanelMineField itsMineFieldPanel;
	protected MineFieldHandler itsMineFieldHandler;
	
	public MinesweeperGUI() {
		this(DEFAULT_FIELD_COUNT_X, DEFAULT_FIELD_COUNT_Y, DEFAULT_MINE_COUNT);
	}
	public MinesweeperGUI(int field_count_x, int field_count_y, int mine_count) {
		super("Minesweeper");
		fieldCountX = field_count_x;
		fieldCountY = field_count_y;
		mineCount = mine_count;
		itsMineFieldHandler = new MineFieldHandler(field_count_x, field_count_y, mine_count);
		initScreen(fieldCountX, fieldCountY);
		itsControllPanel.setMineFieldHandler(itsMineFieldHandler);
		itsMineFieldPanel.setMineFieldHandler(itsMineFieldHandler);
		ResetMisson();
	}
	protected void initScreen(int field_count_x, int field_count_y) {
		int screen_width = DEFAULT_TILE_SIZE * field_count_x;
		int screen_height_top = DEFAULT_TOP_PANEL_HEIGHT;
		int screen_height_bottom = DEFAULT_TILE_SIZE * field_count_y;
		int screen_height = screen_height_top + screen_height_bottom;
		
		setLayout(new BoxLayout(this.getContentPane() ,BoxLayout.PAGE_AXIS));
		setMinimumSize(new Dimension(screen_width,screen_height));
		setPreferredSize(new Dimension(screen_width,screen_height));
		setMaximumSize(new Dimension(screen_width,screen_height));
		
		itsControllPanel = new PanelGameControl(screen_width, screen_height_top);
		itsMineFieldPanel = new PanelMineField(screen_width, screen_height_bottom);
		
		add(itsControllPanel);
		add(itsMineFieldPanel);
		
		itsControllPanel.addStartButtonListener(this);
		itsMineFieldPanel.addMouseListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String [] args) {
		MinesweeperGUI gui = new MinesweeperGUI();
		gui.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ResetMisson();
	}
	protected void ResetMisson() {
		itsMineFieldHandler.CreateNewMission();
		itsMineFieldPanel.updateAllTile();
		itsControllPanel.updateStatus();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int idx_y = e.getX() / (itsMineFieldPanel.getWidth() / itsMineFieldHandler.getMaxX() );
		int idx_x = e.getY() / (itsMineFieldPanel.getHeight() / itsMineFieldHandler.getMaxY() );
		int click_type = e.getButton();
		if( itsMineFieldHandler.ClickTile(idx_x, idx_y, click_type) != 0 ) {
			itsMineFieldPanel.updateAllTile();
			itsControllPanel.updateStatus();
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
