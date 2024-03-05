///Nageen Haider l217712@lhr.nu.edu.pk
//Sidra Noor l217706@lhr.nu.edu.pk
////Filza Fatima l217527@lhr.nu.edu.pk
//Laiba Mirza l217739@lhr.nu.edu.pk

#include<iostream>
#include<fstream>
#include<conio.h>
#include <chrono>
#include <thread>
#include <windows.h>
using namespace std;
void bulletUpdate(char arr[29][30], int xbullet[], int ybullet[], int counter, int x, int y) {
	for (int i = 0; i < counter; i++) {
		if (xbullet[i] != -1) {

			arr[xbullet[i]][ybullet[i]] = ' ';
			if (xbullet[i] == 1) {
				xbullet[i] = -1;
				ybullet[i] = -1;
			}
			else {
				xbullet[i] = xbullet[i] - 1;
			}
			if (xbullet[i] != -1) {
				arr[xbullet[i]][ybullet[i]] = '*';
			}
		}
	}
	arr[x][y] = 'I';
}
void Instructions()
{
	cout << "        INSTRUCTIONS            ";
	cout << endl;
	cout << endl;
	cout << "Press Enter to Fire Bullets";
	cout << endl;
	cout << "\nAvoid Hitting Space Debris";
	cout << endl;
	cout << "\nEsc Key During Game Restarts Level";
	cout << endl;
	cout << endl;
	cout << "  Press Space Key To Start Level 1";
	cout << endl;
	cout << "\n  Press Esc Key to Exit";
	cout << endl;


}

//---------------print 2d board---------------
void printBoard(char arr[29][30], int level, int score, int lives, int highScore) {

	cout << "LEVEL: " << level;
	cout << "\tSCORE: " << score;
	cout << "\tLIVES: " << lives;
	cout << "\tHIGHSCORE: " << highScore << endl;
	for (int i = 0; i <= 0; i++)
	{
		for (int j = 0; j < 29; j++)
		{
			cout << arr[i][j] << "_";
		}

	}

	for (int i = 0; i < 30; i++) {
		int j = 0;
		arr[i][j] = '|';
	}
	for (int i = 2; i < 30; i++) {
		int j = 29;
		arr[29][29] = '|';
		arr[i][j] = '|';
	}
	for (int i = 1; i < 29; i++) {
		for (int j = 0; j < 30; j++) {
			cout << arr[i][j] << " ";
		}

		cout << endl;
	}
	for (int i = 0; i <= 0; i++)
	{
		for (int j = 0; j < 29; j++)
		{
			cout << arr[i][j] << "_";
		}

	}
	cout << endl;

}

//---------------------initialize board----------
void initializeBoard(char arr[29][30]) {

	for (int i = 0; i < 29; i++) {
		for (int j = 0; j < 30; j++) {
			arr[i][j] = ' ';

		}

	}

}
void clearScreen()
{
	COORD cursorPosition;	cursorPosition.X = 0;	cursorPosition.Y = 0;	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), cursorPosition);
}

void levelUpdate(int& level, int score) {

	if (score == 10) {
		system("color F4");
		level = 2;

	}

	if (score == 30)
	{
		system("color 71");
		level = 3;

	}
	if (score == 60)
	{
		system("color B0");
		level = 4;
	}
	if (score == 100)
	{
		system("color 64");
		level = 5;
	}
	if (score == 150)
	{
		cout << "GAME WON!!";
	}
}


void level1(char arr[29][30], int xAlien[], int yAlien[], int& lives)
{
	for (int i = 0; i < 2; i++) {
		if (xAlien[i] != -2) {

			arr[xAlien[i]][yAlien[i]] = ' ';
			if (xAlien[i] == 28) {
				xAlien[i] = -2;
				yAlien[i] = -2;
				lives--;
			}
			else {
				xAlien[i] = xAlien[i] + 1;
			}
			if (xAlien[i] != -2) {
				arr[xAlien[i]][yAlien[i]] = '0';
			}

		}
	}

}



void level2(char arr[29][30], int xAlien[], int yAlien[], int& lives)
{

	for (int i = 2; i < 6; i++) {
		if (xAlien[i] != -2) {

			arr[xAlien[i]][yAlien[i]] = ' ';
			if (xAlien[i] == 27) {
				xAlien[i] = -2;
				yAlien[i] = -2;
				lives--;
			}
			else {
				xAlien[i] = xAlien[i] + 1;
			}
			if (xAlien[i] != -2) {
				arr[xAlien[i]][yAlien[i]] = '0';
			}

		}
	}
}

