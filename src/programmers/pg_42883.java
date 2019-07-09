package programmers;

public class pg_42883 {
    static public String solution(String number, int k) {
        String answer = "";
        char max;
        int idx = 0;
        if(number.charAt(0) == '0') return "0";
        for (int i = 0; i < number.length() - k; i++) {
            max = '0';
            for (int j = idx; j <= k+1; j++) {
                if (max < number.charAt(j)){
                    max = number.charAt(j);
                    idx = j+1;
                }
            }
            answer += max;
        }
        return answer;
    }

    public static void main(String[] args) {
        String num = "1231234";
        int k = 3;
        String ans = solution(num, k);
        System.out.println(ans);
    }
}
