package programmers;

import java.util.Arrays;

public class pg_42886 {

    public static void main(String[] args) {
        int[] weight = {3, 1, 6, 2, 7, 30, 1};
        int ans = solution(weight);
        System.out.println(ans);
    }

    public static int solution(int[] weight) {
        Arrays.sort(weight);

        if(weight[0] != 1)
            return 0;

        int sum = weight[0];
        for(int i=1;i<weight.length; i++){
            if(weight[i] > sum +1) break;
            sum += weight[i];
        }
        return sum + 1;
    }
}