void level3(char arr[29][30], int xAlien[], int yAlien[], int& lives)
{

	for (int i = 6; i < 12; i++) {
		if (xAlien[i] != -2) {

			arr[xAlien[i]][yAlien[i]] = ' ';
			if (xAlien[i] == 27) {
				xAlien[i] = -2;
				yAlien[i] = -2;
				lives--;
			}
			else {
				xAlien[i] = xAlien[i] + 1;
			}
			if (xAlien[i] != -2) {
				arr[xAlien[i]][yAlien[i]] = '0';
			}

		}
	}




}

void level4(char arr[29][30], int xAlien[], int yAlien[], int& lives)
{

	for (int i = 12; i < 20; i++) {
		if (xAlien[i] != -2) {

			arr[xAlien[i]][yAlien[i]] = ' ';
			if (xAlien[i] == 27) {
				xAlien[i] = -2;
				yAlien[i] = -2;
				lives--;
			}
			else {
				xAlien[i] = xAlien[i] + 1;
			}
			if (xAlien[i] != -2) {
				arr[xAlien[i]][yAlien[i]] = '0';
			}

		}
	}




}
void level5(char arr[29][30], int xAlien[], int yAlien[], int& lives)
{

	for (int i = 20; i < 30; i++) {
		if (xAlien[i] != -2) {

			arr[xAlien[i]][yAlien[i]] = ' ';
			if (xAlien[i] == 27) {
				xAlien[i] = -2;
				yAlien[i] = -2;
				lives--;
			}
			else {
				xAlien[i] = xAlien[i] + 1;
			}
			if (xAlien[i] != -2) {
				arr[xAlien[i]][yAlien[i]] = '0';
			}

		}
	}




}

int getStartIndex(int level) {
	if (level == 1) {
		return 0;
	}
	else if (level == 2) {
		return 1;
	}
	else if (level == 3) {
		return 6;
	}
	else if (level == 4) {
		return 12;
	}
	else if (level == 5) {
		return 20;
	}
}

int getEndIndex(int level) {
	if (level == 1) {
		return 1;
	}
	else if (level == 2) {
		return 5;
	}
	else if (level == 3) {
		return 11;
	}
	else if (level == 4) {
		return 19;
	}
	else if (level == 5) {
		return 29;
	}
}

void collisionCheck(char arr[29][30], int xAlien[], int yAlien[], int xbullet[], int ybullet[], int& score, int bulletCounter, int level) {
	bool okay = true;
	int n1, n2;
	n1 = getStartIndex(level);
	n2 = getEndIndex(level);
	for (int i = 0; i < bulletCounter; i++) {
		if (xbullet[i] != -1) {
			for (int j = n1; j <= n2 && okay; j++) {

				if (xAlien[j] != -2) {
					if (xbullet[i] == xAlien[j]) {
						if (ybullet[i] == yAlien[j]) {
							score = score + 5;

							arr[xAlien[j]][yAlien[j]] = ' ';
							xbullet[i] = -1;
							ybullet[i] = -1;

							xAlien[j] = -2;
							yAlien[j] = -2;

							okay = false;

						}
					}
				}

			}

		}

		okay = true;


	}

}

void initAliens(int xAlien[], int yAlien[]) {
	//For Level 1 ---- indexes 0 till 1 --- 2 aliens
	int ran;
	xAlien[0] = 1; yAlien[0] = 10;
	xAlien[1] = 1; yAlien[1] = 20;

	//For Level 2 ---- indexes 2 till 5 --- 4 aliens
	xAlien[2] = 1; yAlien[2] = 6;
	xAlien[3] = 1; yAlien[3] = 12;
	xAlien[4] = 1; yAlien[4] = 18;
	xAlien[5] = 1; yAlien[5] = 24;

	//for level 3 -----indexes 
	xAlien[6] = 1; yAlien[6] = 4;
	xAlien[7] = 1; yAlien[7] = 8;
	xAlien[8] = 1; yAlien[8] = 13;
	xAlien[9] = 1; yAlien[9] = 17;
	xAlien[10] = 1; yAlien[10] = 21;
	xAlien[11] = 1; yAlien[11] = 25;

	//for level 4 
	xAlien[12] = 1; yAlien[12] = 3;
	xAlien[13] = 1; yAlien[13] = 6;
	xAlien[14] = 1; yAlien[14] = 9;
	xAlien[15] = 1; yAlien[15] = 11;
	xAlien[16] = 1; yAlien[16] = 14;
	xAlien[17] = 1; yAlien[17] = 18;
	xAlien[18] = 1; yAlien[18] = 22;
	xAlien[19] = 1; yAlien[19] = 24;

	//for level 5=
	xAlien[20] = 1; yAlien[20] = 2;
	xAlien[21] = 1; yAlien[21] = 4;
	xAlien[22] = 1; yAlien[22] = 6;
	xAlien[23] = 1; yAlien[23] = 8;
	xAlien[24] = 1; yAlien[24] = 10;
	xAlien[25] = 1; yAlien[25] = 13;
	xAlien[26] = 1; yAlien[26] = 16;
	xAlien[27] = 1; yAlien[27] = 19;
	xAlien[28] = 1; yAlien[28] = 23;
	xAlien[29] = 1; yAlien[29] = 27;

}




