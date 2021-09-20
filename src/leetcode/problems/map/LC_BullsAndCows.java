package leetcode.problems.map;

import java.util.HashMap;
import java.util.Map;

public class LC_BullsAndCows {

    public static void main(String[] args) {
        String secret = "11";
        String guess = "01";
        final LC_BullsAndCows task = new LC_BullsAndCows();
        System.out.println(task.getHint(secret, guess));
    }

    public String getHint(String secret, String guess) {
        Map<Character, Integer> numberAndCountMap = new HashMap<>();
        char[] secretNum = secret.toCharArray();
        char[] guessNum = guess.toCharArray();
        for (int idx = 0; idx < secretNum.length; idx++) {
            if (secretNum[idx] != guessNum[idx]) {
                if (!numberAndCountMap.containsKey(guessNum[idx])) {
                    numberAndCountMap.put(guessNum[idx], 1);
                } else numberAndCountMap.put(guessNum[idx], numberAndCountMap.get(guessNum[idx]) + 1);
            }
        }

        int correct = 0;
        int misedPlace = 0;
        for (int idx = 0; idx < secretNum.length; idx++) {
            if (secretNum[idx] == guessNum[idx]) {
                correct++;
            } else {
                if (numberAndCountMap.containsKey(secretNum[idx]) && numberAndCountMap.get(secretNum[idx]) != 0) {
                    misedPlace++;
                    numberAndCountMap.put(secretNum[idx], numberAndCountMap.get(secretNum[idx]) - 1);
                }
            }
        }
        return correct + "A" + misedPlace + "B";
    }
}
