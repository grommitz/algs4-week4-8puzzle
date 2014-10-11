import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Solver {

	private List<Board> solution = new ArrayList<>();
	
	private Comparator<Node> hammingComparator = new Comparator<Solver.Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.board.hamming() - o2.board.hamming();
		}
	};

	private Comparator<Node> manhattanComparator = new Comparator<Solver.Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.board.manhattan() - o2.board.manhattan();
		}
	};

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		solveWith(initial, manhattanComparator);
	}

	private void solveWith(Board initial, Comparator<Node> comparator) {
		MinPQ<Node> pq = new MinPQ<>(comparator);
		pq.insert(new Node(initial, null, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.delMin();
			if (solution.contains(curr.board)) {
				//System.out.println("cycle detected - not solveable!");
				solution = null;
				break;
			}
			solution.add(curr.board);
			if (curr.board.isGoal()) {
				//System.out.println("solved!");
				break;
			}
			for (Board n : curr.board.neighbors()) {
				if (!n.equals(curr.prev)) {
					pq.insert(new Node(n, curr.board, curr.moves+1));
				}
			}
		}
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return solution != null;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return isSolvable() ? solution.size() - 1 : -1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		return solution;
	}

	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
	
	private class Node {
		private final Board board;
		private final Board prev;
		private final int moves;
		public Node(Board board, Board prev, int moves) {
			this.board = board;
			this.prev = prev;
			this.moves = moves;
		}
		
	}
}
