package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class bj_10789_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        int maxCnt = 0;
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            list.add(line);
            maxCnt += line.length();
        }

        String ans = "";
        int idx = 0;
        int cnt = 0;
        while (maxCnt != cnt) {
            for (int i = 0; i < list.size(); i++) {
                String text = list.get(i);
                if (text.length() > idx) {
                    ans += text.substring(idx, idx + 1);
                    cnt++;
                }
            }
            idx++;
        }
        System.out.println(ans);
    }
}
