
public class Board {

	private final int[][] board;
	private final int N;
	
	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.N = blocks.length;
		board = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				board[i][j] = blocks[i][j];
			}
		}
	}
	
	// board dimension N
	public int dimension() {
		return N;
	}
	
	// number of blocks out of place
	public int hamming() {
		return -1;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		return -1;
	}
	
	// is this board the goal board?
	public boolean isGoal() {
		for (int i = 0; i < N * N - 2; ++i) {
			if (at(i) != i + 1) 
				return false;
		}
		return true;
	}
	
	private int at(int i) {
		int row = i / N;
		int col = i % N;
		return board[row][col];
	}
	// a boadr that is obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		return null;
	}
	
	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null || y.getClass() != Board.class) {
			return false;
		}
		Board that = (Board) y;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (this.board[i][j] != that.board[i][j]) return false;
			}
		}
		return true;
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
	}
	
	// string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
	}
}
