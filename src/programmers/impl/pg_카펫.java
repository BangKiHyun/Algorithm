package programmers.impl;

public class pg_카펫 {
    private static final int CORNER_CNT = 4;

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;

        for (int ans : solution(brown, yellow)) {
            System.out.print(ans + " ");
        }
    }


    public static int[] solution(int brown, int yellow) {
        for (int height = 1; height <= yellow; height++) {
            int width = getWidth(yellow, height);
            int maybeBrownCnt = (width + height) * 2;

            maybeBrownCnt += CORNER_CNT;
            if (brown == maybeBrownCnt) {
                return new int[]{width + 2, height + 2};
            }
        }

        return new int[]{-1};
    }

    private static int getWidth(int totalSize, int height) {
        int width = totalSize / height;
        return totalSize % height == 0 ? width : width + 1;
    }
}
