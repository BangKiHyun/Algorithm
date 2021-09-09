package leetcode.problems.stack;

import java.util.Stack;

public class LC_DailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        final LC_DailyTemperatures task = new LC_DailyTemperatures();
        for (int answer : task.dailyTemperatures(temperatures)) {
            System.out.print(answer + " ");
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                answer[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }
}
