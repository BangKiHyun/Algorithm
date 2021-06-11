package leetcode.problems.impl;

public class LC_CountAndSay {

    public static void main(String[] args) {
        int n = 3;
        final LC_CountAndSay solution = new LC_CountAndSay();
        System.out.println(solution.countAndSay(n));
    }

    public String countAndSay(int n) {
        if (n == 1) return String.valueOf(1);
        String number = "11";
        while (n-- > 2) {
            number = findNextNumber(number);
        }
        return number;
    }

    private String findNextNumber(String number) {
        StringBuilder sb = new StringBuilder();
        char preNum = '.';
        int duplicateCount = 1;
        for (char num : number.toCharArray()) {
            if (preNum == '.') {
                preNum = num;
                continue;
            }
            if (preNum == num) duplicateCount++;
            else {
                sb.append(duplicateCount).append(preNum);
                preNum = num;
                duplicateCount = 1;
            }
        }
        if (duplicateCount != 0) {
            sb.append(duplicateCount).append(preNum);
        }
        return sb.toString();
    }
}
