package programmers;

public class pg_12904_구현_fail {
    public static void main(String[] args) {
        String s = "abcdcba";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int length = s.length();
        //String post_str = reverseStr(s);
        //if (isEquals(s, post_str)) return length;
        if (isEquals(s)) return length;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (length - i < ans) return ans;
            for (int j = length; j > i; j--) {
                if (j - i <= ans) break;
                String pre_str = s.substring(i, j);
                /*post_str = reverseStr(pre_str);
                if (isEquals(pre_str, post_str)) {
                    ans = post_str.length();
                    break;
                }*/
                if (isEquals(pre_str)) {
                    ans = pre_str.length();
                    break;
                }
            }
        }
        return ans;
    }

/*    private static String reverseStr(String s) {
        return (new StringBuffer(s).reverse().toString());
    }

    private static boolean isEquals(String s1, String s2) {
        return s1.equals(s2);
    }*/

    private static boolean isEquals(String str) {
        int idx = 0;
        int len = str.length();
        while (idx != (len / 2)) {
            if (str.charAt(idx) != str.charAt(len - idx - 1)) return false;
            idx++;
        }
        return true;
    }
}
//풀이
//문자열 자를 부분을 0부터 시작해서 치대길이부터 시작해서 최소길이+1 까지 순서대로 자르면서 펠린드롭을 찾는다
//0부터 시작해서 최소길이+1 까지 다 잘랐으면 그다음문자은 1부터 시작해서 처음에 했던 부분을 다시 반복한다
//만약 남아있는 문자 길이가 현재 펠린드롭길이보다 작으면 return 하고 끝낸다


//가위치기 할 수 있는 부분
//펠린드롭이 될 수 있는 문자열의 길이를 저장해놓고
//substring 한 값이 현재 가장 긴 펠린드롭길이보다 작으면 continue 해서 넘겅간다

//Test Case
//abcdcba
//abacde
