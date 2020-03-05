package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2661_DFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        goDFS(sb, 0, n);
    }

    private static void goDFS(StringBuilder sb, int depth, int len) {
        if (depth == len) {
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (isValue(String.valueOf(sb.append(i)))) {
                goDFS(sb, depth + 1, len);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean isValue(String num) {
        int length = num.length();
        for (int i = 1; i <= length / 2; i++) {
            String first = num.substring(length - i, length);
            String second = num.substring(length - (i * 2), length - i);
            if (first.equals(second)) {
                return false;
            }
        }
        return true;
    }
}
