package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class bj_1918_Stack {
    private static Map<Character, Integer> oprationPriority = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String operation = br.readLine();

        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack();
        initMap();
        for (char text : operation.toCharArray()) {
            if (isAlphabet(text)) {
                ans.append(text);
            } else if (text == '(') {
                stack.push(text);
            } else if (text == ')') {
                while (!stack.isEmpty()) {
                    char op = stack.pop();
                    if (op == '(') break;
                    ans.append(op);
                }
            } else {
                while (!stack.isEmpty() && oprationPriority.get(stack.peek()) >= oprationPriority.get(text)){
                    ans.append(stack.pop());
                }
                stack.push(text);
            }
        }
        while (!stack.isEmpty()){
            ans.append(stack.pop());
        }
        System.out.println(ans);
    }

    private static void initMap() {
        oprationPriority.put('(', 0);
        oprationPriority.put('+', 1);
        oprationPriority.put('-', 1);
        oprationPriority.put('*', 2);
        oprationPriority.put('/', 2);
    }

    private static boolean isAlphabet(char text) {
        return 'A' <= text && text <= 'Z';
    }
}
