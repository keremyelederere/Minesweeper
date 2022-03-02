import java.awt.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.*;

public class MineSweeperGUI extends JPanel {

	private final ImageIcon smileyIcon = new ImageIcon("src/pictures1/smileyFace.jpg");
	private final ImageIcon sadIcon = new ImageIcon("src/pictures1/sadFace.jpg");
	private final ImageIcon mineIcon = new ImageIcon("src/pictures1/mine.png");
	private final ImageIcon flagIcon = new ImageIcon("src/pictures1/flag_icon.png");

	private ArrayList<ArrayList<JButton>> allButtons = new ArrayList<ArrayList<JButton>>();

	private JButton smileyButton;

	private Timer timer;

	private ActionListener1 actionListener;

	private JLabel timerLabel;

	DecimalFormat decFormat = new DecimalFormat("00");

	Font timerFont = new Font("Digital-7", Font.BOLD, 25);

	public MineSweeperGUI(int rows, int columns, int mines) {

		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(204, 204, 204));
		JPanel gameArea = new JPanel(new GridLayout());

		JLabel minesLeft = new JLabel("   	 " + Integer.toString(ActionListener1.flagCount) + "   	 ");
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(timerFont);
		timerLabel.setForeground(Color.RED);

		this.smileyButton = new JButton(smileyIcon);
		this.smileyButton.setLayout(null);
		this.smileyButton.setBackground(Color.WHITE);

		topPanel.add(minesLeft, BorderLayout.WEST);
		topPanel.add(timerLabel, BorderLayout.EAST);
		topPanel.add(smileyButton, null);
		add(topPanel, BorderLayout.NORTH);

		MineGrid grid = new MineGrid(rows, columns, mines);

		gameArea.setLayout(new GridLayout(rows, columns));
		for (int i = 0; i < rows; i++) {
			ArrayList<JButton> buttonsRow = new ArrayList<JButton>();
			for (int k = 0; k < columns; k++) {

				JButton button = new JButton();
				button.setBackground(new Color(204, 204, 204));
				gameArea.add(button);
				buttonsRow.add(button);
				button.addMouseListener(new ActionListener1(i, k, grid, this));
			}
			allButtons.add(buttonsRow);
		}

		add(gameArea, BorderLayout.CENTER);

		timer = new Timer();
		TimerTask task = new TimerTask() {
			int seconds = 0;
			int minutes = 0;

			public void run() {
				String secondsFormatted = decFormat.format(seconds);
				String minutesFormatted = decFormat.format(minutes);
				timerLabel.setText("    		" + minutesFormatted + ":" + secondsFormatted + "    		   ");
				this.seconds++;

				if (seconds == 60) {
					seconds = 0;
					minutes++;

					secondsFormatted = decFormat.format(seconds);
					minutesFormatted = decFormat.format(minutes);
					timerLabel.setText("    		" + minutesFormatted + ":" + secondsFormatted + "    		   ");
				}

			}
		};
		timer.schedule(task, 0, 1000);

	}

	public JButton getSmiley() {
		return smileyButton;
	}

	public ArrayList<ArrayList<JButton>> getAllButtons() {
		return allButtons;
	}

	public ImageIcon getSmileyIcon() {
		return smileyIcon;
	}

	public ImageIcon getSadIcon() {
		return sadIcon;
	}

	public ImageIcon getMineIcon() {
		return mineIcon;
	}

	public ImageIcon getFlagIcon() {
		return flagIcon;
	}

}
