package programmers;

import java.util.ArrayList;
import java.util.List;

public class pg_17682_kakao {
    private static final String DOUBLE = "D";
    private static final String TRIPLE = "T";

    private static final String MUL = "*";
    private static final String MINUS = "#";

    public static void main(String[] args) {
        String input = "1D2S#10S";
        System.out.println(solution(input));
    }

    public static int solution(String dartResult) {
        List<Integer> operands = new ArrayList<>();
        for (int idx = 0; idx < dartResult.length(); idx++) {
            if (isOperands(dartResult.charAt(idx))) {
                int endIdx = getEndIdx(dartResult, idx);
                int operand = Integer.parseInt(dartResult.substring(idx, endIdx));
                operand = operate(operand, dartResult.substring(endIdx, endIdx + 1));
                operands.add(operand);
                idx = endIdx - 1;
            } else {
                operateOption(operands, dartResult.substring(idx, idx + 1));
            }
        }

        int answer = 0;
        for (int operand : operands) {
            answer += operand;
        }
        return answer;
    }

    private static boolean isOperands(char maybeOperand) {
        return maybeOperand >= 48 && maybeOperand <= 58;
    }

    private static int getEndIdx(String result, int start) {
        int endIdx = 0;
        for (int idx = start; idx < result.length(); idx++) {
            if (!isOperands(result.charAt(idx))) {
                endIdx = idx;
                break;
            }
        }
        return endIdx;
    }

    private static int operate(int operand, String operation) {
        switch (operation) {
            case DOUBLE:
                operand *= operand;
                break;
            case TRIPLE:
                operand = operand * operand * operand;
                break;
            default:
                break;
        }
        return operand;
    }

    private static void operateOption(List<Integer> operands, String option) {
        switch (option) {
            case MUL:
                operateMultiple(operands);
                break;
            case MINUS:
                operateMinus(operands);
                break;
            default:
                break;
        }
    }

    private static void operateMultiple(List<Integer> operands) {
        int lastIdx = operands.size() - 1;
        int operand = operands.get(lastIdx);
        operands.set(lastIdx, operand * 2);

        if (lastIdx == 0) return;
        operand = operands.get(lastIdx - 1);
        operands.set(lastIdx - 1, operand * 2);
    }

    private static void operateMinus(List<Integer> operands) {
        int lastIdx = operands.size() - 1;
        int operand = operands.get(lastIdx);
        operands.set(lastIdx, operand * -1);
    }
}
