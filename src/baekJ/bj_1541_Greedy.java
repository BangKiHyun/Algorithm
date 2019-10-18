package baekJ;

import java.util.Scanner;

public class bj_1541_Greedy {
    private static String arrNum[];
    private static String inputStr;
    private static String plusNum[];

    public static void main(String[] args) {
        init();
        splitByMinus();
        int min = getSumOfMin();
        System.out.println(min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        inputStr = sc.next();
    }

    private static void splitByMinus() {
        arrNum = inputStr.split("-");
    }

    private static int getSumOfMin() {
        int minResult = 0;

        for (int i = 0; i < arrNum.length; i++) {
            int calcNum = getSumOfNum(arrNum[i]);

            if (i == 0) calcNum *= -1;
            minResult -= calcNum;
        }
        return minResult;
    }

    private static int getSumOfNum(String num) {
        int sum = 0;
        splitByPlus(num);
        for (String i : plusNum) {
            sum += Integer.parseInt(i);
        }
        return sum;
    }

    private static void splitByPlus(String num) {
        plusNum = num.split("\\+");
    }
}
