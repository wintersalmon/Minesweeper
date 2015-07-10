package minesweeper;

import minesweeperDataStructure.Tile;

public class MineFieldHandler {
	protected MineField itsMineField;
	protected int maxMineCount;
	protected int curMineCount;
	protected int [][] itsTileTypeArr;
	protected int [][] itsTileStatusArr;
	
	protected boolean bGameOver;
	
	public MineFieldHandler() {
		itsMineField = null;
		itsTileTypeArr = null;
		itsTileStatusArr = null;
		maxMineCount = 0;
		curMineCount = 0;
	}
	public void setMineField(MineField field) {
		bGameOver = false;
		itsMineField = field;
		itsTileTypeArr = new int[itsMineField.getMaxX()][];
		itsTileStatusArr = new int[itsMineField.getMaxX()][];
		for(int x=0; x<itsMineField.getMaxX(); x++) {
			itsTileTypeArr[x] = new int[itsMineField.getMaxY()];
			itsTileStatusArr[x] = new int[itsMineField.getMaxY()];
		}
		maxMineCount = 0;
		for(int x=0; x<itsTileTypeArr.length; x++) {
			for(int y=0; y<itsTileTypeArr[x].length; y++) {
				int type = itsTileTypeArr[x][y] = itsMineField.getTile(x, y).getType();
				if(type == -1) maxMineCount++;
			}
		}
		for(int x=0; x<itsTileStatusArr.length; x++) {
			for(int y=0; y<itsTileStatusArr[x].length; y++) {
				itsTileStatusArr[x][y] = itsMineField.getTile(x, y).getStatus();
			}
		}
	}
	public boolean isGameOver() {
		//if(curMineCount == maxMineCount) return true;
		if(bGameOver == true)return true;
		else return false;
	}
	
	private void ZeroLoop(int idx_x, int idx_y)
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
	private void ToggleAllMine()
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
	
	private void OpenTile(int idx_x, int idx_y, Tile selectedTile)
	{
		int tileType = selectedTile.getType();
		
		switch(tileType)
		{
		case 0:			//주변에 지뢰가 없는 타일
			ZeroLoop(idx_x,idx_y);
			break;
		case -1:		//지뢰
			ToggleAllMine();
			selectedTile.setStatus(1);
			System.out.println("GameOverRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR.");
			bGameOver = true;
			break;
		default:		//주변에 지뢰가 있는 타일
			selectedTile.setStatus(1);
				
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
		if(bGameOver == true)
			return 0;
		
		
		// 만약 인덱스값의 오류유무 확인
		Tile selectedTile = itsMineField.getTile(idx_x, idx_y);
		if(selectedTile == null) return 0;
		
		
		////////////////////////////////////////////////////////////////
		// 여기에서 알맞는 알고리즘을 적용시켜줘서 STATUS 값을 변경시켜준다.
		
		// STATUS 값을 변경해서 변경 전과 변경 후의 값을 저장한다.
		int beforeTileStatus = selectedTile.getStatus();
		
		if(beforeTileStatus != 1) //이미 펼쳐진 타일이면 아무 행동도 하지 않는다.
		{
			if(click_btn_type == 3)
			{
				if(beforeTileStatus == 0)
				{
					selectedTile.setStatus(3);//깃발 꽂기
				}
				else if(beforeTileStatus == 3)
				{
					selectedTile.setStatus(0); // 깃발 뽑기
				}
			}
			else if(click_btn_type == 1  &&  beforeTileStatus == 0) //왼쪽 클릭은 깃발이 꽂혀있는 곳에는 적용되지 않는다.
			{
				OpenTile(idx_x, idx_y, selectedTile);
			}
		
		}
		
		
		int afterTileStatus = selectedTile.getStatus();
		
		////////////////////////////////////////////////////////////////
		
		if(beforeTileStatus == afterTileStatus) { // Status Not Changed
			return 0;
		} else { // Status Changed
			// STATUS 값이 변경되었기 때문에 그 값을 참조하고 있는
			// itsTileStatusArr 와 curMineCount 의 값을 변 시켜준다
			itsTileTypeArr[idx_x][idx_y] = itsMineField.getTile(idx_x, idx_y).getType();
			curMineCount = 0;
			for(int x=0; x<itsTileStatusArr.length; x++) {
				for(int y=0; y<itsTileStatusArr[x].length; y++) {
					int status  =itsMineField.getTile(x, y).getStatus();
					if(status == 3) curMineCount++;
				}
			}
			return 1;
		}
	}
	public final int [][] getAllTileType() {
		return itsTileTypeArr;
	}
	public final int [][] getAllTileStatus() {
		return itsTileStatusArr;
	}
	public int getMaxMineCount() { return maxMineCount; }
	public int getCurMineCount() { return curMineCount; }
	
}
