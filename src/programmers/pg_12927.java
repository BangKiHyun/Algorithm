package programmers;

public class pg_12927 {
    public static void main(String[] args) {
        int n = 3;
        int[] works = {1, 1};
        long ans = solution(n, works);
        System.out.println(ans);

    }

    static public long solution(int n, int[] works) {
        long answer = 0;
        while (n > 0) {
            int idx = findMax(works);
            if (works[idx] > 0) works[idx]--;
            n--;
        }
        for (int i = 0; i < works.length; i++) {
            answer += works[i] * works[i];
        }
        return answer;
    }

    static int findMax(int[] works) {
        int idx = 0;
        int max = -1;
        for (int i = 0; i < works.length; i++) {
            if (works[i] > max) {
                max = works[i];
                idx = i;
            }
        }
        return idx;
    }
}
