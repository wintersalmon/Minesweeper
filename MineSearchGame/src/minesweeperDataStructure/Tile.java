package minesweeperDataStructure;

public class Tile {
	protected int itsStatus;
	protected int itsType;
	public Tile(int type) {
		itsType = type;
		itsStatus = 0;
	}
	public int getType() {
		return itsType;
	}
	public int getStatus() {
		return itsStatus;
	}
	public int setStatus(int status) {
		return itsStatus = status;
	}
}
