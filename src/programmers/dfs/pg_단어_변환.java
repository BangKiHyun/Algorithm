package programmers.dfs;

public class pg_단어_변환 {
    private static final int MAX_VALUE = 51;

    private static boolean[] used;
    private static int length;
    private static int ans = MAX_VALUE;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String words[] = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution(begin, target, words));
    }

    public static int solution(String begin, String target, String[] words) {
        length = words.length;
        used = new boolean[length];
        goDFS(begin, target, 0, words);
        return ans == MAX_VALUE ? 0 : ans;
    }

    private static void goDFS(String curWord, String target, int count, String[] words) {
        if (curWord.equals(target)) {
            ans = Math.min(ans, count);
            return;
        }

        for (int wordIdx = 0; wordIdx < length; wordIdx++) {
            if (!used[wordIdx] && isValid(curWord, words[wordIdx])) {
                used[wordIdx] = true;
                goDFS(words[wordIdx], target, count + 1, words);
                used[wordIdx] = false;
            }
        }
    }

    private static boolean isValid(String curWord, String word) {
        int diffCount = 0;
        for (int idx = 0; idx < curWord.length(); idx++) {
            if (curWord.charAt(idx) != word.charAt(idx)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }
}
