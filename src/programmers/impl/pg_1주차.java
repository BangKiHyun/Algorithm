package programmers.impl;

public class pg_1주차 {

    public static void main(String[] args) {
        int price = 3;
        int money = 20;
        int count = 4;
        final pg_1주차 task = new pg_1주차();
        System.out.println(task.solution(price, money, count));
    }

    public long solution(int price, int money, int count) {
        long requiredMoney = 0;
        int origin = price;
        while (count > 0) {
            requiredMoney += price;
            price += origin;
            count--;
        }
        return requiredMoney - money;
    }
}
