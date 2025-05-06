package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    private boolean xTurn = true;                   // flag to track whose turn: true for X's turn, false for O's turn
    private Button[][] board = new Button[3][3];    // 2D array to represent the tictactoe board

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // creates a 3x3 grid layout for the tictactoe board
        GridPane grid = new GridPane();
        grid.setHgap(5); // horizontal gap between cells
        grid.setVgap(5); // vertical gap between cells
        
        grid.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f9; -fx-boarder-color: #333; -fx-border-width: 2px; -fx-border-radius: 10; -fx-background-radius:10;"); // sets the style of the grid

        // initializes the buttons for each cell in the 3x3 grid
        for (int row = 0; row< 3; row ++){
            for (int col = 0; col<3; col++){
                Button cell = new Button();     // creates a new button for each cell
                cell.setPrefSize(100,100);      // sets the preferred size of the button
                cell.setFont(new Font(24));     // sets the font size of X/O

                cell.setStyle("-fx-background-color: #fff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #333;"); // sets the style of the button
                final int r = row;
                final int c = col;

                cell.setOnAction(e->handleMove(r,c)); // click event handler to process player moves

                board[row][col]= cell;      // stores the button in the board array
                grid.add(cell, col, row);   // adds the button to the grid at the specified row and column
            }
        }
        // creates a scene with the grid layout and sets the size of the window
        Scene scene = new Scene(grid,340,360);
        stage.setTitle("My TicTacToe Game");
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(int row, int col) {
        // checks if the cell is empty before allowing a move
        if (board[row][col].getText().isEmpty()){

            board[row][col].setText(xTurn ? "X" : "O");  // sets the text of the button to X or O based on whose turn it is

            // sets the style of the button based on whose turn it is
            if (xTurn){
                board[row][col].setStyle("-fx-background-color: #d1e7ff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #004085;");
            }else {
                board[row][col].setStyle("-fx-background-color: #ffdce0; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #721c24;");
            }

            // checks for a winner
            if (checkWinner()){
                showAlert((xTurn ? "X" : "O") + " wins!");  // declares the winner
                resetBoard();                               // resets the board after a win
            } 
            // checks if the board is full and no winner is found
            else if (isBoardFull()){
                showAlert("It's A Draw!!"); // declares a draw
            }
            // otherwise, switches the turn to the other player
            else {
                xTurn = !xTurn;
            }
        }

    }

    private boolean isBoardFull() {
        for (int row = 0; row<3; row ++){
            for (int col = 0; col<3; col++){
                // checks if any cell is empty --> if so, the board is not full
                if (board[col][row].getText().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row<3; row ++){
            for (int col = 0; col<3; col++){
                board[row][col].setText("");    // removes X/O from the button
                board[row][col].setStyle("-fx-background-color: #fff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #333;");     // resets the style of the button
            }
        }
        xTurn = true;
    }

    private void showAlert(String message) {
        // creates an alert dialog to show the winner or draw message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetBoard();   // resets the board after showing the alert
    }

    private boolean checkWinner() {
        // checks for a winning condition in rows, columns, and diagonals
        for (int i = 0; i<3; i++){
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
            checkLine(board[0][i],board[1][i],board[2][i])){
                return true;
            }
        }
         return  checkLine(board[0][0], board[1][1], board[2][2]) ||
            checkLine(board[0][2], board[1][1], board[2][0]);
        }

    private boolean checkLine(Button b1, Button b2, Button b3) {
        return  !b1.getText().isEmpty() && b1.getText().equals(b2.getText()) &&b2.getText().equals(b3.getText());   // checks if all three buttons have the same text (X or O) and are not empty
    }
}