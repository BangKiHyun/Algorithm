package baekJ;

import java.util.Scanner;

public class bj_16637_Greedy_DFS {
    final static int INF = Integer.MAX_VALUE;
    private static String input;
    private static String arrOperation[] = new String[INF];
    private static int arrNum[] = new int[INF];
    private static int operationIdx = 0, numIdx = 0;
    private static int min = INF;

    public static void main(String[] args) {
        init();
        seperateOperationAndNum();
        findSumOfMin(arrNum[0], 0);
        System.out.println(min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        input = sc.next();
    }

    private static void seperateOperationAndNum() {
        int startNumIdx = 0;
        operationIdx = 0;
        numIdx = 0;
        for (int i = 0; i < input.length(); i++) {
            String tmp = input.substring(i, i + 1);
            if (!isDistinguish(tmp)) {
                setNum(input, startNumIdx, i, numIdx);
                setOperation(tmp, operationIdx);
                numIdx++;
                operationIdx++;
                startNumIdx = i + 1;
            }
        }
        setNum(input, startNumIdx, input.length(), numIdx);
    }

    private static boolean isDistinguish(String tmp) {
        boolean res = true;
        switch (tmp) {
            case "-":
            case "+":
                return false;
            default:
                break;
        }
        return res;
    }

    private static void setOperation(String op, int opIdx) {
        arrOperation[opIdx] = op;
        return;
    }

    private static void setNum(String input, int startNumIdx, int endNumIdx, int numIdx) {
        String inputNum = input.substring(startNumIdx, endNumIdx);
        arrNum[numIdx] = Integer.parseInt(inputNum);
        return;
    }

    private static void findSumOfMin(int sum, int nowOperationIdx) {
        int nextResult = 0, nowResult = 0;

        if (nowOperationIdx >= operationIdx) {
            System.out.println(sum + " " + nowOperationIdx);
            min = Math.min(sum, min);
            return;
        }

        nowResult = calculateValue(sum, arrNum[nowOperationIdx + 1], arrOperation[nowOperationIdx]);
        findSumOfMin(nowResult, nowOperationIdx + 1);

        if (nowOperationIdx < numIdx - 1) {
            nextResult = calculateValue(arrNum[nowOperationIdx + 1], arrNum[nowOperationIdx + 2], arrOperation[nowOperationIdx + 1]);
            nowResult = calculateValue(sum, nextResult, arrOperation[nowOperationIdx]);
            System.out.println(nowResult + " " + nextResult);
            findSumOfMin(nowResult, nowOperationIdx + 2);
        }
    }

    private static int calculateValue(int sum, int num, String op) {
        switch (op) {
            case "+":
                sum += num;
                break;
            case "-":
                sum -= num;
                break;
            default:
                break;
        }
        return sum;
    }
}
