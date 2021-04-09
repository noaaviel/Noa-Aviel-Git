package finalProject;

import java.util.Arrays;

//this is the Word object that contains the String value 
//and all the line indexes it appears in
public class Words {
	private int []index;
	private int insertIndext;
	private String value;

	public Words(int givenIndex, String value) {
		index = new int [500];
		index[0] = givenIndex;
		insertIndext++;
		this.value = value;
	}

	public int getLine() {
		return index[0];
	}
	
	public void setLine(int index1) {
		if(insertIndext == index.length) {
			index =  Arrays.copyOf(index, index.length+ 1000);
		}
		index[insertIndext] = index1;
		insertIndext++;
	}

	public String getValue() {
		return value;
	}
	public void updateNumOfWords() {
		index[0]++;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("The Word : ");
		builder.append(value+" , ");
		builder.append(" Appears In Line: ");
		builder.append(index[0]);
		for (int i = 1; i < index.length; i++) {
			if(index[i]!=0)
				builder.append(", "+index[i]);
		}
		builder.append("\n");
		return builder.toString();
	}

}
