package programmers.binarysearch;

public class pg_징검다리_건너기 {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int stone : stones) {
            max = Math.max(max, stone);
            min = Math.min(min, stone);
        }

        return binarySearch(min, max, stones, k);
    }

    private static int binarySearch(int min, int max, int[] stones, int k) {
        if (min == max) return min;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canCross(stones, k, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return max;
    }

    private static boolean canCross(int[] stones, int k, int numberOfFriend) {
        int crossableCnt = 0;

        for (int stone : stones) {
            if (stone - numberOfFriend < 0) crossableCnt++;
            else crossableCnt = 0;

            if (crossableCnt == k) return false;
        }

        return true;
    }
}
