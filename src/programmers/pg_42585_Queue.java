package programmers;

public class pg_42585_Queue {
    public static void main(String[] args) {
        String s = "()(((()())(())()))(())";
        int ans = solution(s);
        System.out.println(ans);
    }

    public static int solution(String arrangement) {
        int size = arrangement.length();
        int stick = 0;
        int sum = 0;

        for (int i = 0; i < size; i++) {
            String s = arrangement.substring(i, i + 1);
            if (s.equals("(")) {
                stick++;
            } else if (s.equals(")")) {
                stick--;
                String s1 = arrangement.substring(i - 1, i);
                if (s1.equals("(")) {
                    sum += stick;
                }else sum++;
            }
        }
        return sum;
    }
}