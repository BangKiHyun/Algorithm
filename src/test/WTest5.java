package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WTest5 {
    private static int[] recipe = {4, 50, 10, 10, 4};
    private static int[] curAmount = {5, 100, 10, 5, 2};
    private static final List<Product> products = Arrays.asList(
            new Product(0, 10, 10000),
            new Product(1, 100, 3000),
            new Product(2, 30, 1000),
            new Product(3, 50, 2000),
            new Product(4, 10, 1000)
    );

    public static void main(String[] args) {
        double[] history = {1.0, 2.0, 1.5};
        for (int ans : solution(history)) {
            System.out.print(ans + " ");
        }
    }

    private static int[] solution(double[] history) {
        List<Integer> costList = new ArrayList<>();

        for (double amount : history) {
            if (isNotRange(amount)) {
                return new int[]{-1};
            }
            double junAmount = Math.floor(amount);
            double loverAmount = (amount - junAmount);

            if (junCantEating(junAmount)) {
                return new int[]{-1};
            }

            int neededAmount;
            int cost = 0;
            for (Product product : products) {
                if (product.isPepper()) {
                    neededAmount = (int) (junAmount * recipe[product.idx]);
                    neededAmount += loverAmount * recipe[product.idx];
                } else {
                    neededAmount = (int) (recipe[product.idx] * amount);
                }

                while (neededAmount > curAmount[product.idx]) {
                    curAmount[product.idx] += product.amount;
                    cost += product.price;
                }
                curAmount[product.idx] -= neededAmount;
            }
            costList.add(cost);
        }

        int[] answer = new int[costList.size()];
        for (int i = 0; i < costList.size(); i++) {
            answer[i] = costList.get(i);
        }

        return answer;
    }

    private static boolean isNotRange(double amount) {
        return amount > 2.5 || amount <= 0;
    }

    private static boolean junCantEating(double junAmount) {
        return junAmount == 0;
    }

    private static class Product {
        private int idx;
        private int amount;
        private int price;

        public Product(int idx, int amount, int price) {
            this.idx = idx;
            this.amount = amount;
            this.price = price;
        }

        public boolean isPepper() {
            return this.idx == 4;
        }
    }
}
