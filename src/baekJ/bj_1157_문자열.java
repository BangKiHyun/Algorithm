package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1157_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alphabet = br.readLine();
        alphabet = alphabet.toUpperCase();
        char[] arr = alphabet.toCharArray();
        int[] arr_alpha = new int[26];

        int max = -1;
        char ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i] - 65;
            arr_alpha[idx]++;
            if (arr_alpha[idx] > max) {
                ans = arr[i];
                max = arr_alpha[idx];
            }
        }

        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (arr_alpha[i] == max) cnt++;
        }

        System.out.println(cnt > 1 ? "?" : ans);
    }
}
