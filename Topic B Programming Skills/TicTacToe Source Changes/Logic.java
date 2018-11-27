public class Logic {

	private GameBoard gm = new GameBoard();
	
	public String checkWinner() {
		
		String line = null;
		
		for (int a = 0; a < 48; a++) {
			switch (a) {

			// First Layer //
			case 0:
				line = gm.gameBoard[0] + gm.gameBoard[1] + gm.gameBoard[2];
				break;
			case 1:
				line = gm.gameBoard[3] + gm.gameBoard[4] + gm.gameBoard[5];
				break;
			case 2:
				line = gm.gameBoard[6] + gm.gameBoard[7] + gm.gameBoard[8];
				break;
			case 3:
				line = gm.gameBoard[0] + gm.gameBoard[3] + gm.gameBoard[6];
				break;
			case 4:
				line = gm.gameBoard[1] + gm.gameBoard[4] + gm.gameBoard[7];
				break;
			case 5:
				line = gm.gameBoard[2] + gm.gameBoard[5] + gm.gameBoard[8];
				// First Layer Diagonals //
				break;
			case 6:
				line = gm.gameBoard[0] + gm.gameBoard[4] + gm.gameBoard[8];
				break;
			case 7:
				line = gm.gameBoard[2] + gm.gameBoard[4] + gm.gameBoard[6];
				break;
			// Second Layer //
			case 8:
				line = gm.gameBoard[9] + gm.gameBoard[10] + gm.gameBoard[11];
				break;
			case 9:
				line = gm.gameBoard[12] + gm.gameBoard[13] + gm.gameBoard[14];
				break;
			case 10:
				line = gm.gameBoard[15] + gm.gameBoard[16] + gm.gameBoard[17];
				break;
			case 11:
				line = gm.gameBoard[9] + gm.gameBoard[12] + gm.gameBoard[15];
				break;
			case 12:
				line = gm.gameBoard[10] + gm.gameBoard[13] + gm.gameBoard[16];
				break;
			case 13:
				line = gm.gameBoard[11] + gm.gameBoard[14] + gm.gameBoard[17];
				break;
			// Second Layer Diagonals //
			case 14:
				line = gm.gameBoard[9] + gm.gameBoard[13] + gm.gameBoard[17];
				break;
			case 15:
				line = gm.gameBoard[11] + gm.gameBoard[13] + gm.gameBoard[15];
				break;
			// Third layer //
			case 16:
				line = gm.gameBoard[18] + gm.gameBoard[19] + gm.gameBoard[20];
				break;
			case 17:
				line = gm.gameBoard[21] + gm.gameBoard[22] + gm.gameBoard[23];
				break;
			case 18:
				line = gm.gameBoard[24] + gm.gameBoard[25] + gm.gameBoard[26];
				break;
			case 19:
				line = gm.gameBoard[18] + gm.gameBoard[21] + gm.gameBoard[24];
				break;
			case 20:
				line = gm.gameBoard[19] + gm.gameBoard[22] + gm.gameBoard[25];
				break;
			case 21:
				line = gm.gameBoard[20] + gm.gameBoard[23] + gm.gameBoard[26];
				break;
			// Third Layer Diagonals //
			case 22:
				line = gm.gameBoard[18] + gm.gameBoard[22] + gm.gameBoard[26];
				break;
			case 23:
				line = gm.gameBoard[20] + gm.gameBoard[22] + gm.gameBoard[24];
				break;
			// 3D //
			case 24:
				line = gm.gameBoard[0] + gm.gameBoard[9] + gm.gameBoard[18];
				break;
			case 25:
				line = gm.gameBoard[1] + gm.gameBoard[10] + gm.gameBoard[19];
				break;
			case 26:
				line = gm.gameBoard[2] + gm.gameBoard[11] + gm.gameBoard[20];
				break;
			case 27:
				line = gm.gameBoard[3] + gm.gameBoard[12] + gm.gameBoard[21];
				break;
			case 28:
				line = gm.gameBoard[4] + gm.gameBoard[13] + gm.gameBoard[22];
				break;
			case 29:
				line = gm.gameBoard[5] + gm.gameBoard[14] + gm.gameBoard[23];
				break;
			case 30:
				line = gm.gameBoard[6] + gm.gameBoard[15] + gm.gameBoard[24];
				break;
			case 31:
				line = gm.gameBoard[7] + gm.gameBoard[16] + gm.gameBoard[25];
				break;
			case 32:
				line = gm.gameBoard[8] + gm.gameBoard[17] + gm.gameBoard[26];
				break;
			case 33:
				line = gm.gameBoard[0] + gm.gameBoard[10] + gm.gameBoard[20];
				break;
			case 34:
				line = gm.gameBoard[3] + gm.gameBoard[13] + gm.gameBoard[23];
				break;
			case 35:
				line = gm.gameBoard[6] + gm.gameBoard[16] + gm.gameBoard[26];
				break;
			case 36:
				line = gm.gameBoard[20] + gm.gameBoard[10] + gm.gameBoard[0];
				break;
			case 37:
				line = gm.gameBoard[23] + gm.gameBoard[13] + gm.gameBoard[3];
				break;
			case 38:
				line = gm.gameBoard[26] + gm.gameBoard[16] + gm.gameBoard[6];
				break;
			case 39:
				line = gm.gameBoard[0] + gm.gameBoard[12] + gm.gameBoard[24];
				break;
			case 40:
				line = gm.gameBoard[1] + gm.gameBoard[13] + gm.gameBoard[25];
				break;
			case 41:
				line = gm.gameBoard[2] + gm.gameBoard[14] + gm.gameBoard[26];
				break;
			case 42:
				line = gm.gameBoard[24] + gm.gameBoard[12] + gm.gameBoard[0];
				break;
			case 43:
				line = gm.gameBoard[25] + gm.gameBoard[13] + gm.gameBoard[1];
				break;
			case 44:
				line = gm.gameBoard[26] + gm.gameBoard[14] + gm.gameBoard[2];
				break;
			case 45:
				line = gm.gameBoard[0] + gm.gameBoard[13] + gm.gameBoard[26];
				break;
			case 46:
				line = gm.gameBoard[26] + gm.gameBoard[13] + gm.gameBoard[0];
				break;
			case 47:
				line = gm.gameBoard[2] + gm.gameBoard[13] + gm.gameBoard[24];
				break;
			case 48:
				line = gm.gameBoard[24] + gm.gameBoard[13] + gm.gameBoard[2];
				break;
			}

			if (line.equalsIgnoreCase("XXX")) {
				return "XXX";
			} else if (line.equalsIgnoreCase("OOO")) {
				return "OOO";
			}
		}
		return "   ";
	}

	public boolean checkWinnerX() {
		if (checkWinner() == "XXX") {
			return true;
		}
		return false;
	}

	public boolean checkWinnerO() {
		if (checkWinner() == "OOO") {
			return true;
		}
		return false;
	}
}