package programmers.queue;

import java.util.Stack;

public class pg_크레인_인형뽑기 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(solution(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            for (int[] curBoard : board) {
                int curNumber = curBoard[move - 1];
                if (curNumber != 0) {
                    if (stack.isEmpty()) {
                        stack.push(curNumber);
                    } else {
                        if (stack.peek() == curNumber) {
                            stack.pop();
                            ans += 2;
                        } else {
                            stack.push(curNumber);
                        }
                    }
                    curBoard[move - 1] = 0;
                    break;
                }
            }
        }
        return ans;
    }
}
