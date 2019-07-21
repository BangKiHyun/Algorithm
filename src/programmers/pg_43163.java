package programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class pg_43163 {

    static boolean[] v;
    public static int dfs(String begin, String target, String[] words) {
        int answer = 0;
        String dupBegin;

        for(int i = 0; i < words.length; i++) {
            if(v[i]) continue;

            for(int j = 0; j < begin.length(); j++) {
                dupBegin = begin;
                dupBegin = (j > 0 ? begin.substring(0, j) : "")
                        + words[i].charAt(j) + (j < begin.length()-1 ? begin.substring(j+1) : "");

                if(dupBegin.equals(target))
                    return 1;
                if(dupBegin.equals(words[i])) {
                    v[i] = true;
                    answer = 1;
                    answer += dfs(dupBegin, target, words);
                }
            }
        }
        return answer;
    }

    public static int solution(String begin, String target, String[] words) {
        v = new boolean[words.length + 1];
        for(int i = 0; i < words.length; i++){
            if(target.equals(words[i]))
                return dfs(begin, target, words);
        }
        return 0;
    }
/*
    static int MAX = 51;
    static boolean c[] = new boolean[MAX];

    static public int solution(String begin, String target, String[] words) {
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) return cnt;
        }
        cnt = dfs(begin, target, words, cnt);
        return cnt;
    }

    static int dfs(String begin, String target, String[] words, int ans) {
        int cnt = 0;
        String word;

        for (int i = 0; i < words.length; i++) {
            if (target.equals(begin)) {
                return ans;
            }
            if (c[i]) continue;
            for (int j = 0; j < begin.length(); j++) {
                word = begin;
                if (words[i].charAt(j) != word.charAt(j)) {
                    cnt++;
                }
                if (j == begin.length() - 1 && cnt == 1) {
                    c[i] = true;
                    ans++;
                    dfs(word, target, words, ans);
                }
            }
        }
        return ans;
    }
*/

    public static void main(String[] args) {
        String b = "hit";
        String t = "cog";
        String words[] = {"hot", "dot", "dog", "lot", "log", "cog"};
        int ans = solution(b, t, words);
        System.out.println(ans);
    }
}
