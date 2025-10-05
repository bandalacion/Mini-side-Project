import java.time.Year;
import java.util.Scanner; 

public class Game {
	private static Scanner scanner;

	// === MAP ===
	static String[][] map = {
	    {"Mountains", "Village", "Forest"},
	    {"River", "Start", "Desert"},
	    {"Cave", "Town", "Castle"}
	};
	static int playerX = 1; // row
	static int playerY = 1; // column
	// ===========
	
	public static void main(String[] args) {
		System.out.println( 
			  "    ______   ______   ______   ____   _____         _______    ______   _____    ___             \n"
			+ "   / __  /  / __  /  / __  /  /   / _/    /        / _____/   / __  /  / __  /  /  /             \n"
			+ "  / /_/ /  / / / /  / / / /  / // // _// /        / /        / / / /  / /_/ /  /  /              \n"
			+ " / /_/ /  / /_/ /  / /_/ /  / / /  _/ / /        / /_____   / /_/ /  / __  /  /  /_____          \n"
			+ "/_____/  /_____/  /_____/  /_/  /_/  /_/        /_______/  /_____/  /_/ /_/  /________/          \n"
			+ " 																								\n"
			+ " 				by: Bandalac Ion Cristian														\n"
		);                       
		scanner = new Scanner(System.in);
		
		// Introduction
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		System.out.println("Hola " + name);
		
		System.out.println("When were you born " + name + "?");
        int year = scanner.nextInt(); 
        scanner.nextLine(); // Consume the newline
		System.out.println("Hey " + name + " you were born in the year " + year);
		
		int age = calculateAge(year);
		System.out.println(name + " you are " + age + " years old.");

		if (age >= 60) {
			System.out.println("How are you even running this grandpa? I thought old people can't even turn on their video on calls.");
		}
		else {
			System.out.println("Hey there " + name + " since you are here lets start our adventure through the Forgotten Realms. The legend going around the city is that they are in need of a new \n"
			+ "fighter, as the last one has gone to retrieve the magical staff from the mystical blue dragon, who is believed to be named lyrmith.\n "
			+ "Are you ready explore the Forgotten Realms?"
			);
		}
		
		String ready = scanner.nextLine();

		if (ready.equalsIgnoreCase("yes")) {
 	 	  System.out.println("Great! Let's start our adventure.");
		} else {
  		  System.out.println("You know what " + name + ", I understand you are scared. But you have to be brave. You can do this. I believe in you.");
		}

		// Main game loop for direction choices (visible map)
		chooseDirection(name);
	}
	
	// Method to handle direction choices with go back functionality
	public static void chooseDirection(String name) {
		while (true) {
			System.out.println("\n==================================================");
			displayMiniMap();
			System.out.println("Current position: " + map[playerX][playerY]);
			System.out.println("Where would you like to go?");
			System.out.println("North, South, East, West, or Quit?");
			System.out.println("==================================================");
			String direction = scanner.nextLine();

			if (direction.equalsIgnoreCase("quit")) {
				System.out.println("Thanks for playing! Goodbye " + name + "!");
				break;
			}

			// Save old position in case of invalid move
			int prevX = playerX;
			int prevY = playerY;

			if (direction.equalsIgnoreCase("north")) playerX--;
			else if (direction.equalsIgnoreCase("south")) playerX++;
			else if (direction.equalsIgnoreCase("east")) playerY++;
			else if (direction.equalsIgnoreCase("west")) playerY--;
			else {
				System.out.println("Invalid direction. Please choose North, South, East, West, or Quit.");
				continue;
			}
			
			// Check map bounds
			if (playerX < 0 || playerX >= map.length || playerY < 0 || playerY >= map[0].length) {
				System.out.println("You can't go that way!");
				playerX = prevX;
				playerY = prevY;
				continue;
			}

			System.out.println("You moved to: " + map[playerX][playerY]);

			// Call the exact original storyline for that direction.
			// If the handler returns true -> user selected 'back' inside the story and wants to return to map.
			// If it returns false -> the storyline concluded and we end the game (as you requested).
			boolean backToMap = true;
			String loc = map[playerX][playerY];
			if (loc.equalsIgnoreCase("Village")) {
				backToMap = handleNorthDirection(name);
			} else if (loc.equalsIgnoreCase("Desert") || loc.equalsIgnoreCase("River")) {
				// We'll map South story to Desert or River tiles; call the original south storyline.
				backToMap = handleSouthDirection(name);
			} else if (loc.equalsIgnoreCase("Forest")) {
				backToMap = handleEastDirection(name);
			} else if (loc.equalsIgnoreCase("Mountains")) {
				backToMap = handleWestDirection(name);
			} else {
				// For other tiles (Cave, Town, Castle, etc.) we can show a short message and end the game.
				System.out.println("You explore the area: " + loc + ". There's nothing more to do here in this build.");
				backToMap = false; // end the game after exploring these tiles
			}

			if (backToMap) {
				// user used 'back' somewhere in the storyline; allow them to pick another direction
				continue;
			} else {
				// Story concluded => end the game
				System.out.println("\n--- The adventure ends here. Thank you for playing! ---");
				break;
			}
		}
	}

