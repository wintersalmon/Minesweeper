import myGameMineSearch.MineGameGUI;
import myGameMineSearch.Tester;

public class MineSearchGame {
	public static void main(String [] args) {
		Tester.testCaseFieldConstructor();
		Tester.testGUI();
		MineGameGUI gui = new MineGameGUI();
		gui.getTitle();
	}
}
