package programmers;

public class pg_12977_DFS {
    private static final int MAX_NUMBER = 3_000;
    private static final int MAX_SIZE = 3;

    private static boolean[] isNotPrimeNumbers;
    private static boolean[] visit;

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 6, 4};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        visit = new boolean[nums.length];
        initPrimeNumber();

        return goDFS(0, 0, 0, nums);
    }

    private static void initPrimeNumber() {
        isNotPrimeNumbers = new boolean[MAX_NUMBER + 1];
        for (int i = 2; i <= MAX_NUMBER; i++) {
            if (isNotPrimeNumbers[i]) continue;
            for (int j = i + i; j <= MAX_NUMBER; j += i) {
                isNotPrimeNumbers[j] = true;
            }
        }
    }

    private static int goDFS(int startIdx, int depth, int sum, int[] nums) {
        if (depth == MAX_SIZE) {
            if (!isNotPrimeNumbers[sum]) {
                return 1;
            } else return 0;
        }
        if (startIdx == nums.length) return 0;

        return goDFS(startIdx + 1, depth, sum, nums) +
                goDFS(startIdx + 1, depth + 1, sum + nums[startIdx], nums);
    }
}
