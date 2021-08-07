package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. Youâ€™ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Donâ€™t change
	// â€œthisâ€� grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		outerloop:
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (values[i][j] == 0) {
					xOfNextEmptyCell = i;
					yOfNextEmptyCell = j;
					break outerloop;
				}
			}
		}
		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int i = 1; i <= 9; i++) {
			Grid newGrid = new Grid(this);
			newGrid.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			grids.add(newGrid);
		}
		return grids;
	}
	
	
	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		// Check every row. If you find an illegal row, return false.
		for (int i = 0; i < 9; i++) { 						// row
			for (int j = 0; j < 9; j++) { 					// column
				int num = values[i][j];
				for (int k = j + 1; k < 9; k++) { 			// checks individual elements in row
					if (num == values[i][k] && num != 0)
						return false;
				}
				for (int k = i + 1; k < 9; k++) { 			// checks individual elements in column
					if (num == values[k][j] && num != 0)
						return false;
				}
				for (int u = (i - i % 3); u < (i - i % 3) + 3; u++) {
					for (int c = (j - j % 3); c < (j - j % 3) + 3; c++) {
						int num2 = values[u][c];
						if (num == num2 && u != i && c != j && num2 != 0)
							return false;
					}
				}
			}
		}
		
		// All rows/cols/blocks are legal.
		return true;
	}

	
	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (values[i][j] < 1 || values[i][j] > 9) { // checks if every cell member has a digit from 1 to 9
					return false;
				}
			}
		}
		return true;
	}
	

	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		if (x.getClass() == Grid.class) {
			Grid s = (Grid) x;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (s.values[i][j] != this.values[i][j])
						return false;
				}
			}
		}
		return true;	
	}
	
	public static void main(String[] args) {
		Grid grid = new Grid(TestGridSupplier.getPuzzle1());
		System.out.println(grid.isFull());
		System.out.println(grid.isLegal());
		Grid grid2 = new Grid(TestGridSupplier.getPuzzle1());
		ArrayList<Grid> grids = grid2.next9Grids();
		for (Grid grids2 : grids) {
			System.out.println(grids2.toString());
		}
	}
}
