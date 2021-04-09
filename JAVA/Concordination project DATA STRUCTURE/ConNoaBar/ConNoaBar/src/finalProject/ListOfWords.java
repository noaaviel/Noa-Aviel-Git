package finalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//this class was generated in order to keep 
//all the words in our data structure
public class ListOfWords {
	MyList<Words> allWords;
	int lineIndex;
	int updateWordLine;

//constructor 
	public ListOfWords(String fp) throws IOException {
		allWords = new MyList<>();
		lineIndex = 0;
		updateWordLine = 0;
		readTextToList(fp);

	}

	private void readTextToList(String fr) throws IOException {
		BufferedReader bufReader = new BufferedReader(new FileReader(fr));
		String line = bufReader.readLine();
		while (line != null) {
			lineIndex++;
			String[] stringsInLine = line.split(" ");
			stringsInLine = duplicateWordsInLine(stringsInLine);
			for (int i = 0; i < stringsInLine.length; i++) {
				if (stringsInLine[i] != null) {
					stringsInLine[i] = cutPun(stringsInLine[i]);
					if (stringsInLine[i].equals(("")))
						continue;
					if (!isPunctuationSymbol(stringsInLine[i].charAt(0))) {
						stringsInLine[i] = stringsInLine[i].substring(0, 1).toUpperCase()
								+ stringsInLine[i].substring(1).toLowerCase();
						allWords.add(new Words(lineIndex, stringsInLine[i]));
					}
				}
			}
			line = bufReader.readLine();
		}
		bufReader.close();
		allWords = duplicateWordsInList(allWords); // delete all the duplicate words in the whole list
	}

	private void saveWords(String fileNameToSave) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File(fileNameToSave));
		writer.println("##### Concordance By Bar & Noa #####\n");
		writer.println("Number of words in concordance - " + allWords.getNumberOfWords() + "\n");
		for (int i = 1; i < allWords.getNumberOfWords(); i++) {
			if (allWords.getWordByIndex(i) != null)
				writer.println(allWords.getWordByIndex(i).toString());
		}
		writer.close();
	}

//this function returns array of all unique words in text
	private String[] duplicateWordsInLine(String[] helper) {
		String[] duplicateWords = new String[helper.length];
		int inesrt = 0;
		for (int i = 0; i < helper.length; i++) {
			if (myContains(duplicateWords, helper[i]) == false) {
				duplicateWords[inesrt] = helper[i];
				inesrt++;
			}
		}
		return duplicateWords;
	}

//returns true if string is equal to a word in duplicatewords
	private boolean myContains(String[] duplicateWords, String string) {
		for (int i = 0; i < duplicateWords.length; i++) {
			if (duplicateWords[i] != null && duplicateWords[i].equals(string))
				return true;
		}
		return false;
	}

//returns Mylist of all unique words and their appearances in the text by lines
	private MyList<Words> duplicateWordsInList(MyList<Words> wordsArr) { // now we want to see all the duplicate words
																			// // them and all the lines he was
		MyList<Words> duplicateWordsInList = new MyList<>();
		for (int i = 1; i < wordsArr.getNumberOfWords(); i++) {
			if (myContainsList(duplicateWordsInList, wordsArr.getWordString(i)) == false) {
				duplicateWordsInList.add(wordsArr.getWordByIndex(i));
			} else
				duplicateWordsInList.updateLineNums(updateWordLine, wordsArr.getLineByIndex(i));
		}
		return duplicateWordsInList;
	}

// updates word lines for equal words
//returns true if string is equal to a word in duplicatewords
	private boolean myContainsList(MyList<Words> duplicateWords, String string) {
		int i = 0;
		while (duplicateWords.getWordString(i) != null) {
			if (duplicateWords.getWordString(i).equals(string)) {
				updateWordLine = i;
				return true;
			}
			i++;
		}
		return false;
	}

// returns 1 if word1>word2 , 0 if ==, -1 if word1<word2
	private int MyCompareTo(String word1, String word2) {
		char[] chars1 = word1.toCharArray();
		char[] chars2 = word2.toCharArray();
		int longestChars = 0, returnValue;
		if (chars1.length > chars2.length) {
			longestChars = chars2.length; // to avoid null
			returnValue = 1;
		} else if (chars1.length < chars2.length) {
			longestChars = chars1.length;
			returnValue = -1;
		} else {
			returnValue = 0; // means both equals by they length.
			longestChars = chars1.length;
		}
		for (int i = 0; i < longestChars; i++) {
			if (chars1[i] > chars2[i])
				return chars1[i] - chars2[i];
			else if (chars1[i] < chars2[i])
				return chars1[i] - chars2[i];
		}
// if here means both equals till this part and we return the returnValue
		return returnValue;
	}

	public void SortAndSave(String fileNameToSave) throws FileNotFoundException {
		for (int i = 1; i < allWords.length(); i++) {
			for (int j = i; j <= allWords.length() - 1; j++) {
				if (allWords.getWordString(i) != null && allWords.getWordString(j) != null) {
					if (MyCompareTo(allWords.getWordString(i), allWords.getWordString(j)) > 0) {
						Words tmp = allWords.getWordByIndex(i);
						allWords.set(i, allWords.getWordByIndex(j));
						allWords.set(j, tmp);
					}
				}
			}
		}
		saveWords(fileNameToSave);
	}

// finds punctuation in text and trims it.
	private String cutPun(String word) {
		word = word.trim();
		if (word.length() == 0)
			return word;

		if (isPunctuationSymbol(word.charAt(0)))
			return cutPun(word.substring(1));

		int endIndex = word.length() - 1;
		if (isPunctuationSymbol(word.charAt(endIndex)))
			return cutPun(word.substring(0, endIndex));

		return word;
	}

// identify char as punctuation
	private boolean isPunctuationSymbol(char ch) {
		return "#%{}”[]’’‘_’&^—*–- .,;:!?—()—'/\"><+`@~'=|".indexOf(ch) != -1;
	}

	public String searchWord(String wordToSearch) {
		// Returns index of x if it is present in arr[],
		// else return -1
		int l = 0, r = allWords.getNumberOfWords()-1;
		while (l <= r) {
			int m = l + (r - l) / 2;

			int res = MyCompareTo(wordToSearch, allWords.getWordString(m));

			// Check if x is present at mid
			if (res == 0)
				return allWords.getWordByIndex(m).toString();

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		return "Word Not Found\n";

	}
	
	public String showInRange(char start , char end) {
		StringBuffer sb = new StringBuffer();
		sb.append("The Words In Range From " + start + " To "  + end + ":\n");
		for (int i = 0; i < allWords.getNumberOfWords(); i++) {
			if((allWords.getWordString(i).charAt(0)>=start) && (allWords.getWordString(i).charAt(0)<=end))
				sb.append(allWords.getWordByIndex(i).toString() + "\n");
		}
		
		return sb.toString();
	}

// getters and setters

	public MyList<Words> getWords() {
		return allWords;
	}

	public int howManyWords() {
		return allWords.getNumberOfWords();
	}

	public String toString() {
		return allWords.toString();
	}

}
