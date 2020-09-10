package programmers;

import java.util.HashSet;
import java.util.Set;

public class pg_12981_구현 {
    public static void main(String[] args) {

        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};

        for (int ans : solution(n, words)) {
            System.out.print(ans + " ");
        }
    }

    private static int[] solution(int n, String[] words) {
        Set<String> usedWord = new HashSet<>();
        usedWord.add(words[0]);
        String startText = words[0].substring(words[0].length() - 1);

        int cnt = 0;
        for (String word : words) {
            cnt++;
            if (cnt == 1) continue;

            if (!usedWord.contains(word) && startText.equals(word.substring(0, 1))) {
                usedWord.add(word);
                startText = word.substring(word.length() - 1);
            } else {
                return new int[]{getNumber(n, cnt), getTurnCount(n, cnt)};
            }

        }

        return new int[]{0, 0};
    }

    private static int getNumber(final int n, final int cnt) {
        if (cnt % n == 0) {
            return n;
        }
        return cnt % n;
    }

    private static int getTurnCount(final int n, final int cnt) {
        if (cnt % n == 0) {
            return cnt / n;
        }
        return cnt / n + 1;
    }
}
