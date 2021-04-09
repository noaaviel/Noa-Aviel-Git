package finalProject;

// This is the data structure MyList 
//that is implemented with an array of Word objects, integer that counts 
//the amount of words in the text and another integer that marks the next position we
//will insert another word
import java.util.Arrays;

public class MyList<T> {
	Words[] words;
	int numberOfWords;
	int insert;

	public MyList() {
		words = new Words[500];
		words[0] = (new Words(0,"NOWIC")); // holds the number of words in concordance 
		insert = 1;
		numberOfWords = 400;
	}

//method to add words to the array in the next free position
	public void add(Words word) {
		if (insert==numberOfWords) {
			words = Arrays.copyOf(words, numberOfWords + 1000); // need to to my copy of
			numberOfWords = numberOfWords + 1000;
		}
		words[insert] = word;
		insert++;
		words[0].updateNumOfWords();
	}

	public void set(int location, Words word) {
		words[location] = word;
	}

//return a Word object from the list by given index
	public Words getWordByIndex(int index) {
		return words[index];
	}

//return the String value of the word in index position
	public String getWordString(int index) {
		if(index>=insert)
			return null;
		if (words[index] != null)
			return words[index].getValue();
		return null;
	}

// returns line number in which word[index] is in
	public int getLineByIndex(int index) {
		return words[index].getLine();
	}

// updates line occurrences to a specific word
	public void updateLineNums(int whichWord, int whichIndex) {
		words[whichWord].setLine(whichIndex);
	}

	public Words[] getWords() {
		return words;
	}
// give the number of the array length by index 0
	public int getNumberOfWords() {
		return words[0].getLine();
	}

	public int getInsert() {
		return insert;
	}

	public int length() {
		return words.length;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("##### Concordance By Bar & Noa #####\n");
		sb.append("Number of words in concordance - " + getNumberOfWords() + "\n");
		for (int i = 1; i < words.length; i++) {
			if (words[i] != null)
				sb.append(words[i].toString());
		}
		return sb.toString();
	}

}
