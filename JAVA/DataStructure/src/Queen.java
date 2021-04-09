//318323391
class Queen {

	final static int[][] board = new int[8][8];
	final static int SIZE = 8;

	public static void main(String[] args) {
		recQueen(SIZE);
		// printing the board
		printBoard();

	}

	static void printBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++)
				System.out.print(board[i][j] + "\t");
			System.out.print("\n");
		}
	}

	// function to check if the Queen is in possible position with no harm in
	// sight(valid board)
	static boolean isQueenSafe(int i, int j) {
		// checking if there's a queen in row or col
		for (int k = 0; k < SIZE; k++) {
			if ((board[i][k] == 1) || (board[k][j] == 1))
				return true;
		}
		// checking diagonals
		for (int k = 0; k < SIZE; k++) {
			for (int l = 0; l < SIZE; l++) {
				if (((k + l) == (i + j)) || ((k - l) == (i - j))) {
					if (board[k][l] == 1)
						return true;
				}
			}
		}
		return false;
	}

	static boolean recQueen(int n) {
		// if n is 0, all queens on board in good position
		if (n == 0)
			return true;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				// checking if we can put a queen here
				// queen will not be placed if the place is being 'attacked' -> not valid
				// or the place is already taken by another queen
				if ((!isQueenSafe(i, j)) && (board[i][j] != 1)) {
					board[i][j] = 1;
					// even if we can put the next queen with this board or not(for valid & invalid
					// board)
					if (recQueen(n - 1) == true) {
						return true;
					}
					board[i][j] = 0;
				}
			}
		}
		return false;
	}

}