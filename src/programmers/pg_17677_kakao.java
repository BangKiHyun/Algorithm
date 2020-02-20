package programmers;

import java.util.ArrayList;

public class pg_17677_kakao {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution(str1, str2));
    }

    private static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        initList(str1, list1);
        initList(str2, list2);

        int intersectionCnt = getIntersectionCount(list1, list2);
        int unionCnt = getUnionCount(list1.size(), list2.size(), intersectionCnt);

        if (unionCnt == 0) return 65536;
        double num = ((double) intersectionCnt / unionCnt) * 65536;
        return (int) num;
    }

    private static void initList(String str, ArrayList<String> list) {
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if (isAlphabet(c1) && isAlphabet(c2)) {
                list.add(getAlphabet(c1, c2));
            }
        }
    }

    private static boolean isAlphabet(char alphabet) {
        return alphabet >= 'a' && alphabet <= 'z';
    }

    private static String getAlphabet(char c1, char c2) {
        return Character.toString(c1) + c2;
    }

    private static int getIntersectionCount(ArrayList<String> list1, ArrayList<String> list2) {
        int intersectionCnt = 0;
        for (String str : list1) {
            if (list2.contains(str)) {
                intersectionCnt++;
                list2.remove(str);
            }
        }
        return intersectionCnt;
    }

    private static int getUnionCount(int size1, int size2, int intersectionCnt) {
        return size1 + intersectionCnt + (size2 - intersectionCnt);
    }
}

// FRANCE, french
// handshake, shake hands
// aa1+aa2, AAAA12
// E=M*C^2, e-m*c^2