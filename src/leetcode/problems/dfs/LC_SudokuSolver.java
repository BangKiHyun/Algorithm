package leetcode.problems.dfs;

public class LC_SudokuSolver {
    private static final int MAX_SIZE = 9;
    private static final char EMPTY = '.';

    public void solveSudoku(char[][] board) {
        fillTheBoard(board, 0, 0);
    }

    private boolean fillTheBoard(char[][] board, int curRow, int curCol) {
        if (curCol == MAX_SIZE) {
            curCol = 0;
            curRow++;
        }
        if(curRow == MAX_SIZE) return true;

        if (board[curRow][curCol] == EMPTY) {
            for (int targetNumber = 1; targetNumber <= MAX_SIZE; targetNumber++) {
                if (isValidPlacement(curRow, curCol, targetNumber, board)) {
                    board[curRow][curCol] = (char) (targetNumber + '0');
                    if (fillTheBoard(board, curRow, curCol + 1)) return true;
                    board[curRow][curCol] = EMPTY;
                }
            }
        } else {
            return fillTheBoard(board, curRow, curCol + 1);
        }
        return false;
    }

    private boolean isValidPlacement(int row, int col, int target, char[][] board) {
        return isValidRow(row, target, board) && isValidCol(col, target, board) && isValidSquare(row, col, target, board);
    }

    private boolean isValidRow(int row, int target, char[][] board) {
        for (int colIdx = 0; colIdx < MAX_SIZE; colIdx++) {
            if (board[row][colIdx] - '0' == target) return false;
        }
        return true;
    }

    private boolean isValidCol(int col, int target, char[][] board) {
        for (int rowIdx = 0; rowIdx < MAX_SIZE; rowIdx++) {
            if (board[rowIdx][col] - '0' == target) return false;
        }
        return true;
    }

    private boolean isValidSquare(int row, int col, int target, char[][] board) {
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int rowIdx = row; rowIdx < row + 3; rowIdx++) {
            for (int colIdx = col; colIdx < col + 3; colIdx++) {
                if (board[rowIdx][colIdx] - '0' == target) return false;
            }
        }
        return true;
    }
}