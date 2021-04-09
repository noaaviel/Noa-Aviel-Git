package Q1;
//318323391 Noa Aviel
import java.io.FileNotFoundException;
import java.util.Scanner;

public class q1 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	//Scanning input from user
		Scanner scn = new Scanner(System.in);
		String word;
		int count = 0;
		System.out.println("Enter word for search :");
		word = scn.next();
		System.out.println("Enter number of articles :");
		int numOfArt = scn.nextInt();
		String[] articles = new String[numOfArt];
		MyThread[] myThread = new MyThread[numOfArt];
		//saving names of articles
		for (int i = 0; i < numOfArt; i++) {
			System.out.println("Please Enter Article Number " + (i + 1) + " name:");
			articles[i] = scn.next();
		}
		//making new thread for each article
		for (int i = 0; i < numOfArt; i++) {
			myThread[i] = new MyThread(articles[i], word);
			myThread[i].start();
			count += myThread[i].getCount();
		}
		Thread.sleep(1000);
		//printing total
		System.out.println("Total number of times the word 'that' appears in articles is : " + MyThread.finalCounter);
	}

}
