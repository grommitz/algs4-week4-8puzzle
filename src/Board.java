import javax.management.RuntimeErrorException;


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
		int outOfPlace = 0;
		for (int i = 0; i < N * N - 1; ++i) {
			if (at(i) != i + 1) 
				++outOfPlace;
		}
		return outOfPlace;
	}
	
	private class Point {
		final int i, j;
		Point(int pos) {
			this.i = pos / N;
			this.j = pos % N;
		}
		Point(int i, int j) {
			this.i = i; this.j = j;
		}
		int dist(Point other) {
			return Math.abs(i - other.i) + Math.abs(j - other.j);
		}
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int dist = 0;
		for (int i = 0; i < N * N - 1; ++i) {
			if (at(i) != i + 1) {
				Point p = find(i + 1);
				dist += new Point(i).dist(p);
			}
		}
		return dist;
	}
	
	private Point find(int value) {
		for (int i = 0; i < N * N; ++i) {
			if (at(i) == value) return new Point(i);
		}
		throw new RuntimeException(value + " is not on the board");
	}
	
	// is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
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
