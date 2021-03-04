package programmers.queue;

import java.util.Stack;

public class pg_3진법_뒤집기 {
    private static final int CALCULATED_VALUE = 3;

    public static void main(String[] args) {
        int n = 45;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        Stack<Integer> stack = new Stack<>();
        while (n != 0) {
            int remain = n % CALCULATED_VALUE;
            stack.push(remain);
            n /= CALCULATED_VALUE;
        }

        int numberOfMultiply = 0;
        int totalValue = 0;
        while (!stack.isEmpty()) {
            int number = stack.pop();
            totalValue += getDecimalNumber(number, numberOfMultiply++);
        }
        return totalValue;
    }

    private static int getDecimalNumber(int number, int numberOfMultiply) {
        int totalNumber = 1;
        while (numberOfMultiply != 0) {
            totalNumber *= CALCULATED_VALUE;
            numberOfMultiply--;
        }
        return totalNumber * number;
    }
}
