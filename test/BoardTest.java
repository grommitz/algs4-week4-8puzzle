import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


public class BoardTest {

	@Test
	public void createBoard() {
		Board b = from("1 2 3 / 5 7 8 / 0 6 4");
		System.out.println(b.toString());
	}

	@Test
	public void testIsGoal() {
		Board b = from("1 2 3 / 5 7 8 / 0 6 4");
		assertThat(b.isGoal(), is(false));
		Board c = from("1 2 3 / 4 5 6 / 7 8 0");
		assertThat(c.isGoal(), is(true));
	}

	@Test
	public void testHamming() {
		Board b = from("1 2 3 / 5 4 6 / 7 8 0");
		assertThat(b.hamming(), is(2));
		Board c = from("1 2 3 / 4 5 6 / 7 8 0");
		assertThat(c.hamming(), is(0));
		Board d = from("3 1 2 / 6 0 5 / 4 7 8");
		assertThat(d.hamming(), is(8));
	}

	private Board from(String str) {
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
