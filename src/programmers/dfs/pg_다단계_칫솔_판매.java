package programmers.dfs;

import java.util.*;

public class pg_다단계_칫솔_판매 {
    private static final String NONE = "-";

    private static Map<String, Integer> nameIdxMap = new HashMap<>();
    private static List<SalesPerson> salesPersonList = new ArrayList<>();

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        for (int ans : solution(enroll, referral, seller, amount)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        List<SalesPerson>[] connectedSalesPersonList = new ArrayList[enroll.length];
        initNameIdxMap(enroll);
        initConnectedList(connectedSalesPersonList, enroll, referral);

        final ProfitCalculator profitCalculator = new ProfitCalculator();
        for (int sellerIdx = 0; sellerIdx < seller.length; sellerIdx++) {
            int totalProfit = profitCalculator.calculateTotalProfit(amount[sellerIdx]);
            int distributionAmount = profitCalculator.calculateDistributionAmount(totalProfit);
            insertProfit(salesPersonList.get(nameIdxMap.get(seller[sellerIdx])), totalProfit, distributionAmount,
                    connectedSalesPersonList, profitCalculator);
        }

        int[] answer = new int[salesPersonList.size()];
        int answerIdx = 0;
        for (SalesPerson salesPerson : salesPersonList) {
            answer[answerIdx++] = salesPerson.getProfit();
        }
        return answer;
    }

    private static void insertProfit(SalesPerson curSeller, int totalProfit, int distributionAmount,
                                     List<SalesPerson>[] connectedSalesPersonList, ProfitCalculator profitCalculator) {
        curSeller.addProfit(totalProfit - distributionAmount);
        if(profitCalculator.calculateDistributionAmount(totalProfit) == 0) return;
        for (SalesPerson salesPerson : connectedSalesPersonList[nameIdxMap.get(curSeller.getName())]) {
            insertProfit(salesPerson, distributionAmount, profitCalculator.calculateDistributionAmount(distributionAmount),
                    connectedSalesPersonList, profitCalculator);
        }
    }

    private static void initNameIdxMap(String[] enroll) {
        int idx = 0;
        for (String name : enroll) {
            nameIdxMap.put(name, idx++);
            salesPersonList.add(new SalesPerson(name));
        }
    }

    private static void initConnectedList(List<SalesPerson>[] connectedSalesPersonList, String[] enroll, String[] referral) {
        int length = enroll.length;
        for (int idx = 0; idx < length; idx++) {
            connectedSalesPersonList[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < length; idx++) {
            String enrollName = enroll[idx];
            String referralName = referral[idx];
            if (referralName.equals(NONE)) continue;
            connectedSalesPersonList[nameIdxMap.get(enrollName)].add(salesPersonList.get(nameIdxMap.get(referralName)));
        }
    }

    private static class SalesPerson {
        private String name;
        private int profit;

        public SalesPerson(String name) {
            this.name = name;
            this.profit = 0;
        }

        public void addProfit(int profit) {
            this.profit += profit;
        }

        public String getName() {
            return this.name;
        }

        public int getProfit() {
            return this.profit;
        }
    }

    private static class ProfitCalculator {
        private static final int PROFIT = 100;
        private static final int DISTRIBUTION_PERCENT = 10;

        public int calculateTotalProfit(int saleQuantity) {
            return saleQuantity * PROFIT;
        }

        public int calculateDistributionAmount(int totalAmount) {
            return totalAmount / DISTRIBUTION_PERCENT;
        }
    }
}
