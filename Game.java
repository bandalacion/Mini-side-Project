import java.time.Year;
import java.util.Scanner; 

public class Game {
	private static Scanner scanner;

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
		// ----- Write your code below
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		System.out.println("Hola " + name);
		scanner = new Scanner(System.in);
		System.out.println("When were you born " + name);
        int year = scanner.nextInt(); 
		System.out.println("Hey " + name + " you were born in the year " + year);
		scanner = new Scanner(System.in);
		int age = calculateAge(year);
		System.out.println(name + " you are " + age + " years old.");

		//put an if else statement as i want to determine the age above 21 and below 21 to have 2 different stories
		if (age >= 60) {
			System.out.println("How are you even running this grandpa? I thought old people can't even turn on their video on calls.");
		}
		else {
			System.out.println("Hey there " + name + "since you are here lets start our adventure throught the Forgotten Realms. The legend going around the city is that they are in need of a new \n"
			+ "fighter, as the last one has gone to retrieve the magical staff from the mystical blue dragon, who is believed to be named lyrmith.\n "
			+ "Are you ready explore the Forgotten Realms?"
			);
		}
		scanner = new Scanner(System.in);
		String ready = scanner.nextLine();
		if (ready.equals("yes")) {
			System.out.println("Great! Let's start our adventure.");
		}
		else {
			System.out.println("You know what " + name + ", I understand you are scared. But you have to be brave. You can do this. I believe in you.");
		}
		//north direction input
		scanner = new Scanner(System.in);
		System.out.println("Where would you like to go?");
		System.out.println("North, South, East or West?");
		String north = scanner.nextLine();
		System.out.println("So you chose the " + north + " north direction. You know that people are very scared to go there. It is said to be the land of the giants and goblins.");
		System.out.println("Are you sure about this? ");
		scanner = new Scanner(System.in);
		String ys = scanner.nextLine();
	//if statement here about if yes or if no
	//if no say something about that if he is scared and try to manipulate him to go to the north directio
		if (ys.equals("yes")) {
		System.out.println("You walk for hours and hours, you are very tired and thirsty. You see a small village in the distance. Do you want to go there?");
		}
		else {
			System.out.println("You know what " + name + ", I understand you are scared. But you have to be brave. You can do this. I believe in you.");
			System.out.println("You walk for hours and hours, you are very tired and thirsty. You see a small village in the distance. Do you want to go there?");
		}
		scanner = new Scanner(System.in);
		String village = scanner.nextLine();
		if (village.equals("yes")) {
			System.out.println("You arrive at the village and see a tavern. You go inside and see a group of people playing cards. Do you want to join them?");
			scanner = new Scanner(System.in);
			String cards = scanner.nextLine();
			if (cards.equals("yes")) {
				System.out.println("You sit down and start playing cards with the group. After a while, you win a lot of money. You are now rich!");
			}
			else {
				System.out.println("You decide not to join the group and instead go to the bar to get a drink. You see a beautiful");
			}
		}
	
	else { 
		System.out.println("You have decided not to go to the village as it seems too dangerous for you. You walk for hours and hours,after a while you are starting to get very tired and falling asleep you decide to sleep in a nearby forest.");
		System.out.println("You are being woken up in the middle of the night by some weird noises. Do you want to investigate?");
		scanner = new Scanner(System.in);
		String investigate = scanner.nextLine();
		if (investigate.equals("yes")) {
			System.out.println("You decide to investigate the noises and find a group of goblins. They are very angry and start chasing you. You run as fast as you can and manage to escape.");
		}
		else {
			System.out.println("You decide not to investigate the noise and instea packed up your bags and starting running out back to the dirt path you were walking before.");
		}
	}
	



		// ----- Write your code above
	}

	public static int calculateAge(int year) {
	// // ----- Write your code below
        int currentYear = Year.now().getValue();
        return currentYear - year;
	// // ----- Write your code above
	// }
}
}
