
public class dice {
	private static int totalDice;
	public static int runDice() {
		int dice1 = (int)(Math.random() * 6) + 1;
		int dice2 = (int)(Math.random() * 6) + 1;
		totalDice = dice1 + dice2;
	
		return totalDice;
	}
}
