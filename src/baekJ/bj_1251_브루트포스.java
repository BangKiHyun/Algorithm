package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class bj_1251_브루트포스 {
    private static TreeSet<String> list = new TreeSet();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();

        for (int i = 0; i < text.length() - 2; i++) {
            for (int j = i + 1; j < text.length() - 1; j++) {
                String first = text.substring(0, j);
                if (isRange(first.length())) first = reverseAlpabet(first);
                for (int k = j; k < text.length() - 1; k++) {
                    for (int m = k + 1; m < text.length(); m++) {
                        String second = text.substring(k, m);
                        if (isRange(second.length())) second = reverseAlpabet(second);
                        String third = text.substring(m, text.length());
                        if (isRange(third.length())) third = reverseAlpabet(third);
                        if (!list.contains(first + second + third)) list.add(first + second + third);
                    }
                    break;
                }
            }
            break;
        }
        System.out.println(list.first());
    }

    private static boolean isRange(int length) {
        return length != 1;
    }

    private static String reverseAlpabet(String alpabet) {
        return (new StringBuffer(alpabet)).reverse().toString();
    }
}

//arrested
//mobital
