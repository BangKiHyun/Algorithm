package programmers;

import java.util.Arrays;

public class pg_12910 {
    public static void main(String[] args) {
        int[] arr = //{5, 9, 7, 10};
                {2, 36, 1, 3};
        int divisor = //5;
                1;
        int[] ans = solution(arr, divisor);

        for (int i : ans) {
            System.out.println(i + " ");
        }
    }

    private static int[] solution(int[] arr, int divisor) {
        int length = arr.length;
        Arrays.sort(arr);

        if (divisor == 1) {
            return arr;
        }
        if (arr[length - 1] < divisor) {
            return new int[]{-1};
        }

        int cnt = 0;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            if (arr[i] % divisor == 0) {
                temp[cnt] = arr[i];
                cnt++;
            }
        }

        if (cnt == 0) return new int[]{-1};

        int[] answer = new int[cnt];
        answer = Arrays.copyOf(temp, cnt);
        return answer;
    }
}
