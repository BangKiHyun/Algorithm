package programmers.dfs;

public class pg_타겟_넘버 {

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(numbers, target));
    }

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
}
