package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class bj_2941_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 최대 100글자의 단어가 주어진다. 알파벳 소문자와 '-', '='로만 이루어져 있다.
        String alpabet = br.readLine();
        Set<String> set = new HashSet<>();

        //단어는 크로아티아 알파벳으로 이루어져 있다. 문제 설명의 표에 나와있는 알파벳은 변경된 형태로 입력된다.
        set.add("c=");
        set.add("c-");
        set.add("dz=");
        set.add("d-");
        set.add("lj");
        set.add("nj");
        set.add("s=");
        set.add("z=");

        int ans = 0;
        for (int i = 0; i < alpabet.length(); i++) {
            boolean check = false;
            for (int j = i + 3; j > i; j--) {
                if (j > alpabet.length()) continue;
                String split = alpabet.substring(i, j);
                if (set.contains(split)) {
                    ans++;
                    i = j - 1;
                    check = true;
                    break;
                }
            }
            if (!check) ans++;
        }
        System.out.println(ans);
    }
}
