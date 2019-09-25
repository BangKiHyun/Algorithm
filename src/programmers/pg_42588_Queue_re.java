package programmers;

import java.util.Stack;

public class pg_42588_Queue_re {
    public static void main(String[] args) {
        int[] h = //{6, 9, 5, 7, 4};
        //{3,9,9,3,5,7,2};
        {1,5,3,6,7,6,5};
        int ans[] = solution(h);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int length = heights.length;
        int[] ans = new int[length];

        for (int top : heights) {
            stack.push(top);
        }

        while (!stack.isEmpty()) {
            int idx = length - 1;
            int top = stack.pop();
            for (int i = length - 1; i >= 0; i--) {
                if (heights[i] > top) {
                    ans[idx] = i + 1;
                    break;
                }
            }
            length--;
        }
        return ans;
    }
}
