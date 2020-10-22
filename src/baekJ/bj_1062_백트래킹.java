package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1062_백트래킹 {
    private static final String START_WORD = "anta";
    private static final String END_WORD = "tica";
    private static final String EMPTY = "";

    private static final int ALPHABET_SIZE = 26;

    private static int n, k;
    private static boolean[] visit;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (isNotEnoughToTeach(k)) {
            System.out.println(0);
            return;
        } else if (isEnoughToTeachAll(k)) {
            System.out.println(n);
            return;
        }

        visit = new boolean[ALPHABET_SIZE];
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            String text = br.readLine();
            text = text.replaceAll(START_WORD, EMPTY);
            words[i] = text.replaceAll(END_WORD, EMPTY);
        }

        visit['a' - 97] = true;
        visit['n' - 97] = true;
        visit['t' - 97] = true;
        visit['i' - 97] = true;
        visit['c' - 97] = true;

        teachAlphabet(0, 5, words);
        System.out.println(ans);
    }

    private static boolean isNotEnoughToTeach(int k) {
        return k < 5;
    }

    private static boolean isEnoughToTeachAll(int k) {
        return k == ALPHABET_SIZE;
    }

    private static void teachAlphabet(int alphaIdx, int depth, String[] words) {
        if (depth == k) {
            int ansCount = 0;

            for (int i = 0; i < n; i++) {
                boolean flag = true;

                for (int j = 0; j < words[i].length(); j++) {
                    char word = words[i].charAt(j);
                    if (!visit[word - 97]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    ansCount++;
                }
            }
            ans = Math.max(ans, ansCount);
            return;
        }

        for (int i = alphaIdx; i < ALPHABET_SIZE; i++) {
            if (!visit[i]) {
                visit[i] = true;
                teachAlphabet(i + 1, depth + 1, words);
                visit[i] = false;
            }
        }
    }
}
