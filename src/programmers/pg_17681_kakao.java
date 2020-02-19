package programmers;

public class pg_17681_kakao {
    private static final String WALL = "#";
    private static final String EMPTY = " ";

    public static void main(String[] args) {
        int n = 6;
        int[] arr1 = {46, 33, 33, 22, 31, 50};
        int[] arr2 = {27, 56, 19, 14, 14, 10};

        for (String s : solution(n, arr1, arr2)) {
            System.out.println(s);
        }
    }

    private static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            int[] binary1 = getDecimalToBinary(arr1[i], n);
            int[] binary2 = getDecimalToBinary(arr2[i], n);

            ans[i] = getBinaryToString(binary1, binary2, n);
        }

        return ans;
    }

    private static int[] getDecimalToBinary(int num, int length) {
        int ret[] = new int[length];

        while (num != 0) {
            int mok = num / 2;
            int measure = num % 2;

            ret[--length] = measure;
            num = mok;
        }

        return ret;
    }

    private static String getBinaryToString(int[] num1, int[] num2, int length) {
        String ret = "";

        for (int i = 0; i < length; i++) {
            int sum = num1[i] + num2[i];

            if (isWall(sum)) ret += WALL;
            else ret += EMPTY;
        }
        return ret;
    }

    private static boolean isWall(int num) {
        return num >= 1;
    }
}
