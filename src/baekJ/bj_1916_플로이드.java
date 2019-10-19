package baekJ;

import java.util.Scanner;

public class bj_1916_플로이드 {
    private static int cityCount, busCount;
    private static int startCityNum, endCityNum;
    private static int busCost[][];
    private final static int INF = 1000000001;
    private static Scanner sc;

    public static void main(String[] args) {
        init();
        int minCost = findCostOfMin();
        System.out.println(minCost);
    }

    private static void init() {
        sc = new Scanner(System.in);
        cityCount = sc.nextInt();
        busCount = sc.nextInt();
        busCost = new int[cityCount + 1][cityCount + 1];
        for (int i = 1; i <= cityCount; i++) {
            for (int j = 1; j <= cityCount; j++) {
                busCost[i][j] = INF;
            }
        }
        inputBusPay();
        startCityNum = sc.nextInt();
        endCityNum = sc.nextInt();
    }

    private static void inputBusPay() {
        for (int i = 0; i < busCount; i++) {
            int startCityOfBus = sc.nextInt();
            int endCityOfBus = sc.nextInt();
            int cost = sc.nextInt();
            busCost[startCityOfBus][endCityOfBus] = cost;
        }
    }

    private static int findCostOfMin() {
        for (int k = 1; k <= cityCount; k++) {
            for (int i = 1; i <= cityCount; i++) {
                for (int j = 1; j <= cityCount; j++) {
                    if (busCost[i][j] > busCost[i][k] + busCost[k][j])
                        busCost[i][j] = busCost[i][k] + busCost[k][j];
                }
            }
        }
        return busCost[startCityNum][endCityNum];
    }
}
