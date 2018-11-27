public class GameBoard {

	public static String playerFree = " ";
	public static String playerX = "X";
	public static String playerO = "O";
	
        //This represents the possibilities of 1 to 8, 9 to 17, 18 to 36 on the dimensions of the GameBoard
	public String[] gameBoard = new String[36];

	public GameBoard() {
		//Initialize the game board to an empty state
		for( int i = 0; i < gameBoard.length; i++)
			gameBoard[i] = playerFree; 

	}
	private int mapFromLvlRowCol(int lvl, int row, int col) {
		int map = (lvl - 1) * 9;  // 1:0 to 8, 2: 9 to 17, 3: 18 to 36
		map = map + (row - 1) * 3;  
		map = map + (col - 1);
		return map;
	}
	
	private int levelFromMap(int map) {
		int level = java.lang.Math.round(map / 9);
		return level + 1;
	}
	
	private int rowFromMap(int map) {
		int subMap = map % 9;
		int row = java.lang.Math.round(subMap / 3);
		return row + 1;
	}
	
	private int colFromMap(int map) {
		int subMap = map % 9;
		subMap = subMap % 3;
		int col = java.lang.Math.round(subMap / 3);
		return col + 1;
	}

	//Returns Free, X, or O depending how the board space is occupied
	public String checkMove(int lvl, int row, int col) {
		//Checks if there are any valid or invalid board positions
		int map = mapFromLvlRowCol(lvl, row, col);
		return gameBoard[map];
	}
	
	public boolean checkMove(int moveLocation) {
		//Checks if there are any valid or invalid board positions
		if (gameBoard[moveLocation] == playerFree) {
			return true;
		}
		return false;
	}

	
	//Returns true/false depending if move is valid
	public boolean makeMove(String playerXO,int lvl, int row, int col) {
		//Checks to see if the user input is valid when they select a letter
		if (playerX != "X" || playerO != "O") //If not equal to correct String  
		{
	       return false; //Return as invalid
	    }
	   
		//Checks if Board position is occupied/taken
		if (checkMove(lvl, row, col)  != playerFree) {
			return false; //Returns false (invalid) if taken
		}
		int map = mapFromLvlRowCol(lvl, row, col);
		gameBoard[map] = playerXO;
		return true; //Returns as true (valid) if not
	
        }
	
	//Returns true/false depending if move is valid
	public boolean makeMove(String playerXO,int moveLocation) {
		//Checks to see if the user input is valid when they select a letter
		if (playerXO != playerX && playerXO != playerO) //If not equal to correct String  
		{
	       return false; //Return as invalid
	    }
	   
		//Checks if Board position is occupied/taken
		if (!checkMove(moveLocation)) {
			return false; //Returns false (invalid) if taken
		}
		
		gameBoard[moveLocation] = playerXO;
		return true; //Returns as true (valid) if not
	
        }

	
	public void RefreshBoard() {
		//Loop the already filled game board to an empty state
		for( int i = 0; i < gameBoard.length; i++)
			gameBoard[i] = " "; 
		
		
	}
}
