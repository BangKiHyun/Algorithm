package SW.D3;

import java.util.*;

public class sw_7087 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            int N = sc.nextInt();
            char start = 'A';
            int count = 0;
            HashSet<Character> test = new HashSet<>();
            for (int i = 0; i < N; i++) {
                String input = sc.next();
                test.add(input.charAt(0));
            }
            ArrayList text = new ArrayList(test);
            Collections.sort(text);
            for (int j = 0; j < N; j++) {
                if (text.get(j).equals(start)){
                    count++;
                    start++;
                } else break;
            }
            System.out.println("#" + test_case + " " + count);
        }
    }
}
