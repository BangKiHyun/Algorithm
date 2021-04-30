package programmers.hash;

import java.util.Arrays;
import java.util.Comparator;

public class pg_전화번호_목록 {

    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.comparingInt(String::length));
        int len = phone_book.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
