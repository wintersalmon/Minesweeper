package myGameMineSearch;

class MineFieldController {
	protected Field itsMineField;
	protected int maxMineCount;
	protected int curMineCount;
	protected int [][] itsTileTypeArr;
	protected int [][] itsTileStatusArr;
	public MineFieldController() {
		itsMineField = null;
		itsTileTypeArr = null;
		itsTileStatusArr = null;
		maxMineCount = 0;
		curMineCount = 0;
	}
	public void setMineField(Field f) {
		itsMineField = f;
		itsTileTypeArr = new int[f.max_x_count][];
		itsTileStatusArr = new int[f.max_x_count][];
		for(int x=0; x<f.max_x_count; x++) {
			itsTileTypeArr[x] = new int[f.max_y_count];
			itsTileStatusArr[x] = new int[f.max_y_count];
		}
	}
	public boolean isGameOver() {
		if(curMineCount == maxMineCount) return true;
		else return false;
	}
	public int ClickTile(int idx_x, int idx_y, int click_type) {
		Tile selectedTile = itsMineField.getTile(idx_x, idx_y);
		if(selectedTile == null) return 0;
		
		int beforeTileStatus = selectedTile.getStatus();
		int afterTileStatus = selectedTile.setStatus(click_type);
		
		if(beforeTileStatus == afterTileStatus) return 0; // Status Not Changed
		else return 1; // Status Changed
	}
	public final int [][] getAllTileType() {
		return itsTileTypeArr;
	}
	public final int [][] getAllTileStatus() {
		return itsTileStatusArr;
	}
}