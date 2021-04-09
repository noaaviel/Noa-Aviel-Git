package Q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyThread extends Thread {
	//fields
	private File file;
	private String word, nameOfFile;
	private String search;
	private int counter = 0;
	public static int finalCounter = 0;

	//constructor
	public MyThread(String nameOfFile, String word) throws FileNotFoundException {
		try {
			this.nameOfFile = nameOfFile;
			this.word = word;
			file = new File(nameOfFile);
		} catch (Exception e) {
			throw new FileNotFoundException("The system cannot find the file specified");
		}
	}
  //getters and setters
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getNameOfFile() {
		return nameOfFile;
	}

	public void setNameOfFile(String nameOfFile) {
		this.nameOfFile = nameOfFile;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getCount() {
		return counter;
	}

	public void setCount(int count) {
		this.counter = count;
	}
//overriding run method 
	@Override
	public void run() {
		countWordOccurance();
	}
	
//counting words in file and counting occurrences of searched word
//and counting total of searched word with every invoke of the method (for each thread) 
	public void countWordOccurance() {
		try {
			Scanner scn = new Scanner(file);
			while (scn.hasNext()) {
				String str = scn.next();
				if (str.equalsIgnoreCase(word))
					counter++;

			}
			System.out.println("Word " + word + " appears in " + nameOfFile + " " + counter + " times" + "");
			finalCounter += counter;
			counter = 0;
			scn.close();
		} catch (FileNotFoundException e) {
			System.out.println("The system cannot find the file specified : " + nameOfFile);
		}
	}

}
