package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2490_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt;
        String result;
        for (int i = 0; i < 3; i++) {
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) {
                cnt += Integer.parseInt(st.nextToken());
            }
            result = getResult(cnt);
            System.out.println(result);
        }
    }

    private static String getResult(int cnt) {
        switch (cnt) {
            case 0:
                return "D";
            case 1:
                return "C";
            case 2:
                return "B";
            case 3:
                return "A";
            default:
                return "E";
        }
    }
}
