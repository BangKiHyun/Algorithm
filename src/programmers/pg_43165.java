package programmers;

public class pg_43165 {
    // dfs 내가 푼거 아님
    // 연습하기
    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    public static int dfs(int[] number, int target, int idx, int num) {
        if (idx == number.length) {
            return num == target ? 1 : 0;
        } else {
            return dfs(number, target, idx + 1, num + number[idx])
                    + dfs(number, target, idx + 1, num - number[idx]);
        }
    }

    public static void main(String[] args) {
        int[] num = {1, 1, 1, 1, 1};
        int target = 3;
        int ans = solution(num, target);
        System.out.println(ans);
    }
}
