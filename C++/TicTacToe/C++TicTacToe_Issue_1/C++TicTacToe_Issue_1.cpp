
#include <iostream>
using namespace std;

char square[10] = {'0','1','2','3','4','5','6','7','8','9'};
int score1 = 0, score2 = 0;
int	checkwin();
void board();
void showFinalResult();

int main() {
	int player = 1, i = -1;
	char mark, choice;
	while (i == -1) {
		do {
			board();
			player = (player % 2) ? 1 : 2;
			cout << "Player " << player << ", enter a number:  ";
			cin >> choice;
			mark = (player == 1) ? 'X' : 'O';
			if (choice == '1' && square[1] == '1') {
				square[1] = mark;
			}
			else if (choice == '2' && square[2] == '2') {
				square[2] = mark;
			}
			else if (choice == '3' && square[3] == '3') {
				square[3] = mark;
			}
			else if (choice == '4' && square[4] == '4') {
				square[4] = mark;
			}
			else if (choice == '5' && square[5] == '5') {
				square[5] = mark;
			}
			else if (choice == '6' && square[6] == '6') {
				square[6] = mark;
			}
			else if (choice == '7' && square[7] == '7') {
				square[7] = mark;
			}
			else if (choice == '8' && square[8] == '8') {
				square[8] = mark;
			}
			else if (choice == '9' && square[9] == '9') {
				square[9] = mark;
			}
			else {
				if (choice == 'X' || choice == 'x') {
					showFinalResult();
					exit(1);
				}
				cout << "Invalid move! ";
				player--;
				cin.ignore();
				cin.get();
			}
			i = checkwin();
			player++;
		} while (i == -1);
		if (i == 1) {
			int tmp_player = player;
			if (--tmp_player == 2) {
				score2++;
			}
			else {
				score1++;
			}
		}
		board();
		char loop;
		cout << "Another round? [y/n] ";
		cin >> loop;
		if (loop == 'y' || loop == 'Y') {
			i = -1;
			player = 1;
			for (int j = 0; j < 10; j++) {
				char square2[10] = { 'o','1','2','3','4','5','6','7','8','9' };
				square[j] = square2[j];
			}
			board();
		}
	}
	showFinalResult();

	return 0;
}

/***************************************
		FUNCTION TO SHOW THE 
		RESULT OF THE GAME
****************************************/

void showFinalResult() {
	board();
	if (score1 > score2) {
		cout << "==>\aPlayer 1 Win! ";
	}
	else if (score1 < score2) {
		cout << "==>\aPlayer 2 Win! ";
	}
	else {
		cout << "==>\aIt's a Draw!";
	}
	cout << endl;
}

/*********************************************
	FUNCTION TO RETURN THE GAME STATUS
	RETURN 1 FOR GAME IS OVER WITH RESULT
	RETURN -1 FOR GAME IS STILL IN PROGRESS
	RETURN O GAME IS OVER AND NO RESULT
**********************************************/

int checkwin(){
	if (square[1] == square[2] && square[2] == square[3]) {
		return 1;
	}
	else if (square[4] == square[5] && square[5] == square[6]) {
		return 1;
	}
	else if (square[7] == square[8] && square[8] == square[9]) {
		return 1;
	}
	else if (square[1] == square[4] && square[4] == square[7]) {
		return 1;
	}
	else if (square[2] == square[5] && square[5] == square[8]) {
		return 1;
	}
	else if (square[3] == square[6] && square[6] == square[9]) {
		return 1;
	}
	else if (square[1] == square[5] && square[5] == square[9]) {
		return 1;
	}
	else if (square[3] == square[5] && square[5] == square[7]) {
		return 1;
	}
	else if (square[1] != '1' && square[2] != '2' && square[3] != '3'
			&& square[4] != '4' && square[5] != '5' && square[6] != '6'
			&& square[7] != '7' && square[8] != '8' && square[9] != '9') {
		return 0;
	}
	else {
		return -1;
	}
}

/*******************************************************************
	 FUNCTION TO DRAW BOARD OF TIC TAC TOE WITH PLAYERS MARK
********************************************************************/

void board(){
	system("cls");
	cout << "\n\n\tTic Tac Toe\n\n";
	cout << "Player 1 (X)  -  Player 2 (O)" << endl << endl;
	cout << endl;
	cout << "     |     |     " << endl;
	cout << "  " << square[1] << "  |  " << square[2] << "  |  " << square[3] << endl;
	cout << "_____|_____|_____" << endl;
	cout << "     |     |     " << endl;
	cout << "  " << square[4] << "  |  " << square[5] << "  |  " << square[6] << endl;
	cout << "_____|_____|_____" << endl;
	cout << "     |     |     " << endl;
	cout << "  " << square[7] << "  |  " << square[8] << "  |  " << square[9] << endl;
	cout << "     |     |     " << endl << endl;
	cout << "Player 1 score: " << score1 << ", Player 2 score: " << score2 << endl;
}