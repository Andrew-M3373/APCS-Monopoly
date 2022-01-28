
public class CommunityChestCards extends Game {

	public static int RunCommunityChest() {
		
		int randomChance = (int)(Math.random() * 5) + 1;
		
		System.out.println("************ Community Chest *************");
		switch (randomChance) {
		case 1: System.out.printf("* Doctor's fee, pay $50                  *\n******************************************\n");
		return -50;
		case 2: System.out.printf("* You inherit $100!                      *\n******************************************\n");
		return 100; 
		case 3: System.out.printf("* Income Tax refund; Collect $20         *\n******************************************\n");
		return 20;
		case 4: System.out.printf("* Second place in a contest; Collect $10 *\n******************************************\n");
		return 10;
		case 5: System.out.printf("* Pay School Tax of $150                 *\n******************************************\n");
		return -150;
		}
		return 0;
			
	}
}

