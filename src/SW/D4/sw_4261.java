package SW.D4;

import java.util.Scanner;

public class sw_4261 {
    public static String compare;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case + " ");
            String S = sc.next();
            int N = sc.nextInt();
            String text[] = new String[N];
            for (int i = 0; i < N; i++) {
                text[i] = sc.next();
            }
            ans(S, N, text);
        }
    }

    public static void ans(String S, int N, String[] text) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            if (S.length() < text[i].length()) continue;
            for (int j = 0; j < text[i].length(); j++) {
                Compare(S.charAt(j));
                for (int k = 0; k < compare.length(); k++) {
                    if (compare.charAt(k) == text[i].charAt(j)) {
                        cnt++;
                        continue;
                    }
                }
            }
            if (cnt == text[i].length()) ans++;
        }
        System.out.println(ans);
    }

    public static void Compare(char c) {
        switch (c) {
            case '2':
                compare = "abc";
                break;
            case '3':
                compare = "def";
                break;
            case '4':
                compare = "ghi";
                break;
            case '5':
                compare = "jkl";
                break;
            case '6':
                compare = "mno";
                break;
            case '7':
                compare = "pqrs";
                break;
            case '8':
                compare = "tuv";
                break;
            case '9':
                compare = "wxyz";
                break;
            default:
                break;
        }
    }
}
