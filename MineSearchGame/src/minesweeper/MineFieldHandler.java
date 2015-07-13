package minesweeper;

import javax.swing.JOptionPane;
import minesweeperDataStructure.Tile;

public class MineFieldHandler {
	protected int defaultFieldCountX;
	protected int defaultFieldCountY;
	protected int defaultMineCount;
	protected int markedTileCount;
	protected int curTimeCount;
	protected MineField itsMineField;
	protected boolean bGameOver;
	public MineFieldHandler(int mine_field_count_x, int mine_field_count_y, int mine_count) {
		defaultFieldCountX = mine_field_count_x;
		defaultFieldCountY = mine_field_count_y;
		defaultMineCount = mine_count;
		markedTileCount = 0;
		curTimeCount = 0;
		itsMineField = null;
	}
	public void CreateNewMission() {
		itsMineField = new MineField(defaultFieldCountX, defaultFieldCountY, RandomMineMissionCreator.Create(defaultFieldCountX, defaultFieldCountY, defaultMineCount));
		markedTileCount = 0;
		curTimeCount = 0;
		bGameOver = false;
	}
	public boolean isGameOver() { return bGameOver; }
	public int getTimeCount() { return curTimeCount; }
	public int getRemainingMineCount() { return defaultMineCount - markedTileCount; }
	public int getMaxX() { return defaultFieldCountX; }
	public int getMaxY() { return defaultFieldCountY; }
	public int getMaxMineCount() { return defaultMineCount; }
	public int getTileStatus(int idx_x, int idx_y) {
		Tile t = itsMineField.getTile(idx_x, idx_y);
		if(t != null) {
			return t.getStatus();
		} else {
			return MineField.STATUS_ERROR;
		}
	}
	public int getTileType(int idx_x, int idx_y) {
		Tile t = itsMineField.getTile(idx_x, idx_y);
		if(t != null) {
			return t.getType();
		} else {
			return MineField.TYPE_ERROR;
		}
	}
	//public Tile getTile(int index_x, int index_y) { return itsMineField.getTile(index_x, index_y); }
	protected void ZeroLoop(int idx_x, int idx_y)
	{
		Tile selectedTile = itsMineField.getTile(idx_x, idx_y);
		if(selectedTile == null) return;
		
		if(selectedTile.getStatus() != 1)
		{
			selectedTile.setStatus(1);
			
			if(selectedTile.getType() == 0)
			{
				ZeroLoop(idx_x+1,idx_y);
				ZeroLoop(idx_x-1,idx_y);
				ZeroLoop(idx_x,idx_y+1);
				ZeroLoop(idx_x,idx_y-1);
			}
		}
	}
	protected void ToggleAllMine()
	{
		Tile tile;
		for(int i=0; i < itsMineField.getMaxY(); i++)
		{
			for(int j=0; j < itsMineField.getMaxX(); j++)
			{
				tile = itsMineField.getTile(i, j);
				if(tile.getType() == -1)
					tile.setStatus(1);
			}
		}
	}
	protected void OpenTile(int idx_x, int idx_y, Tile selectedTile)
	{
		int tileType = selectedTile.getType();
		switch(tileType)
		{
		case MineField.TYPE_BLANK:			//주변에 지뢰가 없는 타일
			ZeroLoop(idx_x,idx_y);
			break;
		case MineField.TYPE_MINE:		//지뢰
			ToggleAllMine();
			selectedTile.setStatus(MineField.STATUS_OPEN);
			bGameOver = true;
			JOptionPane.showMessageDialog(null, "GAME OVER");
			break;
		default:		//주변에 지뢰가 있는 타일
			selectedTile.setStatus(MineField.STATUS_OPEN);
		}
	}
	
	public int ClickTile(int idx_x, int idx_y, int click_btn_type) {
		// 타일 클릭이 일어나면 호출된다.
		// 해당하는 타일의 STATUS 값을 이벤트에 알맞게 변경해준다.
		// 입력값 idx_x 클릭된 타일의 X index
		// 입력값 idx_y 클릭된 타일의 Y index
		// 입력값 click_btn_type 클릭된 마우스 버튼 종류 (1:왼쪽클릭, 2:휠클릭, 3:오른쪽클릭)
		// 리턴값 STATUS 변경유무를 반환 (0:변경없음, 1:변경됨)
		
		//게임오버시 클릭되지 않는다.
		if(bGameOver == true) return 0;
		
		// 만약 인덱스값의 오류유무 확인
		Tile selectedTile = itsMineField.getTile(idx_x, idx_y);
		if(selectedTile == null) return 0;
		
		int beforeTileStatus = selectedTile.getStatus();
		if(beforeTileStatus != MineField.STATUS_OPEN) //이미 펼쳐진 타일이면 아무 행동도 하지 않는다.
		{
			if(click_btn_type == 3)
			{
				if(beforeTileStatus == MineField.STATUS_CLOSED)
				{
					selectedTile.setStatus(MineField.STATUS_MARKED);//깃발 꽂기
				}
				else if(beforeTileStatus == MineField.STATUS_MARKED)
				{
					selectedTile.setStatus(MineField.STATUS_CLOSED); // 깃발 뽑기
				}
			}
			else if(click_btn_type == 1  &&  beforeTileStatus == MineField.STATUS_CLOSED) //왼쪽 클릭은 깃발이 꽂혀있는 곳에는 적용되지 않는다.
			{
				OpenTile(idx_x, idx_y, selectedTile);
			}
		}
		int afterTileStatus = selectedTile.getStatus();
				
		if(beforeTileStatus == afterTileStatus) { // Status Not Changed
			return 0;
		} else {
			markedTileCount = 0;
			for(int x=0; x<defaultFieldCountX; x++) {
				for(int y=0; y<defaultFieldCountX; y++) {
					int status = itsMineField.getTile(x, y).getStatus();
					if(status == MineField.STATUS_MARKED) markedTileCount++;
				}
			}
			curTimeCount++;
			return 1;
		}
	}
}
