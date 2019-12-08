package programmers;

public class pg_60057_Fail {
    private static boolean[] visit;
    private static int sum = 0;
    private static int length;

    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int answer = 0;
        length = s.length();
        visit = new boolean[length];
        int mid;

        for (int i = 0; i < length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                mid = (i + length) / 2;
                int len = mid;
                for (int j = i; j < len; j++) {
                    if (goDFS(i, mid, s)) {
                        sum += (mid + 1);
                        i = mid;
                        continue;
                    } else {
                        mid--;
                    }
                }
            }
        }

        System.out.println(sum);
        return answer;
    }

    private static boolean goDFS(int start, int mid, String s) {
        System.out.println(start + " " + mid);
        for (int i = start; i < mid; i++) {
            String first = s.substring(i, i + 1);
            String second = s.substring(mid, mid + i);
            if (first.equals(second)) {
                System.out.println(first + " " + second);
                continue;
            } else {
                return false;
            }
        }
        System.out.println("통과");
        return true;
    }
}
