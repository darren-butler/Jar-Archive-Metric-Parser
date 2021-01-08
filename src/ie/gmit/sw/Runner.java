package ie.gmit.sw;

import java.util.Scanner;

/**
 * Entry point for running the application. 
 * @author Darren
 *
 */
public class Runner {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int option = 1;
		MetricDB metricDB = new MetricDB();

		do {
			printMenu();
			option = in.nextInt();

			switch (option) {
			case 1:
				System.out.print("Fully Qualified Jar Name: ");
				String str = in.next();
				try {
					metricDB.load(str);
				} catch (Exception e) {
					System.out.println("[ERROR] Something went wrong with jar");
					e.printStackTrace();
				}
				break;
			case 2:
				metricDB.query();
				break;
			case 0:
				System.out.println("Quitting...");
				break;
			default:
				System.out.println("Invalid Option");
			}

		} while (option != 0);

		in.close();
	}
	
	/**
	 * Prints menu options to console.
	 */
	static void printMenu() {
		System.out.println("--MENU--");
		System.out.println("1. Load New Jar");
		System.out.println("2. Jar Metrics");
		System.out.println("0. Quit");
		System.out.print(">");
	}
}
