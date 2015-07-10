package myGameMineSearch;

public class Tester {
	public static void testGUI() {
		System.out.println("TEST START GUI");
		MineGameGUI gui = new MineGameGUI();
		gui.CreateNewMission(8, 8, 8);
		System.out.println("TEST OK");
	}
	public static void testCaseFieldConstructor() {
		System.out.println("TEST START TILE and FIELD");
		int x = 8;
		int y = 8;
		int [][] types;
		Field f = null;
		types = new int[8][];
		for(int i=0; i<8; i++)
			types[i] = new int[8];
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				types[i][j] = (i + j) % 2;
			}
		}
		f = new Field(x,y,types);
		Field.TestPrintAllTileType(f);
		Field.TestPrintAllTileStatus(f);
		System.out.println("TEST OK");
	}
}
