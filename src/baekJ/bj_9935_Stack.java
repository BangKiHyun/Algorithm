package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj_9935_Stack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String bomb = br.readLine();

        final String answer = getAnswer(text, bomb);
        System.out.println(answer.length() == 0 ? "FRULA" : answer);

    }

    private static String getAnswer(String text, String bomb) {
        Stack<Character> stack = new Stack<>();
        int bombLen = bomb.length();
        int textLen = text.length();

        for (int i = 0; i < textLen; i++) {
            stack.push(text.charAt(i));

            if (stack.size() >= bombLen) {
                boolean flag = true;
                for (int j = 0; j < bombLen; j++) {
                    char stackChar = stack.get(stack.size() - bombLen + j);
                    char bombChar = bomb.charAt(j);
                    if (stackChar != bombChar) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < bombLen; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return String.valueOf(sb.reverse());
    }
}
