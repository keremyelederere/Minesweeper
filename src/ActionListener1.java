
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class ActionListener1 extends MouseAdapter {

	private int row, col;
	private MineGrid grid;
	private MineSweeperGUI gui;
	public static int flagCount = 0;

	public ActionListener1(int x, int y, MineGrid g, MineSweeperGUI gui) {

		this.gui = gui;
		this.row = x;
		this.col = y;
		this.grid = g;
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getSource() instanceof JButton) {
			JButton button = (JButton) event.getSource();

			if (SwingUtilities.isLeftMouseButton(event)) {

				if (this.grid.isMINE(row, col)) {

					button.setIcon(new ImageIcon("src/pictures1/mine.png"));

					button.setBackground(Color.red);

					for (int curRow = 0; curRow <= 9; curRow++) {// 1-If the user clicks on a mine show all the mines
																	// and change the face icon from happy to sad
						for (int curColumn = 0; curColumn <= 9; curColumn++) {

							if (this.grid.isMINE(curRow, curColumn)) {

								this.gui.getAllButtons().get(curRow).get(curColumn)
										.setIcon(new ImageIcon("src/pictures1/mine.png"));

							}
						}
					}
					this.gui.getSmiley().setIcon(new ImageIcon("src/pictures1/sadFace.jpg"));

					JOptionPane.showMessageDialog(null, "GAME OVER!");

					System.exit(0);

				} else if (!(this.grid.getCellContent(row, col) == 0) && !(this.grid.isMINE(row, col))) {

					button.setText(String.valueOf(this.grid.getCellContent(row, col)));
				}

				if (this.grid.getCellContent(row, col) == 0) {

					button.setText(String.valueOf(this.grid.getCellContent(row, col)));

					this.grid.openNeighbours(row, col, grid, gui);

					/*
					 * 2- When the user opens a “0” cell (i.e. a cell with no mines on surrounding
					 * neighbors) open up all the neighbors as well and keep doing the same thing
					 * recursively if the neighbors are “0” as well
					 */
				}
			}
			if (SwingUtilities.isRightMouseButton(event)) {

				if (!(button.getIcon() == new ImageIcon("src/pictures1/flag_icon.png"))) {
					// if (!(this.grid.isFlagged(row, col))) {

					button.setIcon(new ImageIcon("src/pictures1/flag_icon.png"));// 1-When the user makes a right-click
																					// // on a button, that cell shall
																					// be
																					// “flagged”. Display a flag icon on
																					// the button.
					if (this.grid.isMINE(row, col)) {
						flagCount++;
						if (flagCount == grid.getMines()) {
							JOptionPane.showMessageDialog(null, "“You’re a genius."); // If all the mines are flagged,
																						// pop up a message saying
																						// “You’re a genius.” and end
																						// the game.
							System.exit(0);
						}
					}
				} else if ((button.getIcon() == new ImageIcon("src/pictures1/flag_icon.png"))) {
					button.setIcon(null);
					// 2-If the user right clicks on a flagged cell, the flag should be removed.
					// Once a button is clicked, clicking it again should have no effect
					if (this.grid.isMINE(row, col)) {
						flagCount--;
					}
				} else {

				}

			}

		}
	}
}
