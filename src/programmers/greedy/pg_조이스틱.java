package programmers.greedy;

public class pg_조이스틱 {
    private static final int ALPHABET_A = 65;
    private static final int ALPHABET_Z = 90;

    public static void main(String[] args) {
        String name = "JEROEN";
        System.out.println(solution(name));
    }

    public static int solution(String name) {
        int upDownMoveCnt = 0;

        // 위, 아래로 움직인 Cnt
        for (char alphabet : name.toCharArray()) {
            if (isSmallerThanMid(alphabet)) upDownMoveCnt += alphabet % ALPHABET_A;
            else upDownMoveCnt += ALPHABET_Z + 1 - alphabet;
        }

        // 좌,우로 움직인 Cnt
        int sideMoveCnt = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            int idx = i;

            if (name.charAt(idx) == 'A') {
                while (idx < name.length() && name.charAt(idx) == 'A') idx++;

                // A의 시작점 까지 갔다가 다시 돌아온 Cnt + 맨 뒤에서 A까지 움직인 Cnt
                int moveCnt = ((i - 1) * 2) + name.length() - idx;
                sideMoveCnt = Math.min(sideMoveCnt, moveCnt);
            }
        }

        return upDownMoveCnt + sideMoveCnt;
    }

    private static boolean isSmallerThanMid(char alphabet) {
        return alphabet < 78;
    }
}
