package programmers;

public class pg_12911 {
    static public int solution(int n) {
        int remain = 0;
        int cnt1 = 0;
        int cnt2 = -1;
        int num = n;

        while (num != 0) {
            remain = num % 2;
            num /= 2;
            if (remain == 1) cnt1++;
        }
        while (cnt1 != cnt2){
            cnt2 = change(++n);
        }

        return n;
    }

    static public int change(int n){
        int remain = 0;
        int cnt = 0;
        while (n != 0) {
            remain = n % 2;
            n /= 2;
            if (remain == 1) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 78;
        int a = solution(n);
        System.out.println(a);
    }
}
