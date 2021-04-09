package finalProject;

import java.io.IOException;
import java.util.Scanner;

public class Concordance {
	public void concordance() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter File Path For Concordance :)");
		String filePath = scan.nextLine();
		System.out.println("Please Enter File Name For List To Be Saved In :)");
		String fileNameToSave = scan.nextLine() + ".txt";
		ListOfWords ls = new ListOfWords(filePath);
		ls.SortAndSave(fileNameToSave);
		menu(ls, scan, fileNameToSave);
		scan.close();

	}

	private void menu(ListOfWords ls, Scanner scan, String fileNameToSave) {
		boolean isFinished = false;
		char op;
		while (!isFinished) {
			System.out.println("~~#~~ CONCORDANCE MENU ~~#~~");
			System.out.println("1. Print Save File Name ");
			System.out.println("2. Search Word In Concordance ");
			System.out.println("3. Print Words In Range");
			System.out.println("4. Print Concordance ");
			System.out.println("Choose Your Option Or Press Any Key To Exit ");
			System.out.println("Your Option: ");
			op = scan.nextLine().charAt(0);
			switch (op) {
			case '1':
				System.out.println("File Name Is: " + fileNameToSave);
				System.out.println();
				break;
			case '2':
				System.out.println("Please Enter Word For Search: ");
				String word = scan.nextLine();
				word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
				System.out.println(ls.searchWord(word));
				break;
			case '3':
				System.out.println("Please Enter Start Char : ");
				char start = Character.toUpperCase(scan.nextLine().charAt(0));
				System.out.println("Please Enter End Char : ");
				char end = Character.toUpperCase(scan.nextLine().charAt(0));
				System.out.println(ls.showInRange(start, end));
				break;
			case '4':
				System.out.println(ls.toString());
				break;
			default:
				isFinished = true;
				System.out.println("Thank You, Bye Bye <3 !!");
				break;
			}

		}
	}
}