	// mini visual map
	public static void displayMiniMap() {
		System.out.println("Map:");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == playerX && j == playerY) {
					System.out.print("[@] ");
				} else {
					System.out.print("[ ] ");
				}
			}
			System.out.println();
		}
	}

	public static boolean handleNorthDirection(String name) {
		System.out.println("So you chose the north direction. You know that people are very scared to go there. It is said to be the land of the giants and goblins.");
		System.out.println("Are you sure about this? (yes/no/back)");

		String response = scanner.nextLine();
		
		if (response.equalsIgnoreCase("back")) {
			System.out.println("You decide to turn around and go back to choose another direction.");
			return true;
		}

		if (response.equalsIgnoreCase("yes")) {
			System.out.println("You walk for hours and hours, you are very tired and thirsty. You see a small village in the distance. Do you want to go there? (yes/no/back)");
		}
		else {
			System.out.println("You know what " + name + ", I understand you are scared. But you have to be brave. You can do this. I believe in you.");
			System.out.println("You walk for hours and hours, you are very tired and thirsty. You see a small village in the distance. Do you want to go there? (yes/no/back)");
		}
		
		String village = scanner.nextLine();
		
		if (village.equalsIgnoreCase("back")) {
			System.out.println("You decide to turn around and go back to choose another direction.");
			return true;
		}
		
		if (village.equalsIgnoreCase("yes")) {
			System.out.println("You arrive at the village and see a tavern. You go inside and see a group of people playing cards. Do you want to join them? (yes/no/back)");
			String cards = scanner.nextLine();
			
			if (cards.equalsIgnoreCase("back")) {
				System.out.println("You decide to turn around and go back to choose another direction.");
				return true;
			}
			
			if (cards.equalsIgnoreCase("yes")) {
				System.out.println("You sit down and start playing cards with the group. After a while, you win a lot of money. You are now rich!");
				System.out.println("Congratulations! Your northern adventure ends here.");
			}
			else {
				System.out.println("You decide not to join the group and instead go to the bar to get a drink. You see a beautiful person and strike up a conversation.");
				System.out.println("Your northern adventure ends here.");
			}
		}
		else { 
			System.out.println("You have decided not to go to the village as it seems too dangerous for you. You walk for hours and hours, after a while you are starting to get very tired and falling asleep you decide to sleep in a nearby forest.");
			System.out.println("You are being woken up in the middle of the night by some weird noises. Do you want to investigate? (yes/no/back)");
			String investigate = scanner.nextLine();
			
			if (investigate.equalsIgnoreCase("back")) {
				System.out.println("You decide to turn around and go back to choose another direction.");
				return true;
			}
			
			if (investigate.equalsIgnoreCase("yes")) {
				System.out.println("You decide to investigate the noises and find a group of goblins. They are very angry and start chasing you. You run as fast as you can and manage to escape.");
			}
			else {
				System.out.println("You decide not to investigate the noise and instead packed up your bags and starting running out back to the dirt path you were walking before.");
			}
			System.out.println("Your northern adventure ends here.");
		}
		return false; 
	}

	public static boolean handleSouthDirection(String name) {
		System.out.println("You have chosen to go south and you are walking on a street full of merchants and traders. You see a shop that sells a magical key."
		+ " The merchant tells you that the key was once the main treasure of an ancient king but it was stolen by some goblins which now are hiding in a nearby cave."
		+ " You ask to buy the key but the merchant tells you that you need to tell him the secret password in order to buy it. Every guess costs you 100 gold. You have 500 gold.");
		System.out.println("Would you like to try and guess the password? (yes/no/back)");
		
		String guessPassword = scanner.nextLine();
		
		if (guessPassword.equalsIgnoreCase("back")) {
			System.out.println("You decide to turn around and go back to choose another direction.");
			return true;
		}
		
		if (guessPassword.equalsIgnoreCase("yes")) {
			System.out.println("You have 5 attempts to guess the password. Each guess costs you 100 gold. You have 500 gold.");
			System.out.println("Please enter your first guess (or type 'back' to return):");
			String guess = scanner.nextLine();
			
			if (guess.equalsIgnoreCase("back")) {
				System.out.println("You decide to turn around and go back to choose another direction.");
				return true;
			}
			
			if (guess.toLowerCase().contains("goblin") && guess.toLowerCase().contains("king") && 
			    guess.toLowerCase().contains("treasure") && guess.toLowerCase().contains("ancient") && 
			    guess.toLowerCase().contains("cave")) {
				System.out.println("You have guessed the password correctly and you are now the owner of the magical key. You can now go to the cave and retrieve the treasure.");
			}
			else {
				System.out.println("You have guessed the password incorrectly and lost 100 gold. You now have 400 gold remaining.");
				System.out.println("You decide to cut your losses and leave the merchant.");
			}
		}
		else {
			System.out.println("You have decided not to try and guess the password. You walk away from the shop and continue your adventure.");
		}
		System.out.println("Your southern adventure ends here.");
		return false;
	}

	public static boolean handleEastDirection(String name) {
		System.out.println("You have chosen to go east, are you sure about this direction? The rumors are that the east road is full of bandits and thieves.");
		System.out.println("Yes, No, or Back?");
		String thieves = scanner.nextLine();
		
		if (thieves.equalsIgnoreCase("back")) {
			System.out.println("You decide to turn around and go back to choose another direction.");
			return true;
		}
		
		if (thieves.equalsIgnoreCase("yes")){
			System.out.println("You walk for 40 minutes and you are seeing in front of you a group of people who seem polite. As you get closer you start to see that one of them has a sword around the waist.");
			System.out.println("You decide to go into the nearby bushes and hide. As the group passes by, you can overhear what they are talking about.");
			System.out.println("They are planning to rob a nearby village. Do you want to stop them? (yes/no/back)");
			String stop = scanner.nextLine();
			
			if (stop.equalsIgnoreCase("back")) {
				System.out.println("You decide to turn around and go back to choose another direction.");
				return true;
			}
			
			if (stop.equalsIgnoreCase("yes")) {
				System.out.println("You are a very brave person. You decide to ambush the group. Because you only have a rock and a stick you are hurt by the sword. The people in fact were not thieves but they were the guardians of the village in front.");
				System.out.println("You were badly hit and now you are sitting in the hospital. You are lucky to be alive.");
				System.out.println("The guardians ask you why did you try to attack them. Please explain your reason:");
				String reason = scanner.nextLine();
				System.out.println("The guardians understand your reason: '" + reason + "' and they forgive you. They also give you a reward for your bravery.");
			}
			else {
				System.out.println("You decide not to stop them and you walk away from the group. You continue your adventure.");
				System.out.println("After an hour more of walking you see in the far distance a small village. When you arrive you see that there is a small tavern selling beers.");
				System.out.println("Do you want to go inside? (yes/no/back)");
				String tavern = scanner.nextLine();
				
				if (tavern.equalsIgnoreCase("back")) {
					System.out.println("You decide to turn around and go back to choose another direction.");
					return true;
				}
				
				if (tavern.equalsIgnoreCase("yes")) {
					System.out.println("You go inside the tavern and you see a group playing cards. The game that they are playing is called BELOT. An ancient soviet game. When you come and sit closer to them you can hear them saying that the giants are awake and everyone is afraid.");
					System.out.println("Do you want to approach them? (yes/no/back)");
					String approach = scanner.nextLine();
					
					if (approach.equalsIgnoreCase("back")) {
						System.out.println("You decide to turn around and go back to choose another direction.");
						return true;
					}
					
					if (approach.equalsIgnoreCase("yes")){
						System.out.println("They tell you that you need to buy them a beer first and they will think if it is worth talking to you first." );
						System.out.println("They are a group of 4 people. Each beer would set you back 25 gold. In your purse you only have 500");
						System.out.println("Do you want to buy them beers? (yes/no/back)");
						String belot = scanner.nextLine();
						
						if (belot.equalsIgnoreCase("back")) {
							System.out.println("You decide to turn around and go back to choose another direction.");
							return true;
						}
						
						if (belot.equalsIgnoreCase("yes")) {
							System.out.println("They are looking you from inside your soul and decide to answer truthfully three of your questions.");
							System.out.println("What would be those questions?");
							System.out.println("Here are some of the questions you can ask them: \n"
							+ "1. Who are the guardians?\n"
							+ "2. Who are the giants?\n"
							+ "3. When is the attack supposed to come?\n"
							+ "4. How can I defeat the giants?\n"
							+ "5. Where can I find the locations of the giants?\n" );
						    System.out.print("Enter a number 1-5 (or 0 to go back): ");
   						 	int choice = scanner.nextInt();  
   							scanner.nextLine(); 
   							
   							if (choice == 0) {
   								System.out.println("You decide to turn around and go back to choose another direction.");
								return true;
   							}
   							
							switch (choice) {
								case 1: 
									System.out.println("The guardians are the protectors of the eastern village. They are a group of strong and skilled warriors");
									break;
								case 2: 
									System.out.println("The giants are the mythical creatures that live above the skies in the clouds. They are some of the strongest creatures in the realm");
									break;
								case 3: 
									System.out.println("The attack is supposed to come in 3 days. You still have time to escape or prepare along us for the next attack.");
									break;
								case 4: 
									System.out.println("The only way to defeat the giants is to find the mythical sword once used by the ancient king of the eastern village. It is rumored that the sword was last seen in the southern forest.");
									break;
								case 5: 
									System.out.println("The giants are located in the sky but the only way to get them is with the help of town guardians. They have a special airship that can take you to the clouds.");
									break;
								default: 
									System.out.println("You have not chosen a valid question. Please choose a number between 1 and 5.");
									break;
							}
						}
					}
				}
			}
		}
		else {
			System.out.println("You coward. You decide to go back and choose another direction.");
			return true;
		}
		System.out.println("Your eastern adventure ends here.");
		return false;
	}

	public static boolean handleWestDirection(String name) {
		System.out.println("You have chosen to go west. You walk for hours and now you are approaching a mountain. At the top you are able to see a torch light as it is getting darker.");
		System.out.println("Do you want to go to the top of the mountain? (yes/no/back)");
		String mountain = scanner.nextLine();
		
		if (mountain.equalsIgnoreCase("back")) {
			System.out.println("You decide to turn around and go back to choose another direction.");
			return true;
		}
		
		if (mountain.equalsIgnoreCase("yes")) {
			System.out.println("You start to climb the mountain. After a while you reach the top and you see a small hut. You knock on the door and an old man appears.");
			System.out.println("The old man welcomes you and offers you shelter for the night.");
		}
		else {
			System.out.println("You decide not to climb the mountain and instead set up camp at the base.");
		}
		System.out.println("Your western adventure ends here.");
		return false;
	}

	public static int calculateAge(int year) {
        int currentYear = Year.now().getValue();
        return currentYear - year;
	}
}
