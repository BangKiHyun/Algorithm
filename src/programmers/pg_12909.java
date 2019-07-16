package programmers;

public class pg_12909 {
    static boolean solution(String s) {
        boolean ans = true;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).equals("(")) {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt < 0) return false;
        }
        if (cnt != 0) return false;
        return ans;
    }

    public static void main(String[] args) {
        String s = "(())()";
        boolean a = solution(s);
        System.out.println(a);
    }
}
