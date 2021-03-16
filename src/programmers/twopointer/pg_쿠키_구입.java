package programmers.twopointer;

public class pg_쿠키_구입 {

    public static void main(String[] args) {
        int[] cooke = {1, 1, 2, 3};
        System.out.println(solution(cooke));
    }

    public static int solution(int[] cookie) {
        int maxLength = cookie.length - 1;
        int answer = 0;
        for (int idx = 0; idx < maxLength; idx++) {
            int olderBrotherCookieIdx = idx;
            int olderBrotherCookieCount = cookie[olderBrotherCookieIdx];

            int youngerBrotherCookieIdx = olderBrotherCookieIdx + 1;
            int youngerBrotherCookieCount = cookie[youngerBrotherCookieIdx];

            while (true) {
                if (olderBrotherCookieCount == youngerBrotherCookieCount && answer < olderBrotherCookieCount) {
                    answer = olderBrotherCookieCount;
                } else if (olderBrotherCookieCount <= youngerBrotherCookieCount && olderBrotherCookieIdx != 0) {
                    olderBrotherCookieCount += cookie[--olderBrotherCookieIdx];
                } else if (olderBrotherCookieCount > youngerBrotherCookieCount && youngerBrotherCookieIdx != maxLength) {
                    youngerBrotherCookieCount += cookie[++youngerBrotherCookieIdx];
                } else break;
            }
        }
        return answer;
    }
}
