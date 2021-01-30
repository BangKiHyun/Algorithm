package programmers.queue;

import java.util.Stack;

public class pg_짝지어_제거하기 {

    public static void main(String[] args) {
        String s = "baabaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char alphabet : s.toCharArray()) {
            stack.push(alphabet);

            if (stack.size() >= 2) {
                char firstChar = stack.get(stack.size() - 1);
                char secondChar = stack.get(stack.size() - 2);

                if (firstChar == secondChar) {
                    stack.pop();
                    stack.pop();
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
