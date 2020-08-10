package programmers;

public class pg_62048 {
    public static void main(String[] args) {
        int w = 100_000_000;
        int h = 100_000_000;

        System.out.println(solution(w, h));
    }

    private static long solution(int w, int h) {
        long mul = (long) w * (long) h;
        int gcd = getGCD(w, h);

        return mul - (w / gcd + h / gcd - 1) * gcd;
    }

    private static int getGCD(final int w, final int h) {
        int min = Math.min(w, h);
        int max = Math.max(w, h);

        int remainder = 0;
        while (min != 0) {
            remainder = max % min;
            max = min;
            min = remainder;
        }

        return max;
    }
}
