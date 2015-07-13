package minesweeper;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelMineField extends MyPanel {
	protected final static String TILE_STATUS_STR_CLOSED = "O";
	protected final static String TILE_STATUS_STR_OPEN = ".";
	protected final static String TILE_STATUS_STR_MARKED = "C";
	protected final static String TILE_TYPE_STR_MINE = "X";
	MineFieldHandler itsMineField;
	JPanel [][] itsTilePanels;
	JLabel [][] itsTilePanelLabels;
	PanelMineField(int width, int height) {
		super(width,height);
		itsMineField = null;
		itsTilePanels = null;
		itsTilePanelLabels = null;
	}
	public void setMineFieldHandler(MineFieldHandler field) {
		if(field == null) return;
		if(itsMineField != null) removeAll();
		
		itsMineField = field;
		int max_x = itsMineField.getMaxX();
		int max_y = itsMineField.getMaxY();
		
		setLayout(new GridLayout(max_x,max_y));
		
		itsTilePanels = new JPanel[max_x][];
		itsTilePanelLabels = new JLabel[max_x][];
		for(int x=0; x<max_x; x++) {
			itsTilePanels[x] = new JPanel[max_y];
			itsTilePanelLabels[x] = new JLabel[max_y];
		}
		
		for(int x=0; x<max_x; x++) {
			for(int y=0; y<max_y; y++) {
				JPanel curTile = itsTilePanels[x][y] = new JPanel();
				JLabel curLbl = itsTilePanelLabels[x][y] = new JLabel();				
				curTile.add(curLbl);
				this.add(curTile);
			}
		}
		updateAllTile();
	}
	public void updateAllTile() {
		for(int x=0; x<itsMineField.getMaxX(); x++) {
			for(int y=0; y<itsMineField.getMaxY(); y++) {
				updateTile(x,y);
			}
		}
		revalidate();
		repaint();
	}
	protected void updateTile(int idx_x, int idx_y) {
		if(idx_x < 0 || idx_x >= itsMineField.getMaxX() || idx_y < 0 || idx_y >= itsMineField.getMaxY() )
			return;
		int type = itsMineField.getTileType(idx_x, idx_y);
		int status = itsMineField.getTileStatus(idx_x, idx_y);
		JLabel curLbl = itsTilePanelLabels[idx_x][idx_y];
		String str = null;
		if(status == 0) {
			str = TILE_STATUS_STR_CLOSED;
		} else if(status == 1) {
			if(type == -1)
				str = TILE_TYPE_STR_MINE;
			else if (type == 0)
				str = TILE_STATUS_STR_OPEN;
			else
				str = Integer.toString(type);
		} else {
			str = TILE_STATUS_STR_MARKED;
		}
		curLbl.setText(str);
	}
}
