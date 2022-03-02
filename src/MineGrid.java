
import java.util.Random;

public class MineGrid {

	private static final int MINE = -1;
	private int[][] mineInfo;
	private boolean[][] isClicked;
	private boolean[][] isFlagged;

	int rows, columns, mines;

	public MineGrid(int rows, int columns, int mines) {
		this.mineInfo = new int[rows][columns];
		this.initializeCells();
		this.placeMines(mines);
		this.setMineInfo();

	}

	private void initializeCells() {
		for (int i = 0; i < mineInfo.length; i++) {
			for (int k = 0; k < mineInfo[0].length; k++) {
				this.mineInfo[i][k] = 0;
			}
		}
	}

	private void placeMines(int mines) {
		Random rand = new Random();
		for (int i = 0; i < mines; i++) {
			int r = rand.nextInt(mineInfo.length);
			int c = rand.nextInt(mineInfo[0].length);
			if (mineInfo[r][c] != MINE) {
				mineInfo[r][c] = MINE;
			} else {
				i--;
			}
		}
	}

	public boolean isMINE(int i, int k) {
		return this.mineInfo[i][k] == MINE;

	}

	private void setMineInfo() {
		for (int i = 0; i < this.mineInfo.length; i++) {
			for (int k = 0; k < this.mineInfo[0].length; k++) {
				if (this.mineInfo[i][k] == MINE) {
					this.incrementMineCount(i - 1, k - 1);
					this.incrementMineCount(i - 1, k);
					this.incrementMineCount(i - 1, k + 1);

					this.incrementMineCount(i, k - 1);
					this.incrementMineCount(i, k + 1);

					this.incrementMineCount(i + 1, k - 1);
					this.incrementMineCount(i + 1, k);
					this.incrementMineCount(i + 1, k + 1);
				}

			}
		}
	}

	private void incrementMineCount(int i, int k) {
		if (isInsideGrid(i, k) && !isMINE(i, k)) {
			this.mineInfo[i][k]++;
		}

	}

	public boolean isInsideGrid(int i, int k) {
		return (i >= 0 && i < this.mineInfo.length) && (k >= 0 && k < this.mineInfo[0].length);
	}

	public int getCellContent(int i, int k) {
		return this.mineInfo[i][k];
	}

	public int getMines() {
		return this.mines;
	}

	public boolean isClicked(int row, int column) {
		return this.isClicked[row][column];
	}

	public void setClicked(int row, int column) {
		this.isClicked[row][column] = true;
	}

	public void setFlagged(int row, int column) {
		this.isFlagged[row][column] = true;
	}

	public boolean isFlagged(int row, int column) {
		return this.isFlagged[row][column];
	}

	public void openNeighbours(int row, int col, MineGrid grid, MineSweeperGUI gui) {


		for (int neighbourRow = row - 1; neighbourRow <= row + 1; neighbourRow++) {

			for (int neighbourColumn = col - 1; neighbourColumn <= col + 1; neighbourColumn++) {

				if (grid.isInsideGrid(neighbourRow, neighbourColumn)) {
					gui.getAllButtons().get(neighbourRow).get(neighbourColumn)
							.setText(String.valueOf(grid.getCellContent(neighbourRow, neighbourColumn)));

				}

			}
		}

	}

}
