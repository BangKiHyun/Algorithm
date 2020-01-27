package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class bj_1342_백트 {
    private static int ans = 0;
    private static Set<String> set = new HashSet<>();

    //민식이와 준영이는 자기 방에서 문자열을 공부하고 있다. 민식이가 말하길 인접해 있는 모든 문자가 같지 않은 문자열을 행운의 문자열이라고 한다고 한다. 준영이는 문자열 S를 분석하기 시작했다. 준영이는 문자열 S에 나오는 문자를 재배치하면 서로 다른 행운의 문자열이 몇 개 나오는지 궁금해졌다. 만약 원래 문자열 S도 행운의 문자열이라면 그것도 개수에 포함한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 문자열 S가 주어진다. 길이는 최대 10이다.
        String text = br.readLine();
        boolean[] visit = new boolean[text.length()];

        backtracking(text, visit, 0, "");

        System.out.println(ans);
    }

    private static void backtracking(String text, boolean[] visit, int depth, String str) {
        if (depth == text.length()) {
            if (!isSame(str) && !set.contains(str)) {
                set.add(str);
                ans++;
            }
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                backtracking(text, visit, depth + 1, str + text.charAt(i));
                visit[i] = false;
            }
        }
    }

    private static boolean isSame(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == text.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }
}
