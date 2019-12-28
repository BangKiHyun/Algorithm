package programmers;

import java.util.ArrayList;

public class pg_17677_kakao {
    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = "AAAA12";
        System.out.println(solution(str1, str2));
    }

    private static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            if (c1 >= 97 && c1 <= 122 && c2 >= 97 && c2 <= 122) {
                list1.add(Character.toString(c1) + c2);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            if (c1 >= 97 && c1 <= 122 && c2 >= 97 && c2 <= 122) {
                list2.add(Character.toString(c1) + c2);
            }
        }

        double unionCnt = 0;
        double intersectionCnt = 0;
        for (String str : list1) {
            if (list2.contains(str)) {
                intersectionCnt++;
                list2.remove(str);
            }
            unionCnt = intersectionCnt + list2.size() + (list1.size() - intersectionCnt);
        }

        if (unionCnt == 0) return 65536;
        double num = (intersectionCnt / unionCnt) * 65536;
        return (int) num;
    }
}

// FRANCE, french
// handshake, shake hands
// aa1+aa2, AAAA12
// E=M*C^2, e-m*c^2