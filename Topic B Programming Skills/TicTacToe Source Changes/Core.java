import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Core {

	// The display and shell variables used to setup the window.
	private Display display;
	private Shell shell;
	
	//Logic Classes
	private GameBoard gameBoard;
	private Logic logic;
	
	// Game score / reset
	private int clickedButtonID = 0;
	private int playerOneScore;
	private int playerTwoScore;
	private long startTime;
	private boolean needsRefresh;
	
	// Data swt widgets
	private Button reset;
	private Button playerOneScoreButton;
	private Button playerTwoScoreButton;
	private Button timeToMove;
	
	// constants
	private final String RESET_BTN_TEXT = "Restart Game";
	private final String TIME_TO_MOVE = "Move took: ";
	private final String TIME_TO_MOVE_NO_MOVE = "No Move Yet";
	
	/*
	 * The List to store the all of the buttons that can be clicked in the game.
	 */
	private List<Button> gridButtons = new ArrayList<>();

	/*
	 * Determine which player turn it is, start with X then swap to player O,
	 * then back, vis-versa
	 */
	private boolean playerXTurn = true;

	/*
	 * This is where everything will be initialized, setting up the display
	 * creating the 3x3 X3 grids, and any other methods to setup the game.
	 */
	public Core() {
		setupDisplay();
	}

	/*
	 * This method will perform everything that is needed in order to setup the
	 * game and display the game. Initialize the display and shell, generate the
	 * buttons add the click listeners, set the size, and code for disposal
	 */
	private void setupDisplay() {
		display = new Display();
		shell = new Shell(display);
		
		gameBoard = new GameBoard();
		logic = new Logic();
		
		// Set the title
		shell.setText("3D Tic Tac Toe");

		// Set scores
		playerOneScore = 0;
		playerTwoScore = 0;
		startTime = System.currentTimeMillis();
		
		needsRefresh = false;

		// Setup the grids / buttons
		generateGridButtons();

		// Add Listeners
		addClickListeners();

		shell.pack();
		shell.setSize(400, 618);
		shell.setVisible(true);

		// Check for if the x button is closed.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		shell.dispose();
	}

	/*
	 * This method will generate all of the grid buttons onto the shell and
	 * display them.
	 */
	private void generateGridButtons() {
		int x = 0;
		int y = 0;

		// Loop through 27 times, 3x3 -- 3 times
		for (int i = 0; i < 27; i++) {
			/*
			 * Create an empty button with the size 60x60 pixels then set the
			 * location according the the x and y.
			 */
			Button button = new Button(shell, SWT.PUSH);
			button.setSize(new Point(60, 60));
			button.setLocation(new Point(x, y));
			// button.setFont(new Font(display, "Tahoma", 10, SWT.BOLD));
			// button.setText("" + i);
			button.setVisible(true);

			// Add 60 to x
			x += 60;
			// If x is 180; so 3 buttons on a row, set
			// x to 0 to start on the left then increase y
			// by 60 to move down a row.
			if (x == 180) {
				x = 0;
				y += 60;

				/*
				 * Add Spacer in between the grids
				 */

				if (y == 180 || y == 380) {
					// Button filler = new Button(shell, SWT.BORDER);
					Label filler = new Label(shell, SWT.PUSH);
					filler.setSize(new Point(180, 20));
					filler.setLocation(new Point(x, y));
					filler.setVisible(true);
					// add 20 to keep formatting nice
					y += 20;
				}
			}

			// Finally add the buttons to the list for later use.
			gridButtons.add(button);
		}

		// Reset button
		reset = new Button(shell, SWT.PUSH);
		reset.setSize(new Point(180, 60));
		reset.setLocation(new Point(195, 510));
		reset.setText(RESET_BTN_TEXT);
		reset.setVisible(true);

		// Player Score Buttons //
		playerOneScoreButton = new Button(shell, SWT.PUSH);
		playerOneScoreButton.setSize(new Point(180, 60));
		playerOneScoreButton.setLocation(new Point(195, 25));
		playerOneScoreButton.setText("Player One Wins: " + playerOneScore);
		playerOneScoreButton.setVisible(true);

		playerTwoScoreButton = new Button(shell, SWT.PUSH);
		playerTwoScoreButton.setSize(new Point(180, 60));
		playerTwoScoreButton.setLocation(new Point(195, 95));
		playerTwoScoreButton.setText("Player Two Wins: " + playerTwoScore);
		playerTwoScoreButton.setVisible(true);
		
		timeToMove = new Button(shell, SWT.PUSH);
		timeToMove.setSize(new Point(180, 60));
		timeToMove.setLocation(new Point(195, 440));
		timeToMove.setText(TIME_TO_MOVE_NO_MOVE);
		timeToMove.setVisible(true);
		
		Label label = new Label(shell, SWT.BORDER);
		label.setSize(new Point(180, 255));
		label.setLocation(new Point(195, 170));
		label.setText("===== Game Designers ===== \n\n" +
					  "	    Kiran Hart \n\n" + 
					  "	    Rahul Tailor \n\n" + 
					  "	    Antonio N-J \n\n" + 
					  "	    Daniel Gopal \n\n" +
					  "	    Calvin Ye \n\n" + 
					  "	    Robin Saran \n\n" +
					  "---------------------------------------\n" +
					  "           Thank you for playing");
		label.setVisible(true);
	}

	/*
	 * This method will loop through each of the buttons in the gridButtons
	 * array and add the mouse listener class to the button, by getting the
	 * button by index I.
	 */
	private void addClickListeners() {
		for (int i = 0; i < gridButtons.size(); i++) {
			gridButtons.get(i).addMouseListener(new MouseListeners(gridButtons.get(i)));
		}
		reset.addMouseListener(new MouseListeners(reset));
	}
	
	/*
	 * Restart the game
	 */
	private void resetGame() {
		//Reset GameBoard
		gameBoard.RefreshBoard();
		
		//Reset Text;
		for (Button b : gridButtons) {
			b.setText("");
		}
		
		timeToMove.setText(TIME_TO_MOVE_NO_MOVE);
		startTime = System.currentTimeMillis();
		updateButtonScores();
		needsRefresh = false;
	}
	
	/*
	 * Update button score;
	 */
	private void updateButtonScores() {
		playerOneScoreButton.setText("Player One Wins: " + playerOneScore);
		playerTwoScoreButton.setText("Player Two Wins: " + playerTwoScore);
	}
	
	/*
	 * This is an inner class that implements the mouse listener this helps
	 * minimize the code as much as possible, so I can loop through each button
	 * and add the listener, and perform any action from there. Gotta make it
	 * efficient you know.
	 */
	class MouseListeners implements MouseListener {

		private Button button;

		public MouseListeners(Button button) {
			this.button = button;
		}

		/*
		 * Check when the player clicks on the button, if they click perform any
		 * check needed to place the O or X character, and check wins.
		 */
		@Override
		public void mouseDown(MouseEvent e) {
			
			/*
			 * Check if the restart button is clicked
			 * if so, reset the game.
			 */
			if (button.getText().equalsIgnoreCase(RESET_BTN_TEXT)) {
				resetGame();
				return;
			}
			
			// Get the button index in the array on click.
			clickedButtonID = gridButtons.indexOf(button);
			if (gameBoard.checkMove(clickedButtonID)) {
				// Make a move depending on who's turn it is on the specific
				// level, row and column.
				String playerXO = GameBoard.playerX;
				if (!playerXTurn) {
					playerXO = GameBoard.playerO;
				}
				if (gameBoard.makeMove(playerXO,clickedButtonID))  {
					
				}
			}
			
			if (logic.checkWinnerX()) {
				needsRefresh = true;
				playerOneScore++;
			} else if (logic.checkWinnerO()) {
				needsRefresh = true;
				playerTwoScore++;
			} else {
				
				/*
				 * Mr. Nestor's mapping method to get the specific level, row,
				 * column depending on which button is clicked and it's ID, in a
				 * single dimensional array.
				 * https://github.com/Greg5519/ICS4C0/blob/master/Topic%20B%20Programming%20Skills/3D%20TTT%20Mapper.txt
				 */
				double remainder = clickedButtonID % 9;
				int level = (int) clickedButtonID / 9;
				int row = (int) remainder / 3;
				int col = (int) remainder % 3;

				// Check if the move can be made to the specific level, row and
				// column by checking if the space is blank
				if (gameBoard.checkMove(Integer.valueOf(level + 1), Integer.valueOf(row + 1), Integer.valueOf(col + 1)).equalsIgnoreCase(GameBoard.playerFree)) {
					// Make a move depending on who's turn it is on the specific
					// level, row and column.
					if (gameBoard.makeMove((playerXTurn) ? GameBoard.playerX : GameBoard.playerO, Integer.valueOf(level + 1), Integer.valueOf(row + 1), Integer.valueOf(col + 1))) {
						
						//Check if game needs to be refreshed
						if (!needsRefresh) {
							
							System.out.println("Made move to level: " + level + " row: " + row + " col: " + col);

							/*
							 * Update the button text to let the player know which
							 * player made a move onto that grid slot.
							 */
							button.setText((playerXTurn) ? GameBoard.playerX : GameBoard.playerO);
							
							// Swap the player turn.
							playerXTurn = !playerXTurn;
							
							// Record Time it took to make move
							long secs = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
							timeToMove.setText(String.valueOf((secs <= 0) ? "Move took less than 1 sec." : TIME_TO_MOVE + String.valueOf(secs) + "s"));
							startTime = System.currentTimeMillis();
						
						} else {
							System.out.println("The game needs to be restarted to continue.");
						}
					}
				} else {
					System.out.println("The player: \"" + button.getText() + "\" already chose there.");
				}
			}
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}

	}
	
	/*
	 * This is the main method, the program will call this method and run any
	 * code within this class upon pressing start
	 */
	public static void main(String[] args) {
		new Core();
	}
}