int main() {
	system("color 4F");
	cout << "!----------------------------SPACE SHOOTER---------------------------------!";
	cout << endl;
	cout << endl;
	cout << "Created By:Nageen Haider\n\t   Sidra Noor\n\t   Filza Fatima\n\t   Laiba Mirza\n\n";
	std::this_thread::sleep_for(std::chrono::seconds(3)); // sleep for 1 second
	system("CLS");
	system("color DF");
	Instructions();
	int a;
	bool levelUpdateFlag = false;
	a = _getch();
	if (a == 32) {
		system("CLS");
		Beep(1213, 200);
		Beep(1213, 200);
		Beep(3890, 200);
		Beep(4390, 200);
		Beep(1223, 1000);

		char arr[29][30];
		int i = 0, j = 0, s = 20;
		char q;
		int level = 1, score = 0, lives = 3, k = 1;
		int highScore = 0;
		char fileScore[10];
		ifstream in;
		ofstream out;
		in.open("highscore.txt");
		in >> fileScore;
		highScore = atoi(fileScore);
		initializeBoard(arr);
		arr[28][15] = 'I';
		arr[i = 28][j = 15];
		printBoard(arr, level, score, lives, highScore);
		bool flag = true;

		//////
		int xbullet[100];
		int ybullet[100];
		int bulletCounter = 0;
		int speed = 0;
		/////

		int xAlien[30];
		int yAlien[30];

		for (int i = 0; i < 30; i < i++) {
			xAlien[i] = -2;
			yAlien[i] = -2;
		}

		initAliens(xAlien, yAlien);

		//system("pause");

		while (flag == true) {


			clearScreen();


			if (_kbhit()) {
				q = _getch();
				if (q == 'a' || q == 'A') {

					if (j == 1) {
						swap(arr[i][j], arr[i][j]);
					}
					else {
						swap(arr[i][j - 1], arr[i][j]);
						j = j - 1;
					}
				}
				if (q == 'd' || q == 'D')
				{
					if (j == 29) {
						swap(arr[i][j], arr[i][j]);
					}
					else {
						swap(arr[i][j + 1], arr[i][j]);
						j = j + 1;
					}
				}
				if (q == 13) {
					Beep(1234, 200);
					xbullet[bulletCounter] = i;
					ybullet[bulletCounter] = j;
					bulletCounter++;
					swap(arr[i][j], arr[i][j]);
				}
				if (q == 'q' || q == 27) {
					flag = false;
				}
			}

			if (speed > 5) {



				collisionCheck(arr, xAlien, yAlien, xbullet, ybullet, score, bulletCounter, level);
				bulletUpdate(arr, xbullet, ybullet, bulletCounter, i, j);
				collisionCheck(arr, xAlien, yAlien, xbullet, ybullet, score, bulletCounter, level);

				if (level == 1) {

					level1(arr, xAlien, yAlien, lives);

				}
				if (level == 2) {

					level2(arr, xAlien, yAlien, lives);
				}
				if (level == 3)
				{
					level3(arr, xAlien, yAlien, lives);
				}
				if (level == 4) {
					level4(arr, xAlien, yAlien, lives);
				}

				if (level == 5) {
					level5(arr, xAlien, yAlien, lives);
				}

				speed = 0;
				k++;
			}
			speed++;
			printBoard(arr, level, score, lives, highScore);
			levelUpdate(level, score);
			//std::this_thread::sleep_for(std::chrono::milliseconds(150)); // sleep for 1 second
			if (lives <= 0) {
				flag = false;
			}
		}
		if (highScore < score) {
			out.open("highscore.txt");
			out << score;
		}
		cout << "GAME OVER!!" << endl;
		for (int i = 0; i < 10; i < i++) {
			cout << xAlien[i] << endl;
		}

		cout << "level " << level << endl;

		for (int i = 0; i < 10; i < i++) {
			cout << xbullet[i] << endl;
		}
	}
	return 0;
}