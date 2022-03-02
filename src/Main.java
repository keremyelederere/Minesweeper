import javax.swing.*;

public class Main {

	private static final int MINES_IN_TOTAL = 10;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Mine Sweeper | # of mines: " + MINES_IN_TOTAL);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MineSweeperGUI(10, 10, MINES_IN_TOTAL));
		frame.setVisible(true);

	}

}
