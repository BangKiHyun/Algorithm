package programmers;

public class pg_60057_Re {
    public static void main(String[] args) {
        String s = "aabbaccc";
        // "abcabcabcabcdededededede";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int ans = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            int pos = 0;
            int len = s.length();
            while (pos + i <= s.length()) {
                String target = findTarget(pos, pos + i, s);
                pos = getChangePosition(pos, i);
                int[] cntAndPos = countDuplicate(pos, i, s, target);
                len = getChangeLength(len, cntAndPos[0], i);
                ans = Math.min(ans, len);
                pos = cntAndPos[1];
            }
        }
        return ans;
    }

    private static String findTarget(int start, int end, String text) {
        return text.substring(start, end);
    }

    private static int getChangePosition(int pos, int length) {
        return pos + length;
    }

    private static int[] countDuplicate(int pos, int length, String s, String target) {
        int cnt = 0;
        while (pos + length <= s.length()) {
            if (isEquals(s.substring(pos, pos + length), target)) {
                cnt++;
                pos = getChangePosition(pos, length);
            } else {
                break;
            }
        }
        return new int[]{cnt, pos};
    }

    private static boolean isEquals(String target1, String target2) {
        return target1.equals(target2);
    }

    private static int getChangeLength(int len, int cnt, int length) {
        if (cnt == 0) return len;
        if (cnt < 9) len++;
        else if (cnt < 99) len += 2;
        else if (cnt < 999) len += 3;
        else len += 4;

        return len -= cnt * length;
    }
}
