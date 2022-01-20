
public class ChanceCards extends Game {

	public static int RunChanceCards() {

//		System.out.println("You have landed on a chance card");
			
		int randomChance = (int)(Math.random() * 5) + 1;
		
		System.out.println("******************************");
		switch (randomChance) {
		case 1: System.out.printf("* Go to %-20s *\n******************************\n", Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getSpecialSpaces().getJailTitle());
		return 10;
		case 2: System.out.printf("* Go to %-20s *\n******************************\n", Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getGoName() + " and collect $" + Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getSpecialSpaces().getGoPrice());
		return 40; 
		case 3: System.out.printf("* Go to %-20s *\n******************************\n", Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getProperties().getPropertyGroup7().getProperty1Name());
		return 31;
		case 4: System.out.printf("* Go to %-20s *\n******************************\n", Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getUtilityGroup().getProperty1Name());
		return 12;
		case 5: System.out.printf("* Go to %-20s *\n******************************\n", Database.gameDatabase.get(MonopolyRunner.getGameIndex()).getSpecialSpaces().getIncomeTaxTitle());
		return 4;
		}
		return 0;
	
		
	
	}
}
