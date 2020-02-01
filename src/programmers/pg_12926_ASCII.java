package programmers;

//어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
//예를 들어 AB는 1만큼 밀면 BC가 되고, 3만큼 밀면 DE가 됩니다. z는 1만큼 밀면 a가 됩니다.
//문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.

//제한 조건
//공백은 아무리 밀어도 공백입니다.
//s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
//s의 길이는 8000이하입니다.
//n은 1 이상, 25이하인 자연수입니다.
public class pg_12926_ASCII {
    public static void main(String[] args) {
        String s = "z";
        int n = 1;

        System.out.println(solution(s, n));
    }

    private static String solution(String s, int n) {
        StringBuilder ans = new StringBuilder();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char alpa = s.charAt(i);

            if (alpa == ' ') {
                ans.append(alpa);
                continue;
            } else if (alpa <= 90) {
                alpa = changeBigAlpa(alpa, n);
            } else {
                alpa = changeSmallAlpa(alpa, n);
            }

            ans.append(alpa);
        }

        return String.valueOf(ans);
    }

    private static char changeBigAlpa(char alpa, int cnt) {
        if (alpa + cnt > 90) {
            cnt = alpa + cnt - 26;
            alpa = (char) cnt;
        } else {
            alpa += cnt;
        }

        return alpa;
    }

    private static char changeSmallAlpa(char alpa, int cnt) {
        if (alpa + cnt > 122) {
            cnt = alpa + cnt - 26;
            alpa = (char) cnt;
        } else {
            alpa += cnt;
        }

        return alpa;
    }
}
//아스키코드
//65~90 A~Z
//97~122 a~z

//Test Case
//AB
//a B z