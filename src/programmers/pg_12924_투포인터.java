package programmers;

public class pg_12924_투포인터 {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n));
    }

    private static int solution(int n) {
        int answer = 0;

        int start = 0;
        int end = 0;

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }

        int sum = 0;
        while (end < n) {
            if (sum <= n) {
                if (sum == n) answer++;
                sum += arr[end++];
            } else {
                sum -= arr[start++];
            }
        }

        while (start < n) {
            if (sum == n) {
                answer++;
            }

            if (sum > n) {
                sum -= arr[start];
            }
            start++;
        }

        return answer + 1;
    }
}
