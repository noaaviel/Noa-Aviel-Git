
public class NqueensProblem {

	final static int SIZE = 8, QUEEN = 1, DT = 2;

	// board is initialized to zero, 1->queen 2->don't touch
	public static void main(String[] args) {

	}

	boolean isValid(int board[][], int rowIndex, int colIndex) {

		// check row
		for (int i = 0; i < SIZE; i++) {
			if (board[rowIndex][i] == DT) {
				// meaning in this row we have a queen
				return false;
			}
		}

		// check col
		for (int i = 0; i < SIZE; i++) {
			if (board[i][colIndex] == DT) {
				return false;
			}
		}

		// check diagonal left to right
		for (int i = 0; i < SIZE; i++) {
			if (rowIndex + i == SIZE || colIndex + i == SIZE) {
				break;
			}
			if (board[rowIndex + i][colIndex + i] == DT) {
				return false;
			}
		}

		for (int i = rowIndex - 1; i <= 0; i--) {
			if (i == -1 || colIndex == SIZE) {
				break;
			}
			if (board[i][++colIndex] == DT) {
				return false;
			}
		}

		return true;
	}

	int[][] rec(int board[][], int initRow, int initCol, int queenCounter) {

		// if all queens are in valid positions
		if (initCol == SIZE && queenCounter == SIZE) {
			return board;
		}
		if (initRow == SIZE && queenCounter != SIZE) {
			// clear board
			return rec(board, initRow++, initCol, queenCounter);
		}
		if (isValid(board, initRow, initCol) == true) {
			board[initRow][initCol] = QUEEN;
			// function that fills with 2's
			return rec(board, initRow, initCol + 1, queenCounter++);
		} else {
			return rec(board, initRow++, initCol, queenCounter);
		}
	}

}
