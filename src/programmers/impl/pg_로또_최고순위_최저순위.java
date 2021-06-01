package programmers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class pg_로또_최고순위_최저순위 {

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        for (int ans : solution(lottos, win_nums)) {
            System.out.println(ans);
        }
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        final LottoTicket lottoTicket = new LottoTicket(lottos);
        final WinNumbers winNumbers = new WinNumbers(win_nums);
        int maxPrice = LottoResult.calculatePrice(lottoTicket.findMatchCountOfMaxPrice(winNumbers));
        int minPrice = LottoResult.calculatePrice(lottoTicket.findMatchCountOfMinPrice(winNumbers));

        return new int[]{maxPrice, minPrice};
    }

    private static class LottoTicket {
        private static final int LOTTO_SIZE = 6;
        private static final int ANYTHING = 0;

        private final List<Integer> lottoNumbers;

        public LottoTicket(int[] lottos) {
            this.lottoNumbers = Arrays.stream(lottos)
                    .boxed()
                    .collect(Collectors.toList());
        }

        public int findMatchCountOfMaxPrice(WinNumbers winNumbers) {
            return (int) lottoNumbers.stream()
                    .filter(num -> num == ANYTHING || winNumbers.contains(num))
                    .count();
        }

        public int findMatchCountOfMinPrice(WinNumbers winNumbers) {
            return (int) lottoNumbers.stream()
                    .filter(num -> num != ANYTHING && winNumbers.contains(num))
                    .count();
        }
    }

    private static class WinNumbers {
        private final List<Integer> winNums;

        public WinNumbers(int[] winNumbs) {
            this.winNums = Arrays.stream(winNumbs)
                    .boxed()
                    .collect(Collectors.toList());
        }

        public boolean contains(Integer num) {
            return winNums.contains(num);
        }
    }

    private enum LottoResult {
        FIRST(1, 6),
        SECOND(2, 5),
        THIRD(3, 4),
        FOURTH(4, 3),
        FIFTH(5, 2),
        FAIL(6, 0);

        private final int ranking;
        private final int matchCount;

        LottoResult(int ranking, int matchCount) {
            this.ranking = ranking;
            this.matchCount = matchCount;
        }


        public static int calculatePrice(int matchCount) {
            return Arrays.stream(LottoResult.values())
                    .filter(lottoResult -> lottoResult.getMatchedCount() == matchCount)
                    .findFirst()
                    .orElse(FAIL)
                    .getRanking();
        }

        private int getMatchedCount() {
            return this.matchCount;
        }

        private int getRanking() {
            return ranking;
        }
    }
}
