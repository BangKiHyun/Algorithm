package programmers;

import java.util.Stack;

public class pg_12973_Stack {
    public static void main(String[] args) {
        String s = "cdcd";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        char[] text = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (char c : text) {
            stack.push(c);

            if (stack.size() >= 2) {
                char curText = stack.get(stack.size() - 1);
                char nextText = stack.get(stack.size() - 2);

                if (curText == nextText) {
                    stack.pop();
                    stack.pop();
                }
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
