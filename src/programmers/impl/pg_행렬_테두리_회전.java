package programmers.impl;

public class pg_행렬_테두리_회전 {

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}};

        for (int ans : solution(rows, columns, queries)) {
            System.out.println(ans);
        }
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        final Board board = new Board(rows, columns);
        int[] answer = new int[queries.length];
        int answerIdx = 0;
        for (int[] query : queries) {
            board.rotate(query[0], query[1], query[2], query[3]);
            answer[answerIdx++] = board.getMinNumber();
            board.initMinNumber();
        }
        return answer;
    }

    private static class Board {
        private final int[][] board;
        private int minNumber;

        public Board(int rowSize, int colSize) {
            this.board = create(rowSize, colSize);
            minNumber = Integer.MAX_VALUE;
        }

        private int[][] create(int rowSize, int colSize) {
            int[][] board = new int[rowSize + 1][colSize + 1];
            int num = 1;
            for (int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
                for (int colIdx = 1; colIdx <= colSize; colIdx++) {
                    board[rowIdx][colIdx] = num++;
                }
            }
            return board;
        }

        public void rotate(int startRow, int startCol, int endRow, int endCol) {
            int rightPreNum = board[startRow + 1][startCol];
            int downPreNum = board[startRow][endCol];
            int leftPreNum = board[endRow - 1][endCol];
            int upPreNum = board[endRow][startCol];
            rotateRight(startRow, startCol, endCol, rightPreNum);
            rotateDown(startRow, endCol, endRow, downPreNum);
            rotateLeft(endRow, endCol, startCol, leftPreNum);
            rotateUp(endRow, startCol, startRow, upPreNum);
        }

        private void rotateRight(int startRow, int startCol, int endCol, int preNumber) {
            int swapNumber;
            for (int colIdx = startCol; colIdx <= endCol; colIdx++) {
                minNumber = Math.min(minNumber, preNumber);
                swapNumber = board[startRow][colIdx];
                board[startRow][colIdx] = preNumber;
                preNumber = swapNumber;
            }
        }

        private void rotateLeft(int startRow, int startCol, int endCol, int preNumber) {
            int swapNumber;
            for (int colIdx = startCol; colIdx >= endCol; colIdx--) {
                minNumber = Math.min(minNumber, preNumber);
                swapNumber = board[startRow][colIdx];
                board[startRow][colIdx] = preNumber;
                preNumber = swapNumber;
            }
        }

        private void rotateUp(int startRow, int startCol, int endRow, int preNumber) {
            int swapNumber;
            for (int rowIdx = startRow - 1; rowIdx > endRow; rowIdx--) {
                minNumber = Math.min(minNumber, preNumber);
                swapNumber = board[rowIdx][startCol];
                board[rowIdx][startCol] = preNumber;
                preNumber = swapNumber;
            }
        }

        private void rotateDown(int startRow, int startCol, int endRow, int preNumber) {
            int swapNumber;
            for (int rowIdx = startRow + 1; rowIdx < endRow; rowIdx++) {
                minNumber = Math.min(minNumber, preNumber);
                swapNumber = board[rowIdx][startCol];
                board[rowIdx][startCol] = preNumber;
                preNumber = swapNumber;
            }
        }

        public int getMinNumber() {
            return this.minNumber;
        }

        public void initMinNumber() {
            this.minNumber = Integer.MAX_VALUE;
        }
    }
}
