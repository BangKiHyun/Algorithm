package programmers;

import java.util.*;

import static java.lang.Long.parseLong;
import static java.lang.Math.*;

public class pg_67257_kakao {
    private static final int DEPTH = 3;

    private static final char[] initOperation = {'+', '-', '*'};
    private static char[] priorityOperation = new char[3];
    private static boolean[] visit = new boolean[3];
    private static List<Character> operation = new ArrayList<>();
    private static List<Long> operator = new ArrayList<>();
    private static long ans = 0;

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }

    public static long solution(String expression) {
        setOperatorAndOperation(expression);
        goDFS(0, 0);
        return ans;
    }

    private static void goDFS(int idx, int depth) {
        if (DEPTH == depth) {
            ans = max(abs(calculate()), ans);
        }

        for (int i = 0; i < DEPTH; i++) {
            if (!visit[i]) {
                visit[i] = true;
                priorityOperation[idx] = initOperation[i];
                goDFS(idx + 1, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static long calculate() {
        ArrayList<Character> copyOperation = new ArrayList<>(operation);
        ArrayList<Long> copyOperator = new ArrayList<>(operator);

        for (int i = 0; i < DEPTH; i++) {
            for (int operationIdx = 0; operationIdx < copyOperation.size(); operationIdx++) {
                if (priorityOperation[i] == copyOperation.get(operationIdx)) {
                    long calculateValue = calculate(copyOperator.remove(operationIdx),
                            copyOperator.remove(operationIdx), copyOperation.remove(operationIdx));
                    copyOperator.add(operationIdx, calculateValue);
                    operationIdx--;
                }
            }
        }

        return copyOperator.get(0);
    }

    private static long calculate(long firstOperator, long secondOperator, char operation) {
        switch (operation) {
            case '+':
                return firstOperator + secondOperator;
            case '-':
                return firstOperator - secondOperator;
            case '*':
                return firstOperator * secondOperator;
        }
        return -1;
    }

    private static void setOperatorAndOperation(String expression) {
        StringBuilder strNumber = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (isOperation(c)) {
                operation.add(c);
                operator.add(parseLong(strNumber.toString()));
                strNumber = new StringBuilder();
            } else {
                strNumber.append(c);
            }
        }
        operator.add(parseLong(strNumber.toString()));
    }

    private static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}
