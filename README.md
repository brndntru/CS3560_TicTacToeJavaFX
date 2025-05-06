# CS3560_TicTacToeJavaFX
This application is a Tic Tac Toe game built with JavaFX, allowing two players to play locally on the same machine. 
The program tracks players' turns and moves, handles win and draw conditions, and resets the board after each game.

## Features
- Multiplayer (two players: X and O)
- Game Board and Cells (3x3 Grid) designed and outputted with GUI (using JavaFX)
- Button Array to store and access each cell for player marks (X or O)
- Win and Draw detection
- Alerts to declare wins and draws
- Automatic Reset of board upon game completion

## Design
The program creates and styles a 3x3 Tic Tac Toe grid with interactive buttons that players click to mark either 'X' 
or 'O'. It checks the board for conditions including if there's a win or draw, if it's full, and if there's a line. 
It handles moves and shows a pop-up alert, declaring a winner or a draw, and resets the board automatically.

## How to Play
1. Player X always starts
2. Click on any empty cell in the 3x3 grid to mark X or O
3. Try to complete a line (3 identical marks horizontally, vertically, or diagonally) before the other player
4. The game will automatically check, detect, and declare wins and draws. It will then reset the game and board.
5. Repeat and play as many games as you want!
