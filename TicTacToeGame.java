import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	public static void printGameBoard(char[][] gameBoard) {
		
		for(char[] row: gameBoard) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePosition(char[][] gameBoard, int pos, String user) {
		char symbol=' ';
		
		if (user.equals("player")) {
			symbol='X';
			playerPositions.add(pos);
		}
		else {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
		case 1: gameBoard[0][0] = symbol;
				break;
		case 2: gameBoard[0][2] = symbol;
				break;
		case 3: gameBoard[0][4] = symbol;
		break;
		case 4: gameBoard[2][0] = symbol;
		break;
		case 5: gameBoard[2][2] = symbol;
		break;
		case 6: gameBoard[2][4] = symbol;
		break;
		case 7: gameBoard[4][0] = symbol;
		break;
		case 8: gameBoard[4][2] = symbol;
		break;
		case 9: gameBoard[4][4] = symbol;
		break;
		default:
		break;
		}
	}
	
	public static String checkWinner() { // change to boolean
		List topRow = Arrays.asList(1,2,3);
		List middleRow = Arrays.asList(4,5,6);
		List bottomRow = Arrays.asList(7,8,9);
		List topCol = Arrays.asList(1,4,7);
		List middleCol = Arrays.asList(2,5,8);
		List bottomCol = Arrays.asList(3,6,9);
		List leftDiagonal = Arrays.asList(1,5,9);
		List rightDiagonal = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(leftDiagonal);
		winning.add(middleCol);
		winning.add(middleRow);
		winning.add(bottomRow);
		winning.add(topCol);
		winning.add(bottomCol);
		winning.add(rightDiagonal);
		
		for (List eachList:winning) {
			if(playerPositions.containsAll(eachList)) {
				return "Player won!";
			}
			else if(cpuPositions.containsAll(eachList)) {
				return "cpu won!";
			}
			else if(playerPositions.size()+cpuPositions.size()==9) {
				return "Oops! Tie";
			}
		}
		return null;
	}

	public static void main(String args[]) {
		char[][] gameBoard = {	{' ','|',' ','|',' '},
				{'#','|','#','|','#'},
				{' ','|',' ','|',' '},
				{'#','|','#','|','#'},
				{' ','|',' ','|',' '}  };

		printGameBoard(gameBoard);
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
		System.out.println("Enter your position: 1-9");
		int playerPosition = sc.nextInt();
		while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
			System.out.println("Invalid position player, Select again");
			playerPosition = sc.nextInt();
		}
		placePosition(gameBoard, playerPosition, "player");
		
		printGameBoard(gameBoard);
		
		String winner = checkWinner();
		if(winner!=null) {
			System.out.println(winner);
			break;
		}
		
		Random rand = new Random();
		int cpuPos = rand.nextInt(9)+1; 
		while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
			cpuPos = rand.nextInt(9)+1; 
		}
		placePosition(gameBoard, cpuPos, "cpu");

		printGameBoard(gameBoard);
		
		winner = checkWinner();
		if(winner!=null) {
			System.out.println(winner);
			break;
		}		
		}
	}
}
