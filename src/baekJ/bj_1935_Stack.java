package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class bj_1935_Stack {
    private static Map<Character, Double> alphabetMapper = new HashMap<>();
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        int n = parseInt(BR.readLine());
        String line = BR.readLine();

        initMap(n);

        Stack<Double> st = new Stack<>();
        for (char c : line.toCharArray()) {
            if (isAlphabet(c)) {
                st.push(alphabetMapper.get(c));
            } else {
                st.push(calculate(st.pop(), st.pop(), c));
            }
        }

        System.out.printf("%.2f", st.pop());
    }

    private static void initMap(int n) throws IOException {
        char alphabet = 'A';
        for (int i = 1; i <= n; i++) {
            alphabetMapper.put(alphabet++, parseDouble(BR.readLine()));
        }
    }

    private static boolean isAlphabet(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static double calculate(Double first, Double second, char operation) {
        switch (operation) {
            case '+':
                return second + first;
            case '-':
                return second - first;
            case '*':
                return second * first;
            case '/':
                return second / first;
        }
        return -1;
    }

}
