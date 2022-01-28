import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MonopolyRunner
	{

	
	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static int gameIndex = 0;
	private static boolean stillPlaying = true;
	private static boolean doubles = false;
	private static boolean rollAgain = true;
	
		public static void main(String[] args)
			{
			
			Database.generateDatabase(); // Creates ArrayList that includes all game data
			
			Scanner scanner = new Scanner(System.in); // Universal scanner
			
			playerSetup(scanner); // setup the player with introductory prompts
			
			runPlayer(scanner); // the actual runner
			
		}
		
		public static void runPlayer(Scanner scanner) {
			
			while(stillPlaying) {
				
				// for loop that allows each player to have 1 turn before moving on
				for (Player p : playerList) {
					int doublesCounter = 0;
					System.out.println("\n\n\n" + p.getName() + "'s turn");
					int diceRoll = 0;
					rollAgain = true;
					p.setDoublesCounter(0);
					do {
						if (!p.isInJail()) {

							doubles = false;
							
							if (rollAgain) {
								diceRoll = rollDice(p, scanner); // rolls Dice and checks for doubles
							}
							else rollAgain = true;
		//					System.out.println(p.getLocation());
		//					System.out.println(p.getMoney());
							
							
							if (rollAgain) {
								// if passing go, receive allowance
								if (p.getLocation() >= 40) {
									p.setLocation(p.getLocation()-40);
									p.setMoney(p.getMoney()+Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoPrice());
									System.out.println("Collect $" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoPrice() + " for passing " + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoTitle());
								}
								else if (p.getLocation() < 0) {
									p.setLocation(p.getLocation()+40);
									p.setMoney(p.getMoney()+Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoPrice());
									System.out.println("Collect $" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoPrice() + " for passing " + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoTitle());
								}
								
								// print location that was landed on
								System.out.println("You rolled the number " + diceRoll + " and landed on " + locationConverter(p.getLocation()).split("::")[1]);
								// special actions for chance and community chest
								if (p.getLocation() == 2 || p.getLocation() == 17 || p.getLocation() == 33) {
									Database.gameDatabase.get(gameIndex).getCommunityChestCards();
								}
								else if (p.getLocation() == 7 || p.getLocation() == 22 || p.getLocation() == 36) {
			
									p.setLocation(ChanceCards.RunChanceCards());
									if (p.getLocation() == 10) p.setInJail(true);
			
								}
								// Move piece from Go To Jail to Jail
								if (p.getLocation() == 30) {
									p.setInJail(true);
									p.setLocation(10);
									doubles = false;
								}
								
								// Reverse movement direction if on Free Parking
								if (p.getLocation() == 20) {
									p.setReverse(p.getReverse()*-1);
								}
								
								// Pay income tax if needed
								if (p.getLocation() == 4)
									incomeTax(p);
								// Pay luxury tax if needed
								if (p.getLocation() == 38) 
									luxuryTax(p);
								
								
								// if landed on a purchasable property, execute this. 
								if (p.getLocation() != 0 && p.getLocation() != 40 && p.getLocation() != 7 && p.getLocation() != 22 && p.getLocation() != 36 && p.getLocation() != 2 && p.getLocation() != 17 && p.getLocation() != 33 && p.getLocation() != 0 && p.getLocation() != 10 && p.getLocation() != 20 && p.getLocation() != 30 && p.getLocation() != 38 && p.getLocation() != 4) {
									System.out.println("This property is unowned; would you like to purchase it? (y/n)\n\t"
											+ "Price: $" + locationConverter(p.getLocation()).split("::")[0] + "\n\tCurrent balance: $" + p.getMoney());
									
									String yOrN = scanner.nextLine();
									if (yOrN.equals("y")) {
										p.setMoney(p.getMoney()-Integer.parseInt(locationConverter(p.getLocation()).split("::")[0]));
										System.out.println("Your new balance is $"+ p.getMoney());
										p.addProperty(new String[] {locationConverter(p.getLocation()).split("::")[1], locationConverter(p.getLocation()).split("::")[2], locationConverter(p.getLocation()).split("::")[0]}); //add rent
									}
								}
								// press enter to move on to the next player's turn
								if (doubles == true) System.out.println("Press enter to roll again or \"i\" to view the inventory.\n\n\n");
								else System.out.println("Press enter to continue or \"i\" to view the inventory. ");
								pressEnterOrI(p,scanner);
							
							}
						}
						else { 
							if (rollAgain) {
							System.out.println("You're in " + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getJailTitle()
									+ ". What do you want to do? "
									+ "\n(1) Attempt to roll doubles"
									+ "\n(2) Pay the $50 fee");
							
							int input = Integer.parseInt(scanner.next());
							scanner.nextLine();
							if (input == 2) {
								p.setInJail(false);
								p.setMoney(p.getMoney()-50);
								p.setJailCounter(0);
								doubles = true; 
								rollAgain = true;
							}
							else if (input == 1) {
								doubles = false;
								diceRoll = Dice.runDice();
								p.setJailCounter(p.getJailCounter()+1);
								if (doubles) {
									rollAgain = false;
									p.setLocation(p.getLocation()+(p.getReverse()*diceRoll));
									System.out.println("You rolled doubles!");
									p.setInJail(false);
									p.setJailCounter(0);
								}
								else if (p.getJailCounter() >= 3) {
									System.out.println("You didn't roll doubles on your third try, so you paid $50 to get out of jail");
									p.setInJail(false);
									p.setMoney(p.getMoney()-50);
									p.setJailCounter(0);
									doubles = true; 
									rollAgain = true;
								}
								else System.out.println("You didn't roll doubles. Better luck next time!");
								}
							}
						}
					} while (doubles);
				}
				
			}
		}
		
		public static void playerSetup(Scanner scanner) {
			
			// options to play different monopoly games
			System.out.println("Which game would you like to play?");
			for (int i = 1; i <= Database.gameDatabase.size(); i++) {
				System.out.println("(" + i + ") " + Database.gameDatabase.get(i-1).getTitle());
			}
			gameIndex = Integer.parseInt(scanner.next()) - 1;
			scanner.nextLine();
			
			
			// option of how many players will be playing
			System.out.println("\nWelcome to the game of " + Database.gameDatabase.get(gameIndex).getTitle() + "!");
			System.out.println("Extra rules:"
					+ "\n\t(1) Press the letter \"i\" after your turn to view each player's inventory"
					+ "\n\t(2) Tokens move in reverse after landing on " + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getFreeParkingTitle()
					+ "\n\t(3) Debugging note: the letter \"i\" may be substituted for \"j\" or \"fp\" to move the token directly to Jail or Free Parking, respectively");
			System.out.println("\nHow many players?");
			int playerCount = Integer.parseInt(scanner.next());
			scanner.nextLine();
			if (playerCount > 8 || playerCount < 1) {
				System.out.println("Please try again with a valid input");
				System.exit(0);
			}
			
			
			// ask each player to choose a unique token 
			for (int i = 0; i < playerCount; i++) {
			
				System.out.println("\nPlayer " + (i + 1) + ", what token would you like to play?");
				for (int j = 1; j <= Database.gameDatabase.get(gameIndex).getPieces().getSize(); j++) {
					System.out.println("(" + j + ") " + Database.gameDatabase.get(gameIndex).getPieces().getPiece(j-1));
				}

				
				int pieceIndex = Integer.parseInt(scanner.next());
				scanner.nextLine();
				
				
				// add the player to the list, and delete the token option for the next player
				playerList.add(new Player(Database.gameDatabase.get(gameIndex).getPieces().getPiece(pieceIndex-1), 1500, 0, false, 0, 0, 1, new ArrayList <String[]>()));
				Database.gameDatabase.get(gameIndex).getPieces().removePiece(pieceIndex-1);
			}
		}

		public static String locationConverter(int l) {
			switch (l) {
			case 7: case 22: case 36: return "00::Chance";
			case 2: case 17: case 33: return "00::Community Chest"; 
			// group 1
			case 1: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getGroupTitle(); 
			case 3: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup1().getGroupTitle(); 
			// group 2
			case 6: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getGroupTitle(); 
			case 8: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getGroupTitle();
			case 9: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup2().getGroupTitle();
			// group 3
			case 11: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getGroupTitle(); 
			case 13: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getGroupTitle(); 
			case 14: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup3().getGroupTitle(); 
			// group 4
			case 16: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getGroupTitle(); 
			case 18: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getGroupTitle(); 
			case 19: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup4().getGroupTitle(); 
			// group 5
			case 21: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getGroupTitle(); 
			case 23: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getGroupTitle(); 
			case 24: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup5().getGroupTitle(); 
			// group 6
			case 26: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getGroupTitle(); 
			case 27: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getGroupTitle(); 
			case 29: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup6().getGroupTitle(); 
			// group 7
			case 31: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getGroupTitle(); 
			case 32: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getGroupTitle(); 
			case 34:return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty3Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getProperty3Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup7().getGroupTitle(); 
			// group 8
			case 37: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getGroupTitle(); 
			case 39: return Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getProperties().getPropertyGroup8().getGroupTitle(); 
			// special
			case 0: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoTitle(); 
			case 4: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getIncomeTaxPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getIncomeTaxTitle();
			case 10: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getJailPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getJailTitle();
			case 20: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getFreeParkingPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getFreeParkingTitle();
			case 30: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoToJailPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getGoToJailTitle();
			case 38: return Database.gameDatabase.get(gameIndex).getSpecialSpaces().getLuxuryTaxPrice() + "::" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getLuxuryTaxTitle();
			// railroads
			case 5: return Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadPrices() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroad1Name() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadGroupTitle();
			case 15: return Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadPrices() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroad2Name() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadGroupTitle();
			case 25: return Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadPrices() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroad3Name() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadGroupTitle();
			case 35: return Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadPrices() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroad4Name() + "::" + Database.gameDatabase.get(gameIndex).getRailroadGroup().getRailroadGroupTitle();
			// utilities
			case 12: return Database.gameDatabase.get(gameIndex).getUtilityGroup().getProperty1Price() + "::" + Database.gameDatabase.get(gameIndex).getUtilityGroup().getProperty1Name() + "::" + Database.gameDatabase.get(gameIndex).getUtilityGroup().getGroupTitle();
			case 28: return Database.gameDatabase.get(gameIndex).getUtilityGroup().getProperty2Price() + "::" + Database.gameDatabase.get(gameIndex).getUtilityGroup().getProperty2Name() + "::" + Database.gameDatabase.get(gameIndex).getUtilityGroup().getGroupTitle();
			}
			return "";
		}

		public static void setDoubles() {
			doubles = true;
		}
		
		public static int getGameIndex() {
			return gameIndex;
		}
		
		public static int rollDice(Player p, Scanner scanner) {
			int diceRoll = Dice.runDice(); // Roll the Dice
			p.setLocation(p.getLocation() + (p.getReverse()*diceRoll)); // move the token to the new location
			if (doubles == true) {
				System.out.println("You rolled doubles!");
				p.setDoublesCounter(p.getDoublesCounter() + 1);
				if (p.getDoublesCounter() > 2) {
					System.out.println("You rolled doubles 3 times in a row, go to " + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getJailTitle());
					p.setInJail(true);
					p.setLocation(10);
					rollAgain = false; 
					doubles = false;
					System.out.println("Press enter to continue or \"i\" to view the inventory");
					pressEnterOrI(p,scanner);
				}
			}
			
			return diceRoll;
		}
		
		public static void pressEnterOrI(Player p, Scanner scanner) {
			String input = scanner.nextLine();
			
			if (input.equals("i")) {
				System.out.println("\nCurrent balance: $" + p.getMoney());
				
				if (p.getInventory().size() == 0)
					System.out.println(p.getName() + " does not own any properties");
				else {
					System.out.println("\n" + p.getName() + "'s list of owned properties:");
					
					// Sort ArrayList by property group type
					Collections.sort(p.getInventory(),new Comparator <String[]>() {
						public int compare(String[] a, String[] b) {
							return -1 * a[1].compareToIgnoreCase(b[1]);
						}
					});
					
					String format = format("| %-25s | %-15s | %-5s |%n");
					System.out.println(format("a===========================b=================b=======c"));
					System.out.println(format("| ") + "Property title" + format("            | ") + "Group" + format("           | ") + "Price" + format(" |"));
					System.out.println(format("j===========================e=================e=======f"));
					for (String[] s : p.getInventory()) {
						System.out.printf(format,s[0],s[1],s[2]);
					}
					System.out.println(format("g===========================h=================h=======i"));
				}
				
				boolean viewingInventory = true;
				do {
					System.out.println("\nPress enter to continue or a player number to view their inventory");
					for (int i = 0; i < playerList.size(); i++) {
						System.out.println("\t(" + (i+1) + ") " + playerList.get(i).getName());
					}
					input = scanner.nextLine();
					if (!input.equals("")) {
						System.out.println("\nCurrent balance: $" + p.getMoney());
						
						if (playerList.get(Integer.parseInt(input)-1).getInventory().size() == 0)
							System.out.println(playerList.get(Integer.parseInt(input)-1).getName() + " does not own any properties");
						else {
							System.out.println("\n" + playerList.get(Integer.parseInt(input)-1).getName() + "'s list of owned properties:");
							
							String format = format("| %-25s | %-15s | %-5s |%n");
							System.out.println(format("a===========================b=================b=======c"));
							System.out.println(format("| ") + "Property title" + format("            | ") + "Group" + format("           | ") + "Price" + format(" |"));
							System.out.println(format("j===========================e=================e=======f"));
							for (int i = 0; i < playerList.get(Integer.parseInt(input)-1).getInventory().size(); i++)
								System.out.printf(format,playerList.get(Integer.parseInt(input)-1).getInventory().get(i)[0],playerList.get(Integer.parseInt(input)-1).getInventory().get(i)[1],playerList.get(Integer.parseInt(input)-1).getInventory().get(i)[2]);
							System.out.println(format("g===========================h=================h=======i"));
						}
					}
					else viewingInventory = false;
				} while (viewingInventory);
			}
			
			else if (input.toLowerCase().equals("j")) {
				p.setLocation(10);
				p.setInJail(true);
				rollAgain = false;
				doubles = false;
			}
			
			else if (input.toLowerCase().equals("fp")) {
				p.setLocation(20);
				p.setReverse(p.getReverse()*-1);
			}
		}
		
		private static String format(String string) {
			return string.replace("a", "\u250c")
					.replace("b", "\u252c")
					.replace("c", "\u2510")
					.replace('j', '\u251c')
	                .replace('e', '\u253c')
	                .replace('f', '\u2524')
	                .replace('g', '\u2514')
	                .replace('h', '\u2534')
	                .replace('i', '\u2518')
					.replace("=", "\u2500")
					.replace('|', '\u2502');
		}
		
		public static void incomeTax(Player p) {
			System.out.println("You have paid $" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getIncomeTaxPrice());
			p.setMoney(p.getMoney()-Database.gameDatabase.get(gameIndex).getSpecialSpaces().getIncomeTaxPrice());
		}
		
		public static void luxuryTax(Player p) {
			System.out.println("You have paid $" + Database.gameDatabase.get(gameIndex).getSpecialSpaces().getLuxuryTaxPrice());
			p.setMoney(p.getMoney()-Database.gameDatabase.get(gameIndex).getSpecialSpaces().getLuxuryTaxPrice());
		}
	}
