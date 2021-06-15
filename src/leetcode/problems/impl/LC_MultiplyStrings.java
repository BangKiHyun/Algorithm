package leetcode.problems.impl;


// Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
public class LC_MultiplyStrings {

    public static void main(String[] args) {
        final LC_MultiplyStrings problem = new LC_MultiplyStrings();
        String num1 = "9";
        String num2 = "99";
        System.out.println(problem.multiply(num1, num2));
    }

    // int형으로 바로 바꿔 계산하면 안되는 문제
    // 각각의 숫자들을 곱해서 자리에 맞춰 저장한다.
    // 저장할 때 숫자를 거꾸로 저장했다. 나중에 값을 StringBuilder로 값을 저장할 때 오른쪽에서부터 값이 채워지기 때문이다.
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.equals("1")) return num2;
        if (num2.equals("1")) return num1;

        int[] eachNumbers = new int[num1.length() + num2.length()];
        for (int firstIdx = num1.length() - 1; firstIdx >= 0; firstIdx--) {
            int eachNumberIdx = num2.length() + firstIdx;
            int carry = 0;
            for (int secondIdx = num2.length() - 1; secondIdx >= 0; secondIdx--) {
                int multiplyNumber = (num1.charAt(firstIdx) - '0') * (num2.charAt(secondIdx) - '0');
                multiplyNumber += carry;
                int prev = eachNumbers[eachNumberIdx];
                eachNumbers[eachNumberIdx] = (prev + multiplyNumber) % 10;
                carry = (prev + multiplyNumber) / 10;
                eachNumberIdx--;
            }
            eachNumbers[eachNumberIdx] = carry;
        }

        StringBuilder answerStringBuilder = new StringBuilder();
        int eachNumberIdx = 0;
        while (eachNumbers[eachNumberIdx] == 0) {
            eachNumberIdx++;
        }

        while (eachNumberIdx < num1.length() + num2.length()) {
            answerStringBuilder.append(eachNumbers[eachNumberIdx++]);
        }
        return String.valueOf(answerStringBuilder);
    }
}
