import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


public class BoardTest {

	@Test
	public void createBoard() {
		Board b = board("1 2 3 / 5 7 8 / 0 6 4");
		System.out.println(b.toString());
	}

	@Test
	public void testIsGoal() {
		assertThat(board("1 2 3 / 5 7 8 / 0 6 4").isGoal(), is(false));
		assertThat(board("1 2 3 / 4 5 6 / 7 8 0").isGoal(), is(true));
	}

	@Test
	public void testHamming() {
		assertThat(board("1 2 3 / 5 4 6 / 7 8 0").hamming(), is(2));
		assertThat(board("1 2 3 / 4 5 6 / 7 8 0").hamming(), is(0));
		assertThat(board("3 1 2 / 6 0 5 / 4 7 8").hamming(), is(8));
	}

	@Test
	public void testManhattan() {
		assertThat(board("0 1 3 / 4 2 5 / 7 8 6").manhattan(), is(4));
		assertThat(board("4 1 3 / 0 2 5 / 7 8 6").manhattan(), is(5));
		assertThat(board("1 0 3 / 4 2 5 / 7 8 6").manhattan(), is(3));
		assertThat(board("1 2 3 / 4 5 6 / 7 8 0").manhattan(), is(0));
	}
	
	private Board board(String str) {
		int[][] blocks = getBlocks(str);
		return new Board(blocks);
	}
	
	private int[][] getBlocks(String str) {
		Scanner s = new Scanner(str);
		List<Integer> row = new ArrayList<>();
		int[][] blocks = null;
		int rowCount = 0;
		while (s.hasNext()) {
			String tok = s.next();
			if (tok.equals("/")) {
				if (blocks == null) {
					blocks = new int[row.size()][row.size()];
				}
				for (int j = 0; j < row.size(); ++j) {
					blocks[rowCount][j] = row.get(j);
				}
				rowCount++;
				row.clear();
			} else {
				row.add(Integer.parseInt(tok));
			}
		}
		if (!row.isEmpty()) {
			for (int j = 0; j < row.size(); ++j) {
				blocks[rowCount][j] = row.get(j);
			}
		}
		s.close();
		return blocks;
	}
	
}
