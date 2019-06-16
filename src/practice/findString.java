package practice;

import java.util.Scanner;

public class findString {

    static int find(String parent, String pattern) {
        int parentSize = parent.length();
        int patternSize = pattern.length();
        char[] str = parent.toCharArray();
        char[] compare = pattern.toCharArray();

        for (int i = 0; i <= parentSize - patternSize; i++) {
            boolean finded = true;
            for (int j = 0; j < patternSize; j++) {
                if (str[i + j] != compare[j]) {
                    finded = false;
                    break;
                }
                if (finded) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String pattern = sc.next();
        int result = find(input, pattern);
        if (result == -1) {
            System.out.println("값을 찾을 수 없습니다");
        } else {
            System.out.print(result + "번째에서 찾았습니다");
        }
    }
}
