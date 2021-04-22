package programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

public class pg_다리를_지나는_트럭_Re {
    private static int currentBridgeWeight = 0;

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int truck_weight[] = {7, 4, 5, 6};

        System.out.println(solution(bridge_length, weight, truck_weight));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> remainTruck = new LinkedList<>();
        initRemainTruck(truck_weights, remainTruck);

        Queue<Truck> crossingTruck = new LinkedList<>();
        int time = 0;
        while (!remainTruck.isEmpty() || !crossingTruck.isEmpty()) {
            if (!crossingTruck.isEmpty()) {
                moveTruck(crossingTruck, bridge_length);
            }

            if (!remainTruck.isEmpty() && crossingTruck.size() < bridge_length) {
                final Truck nextTruck = remainTruck.peek();
                if (nextTruck.canCross(weight)) {
                    Truck truck = remainTruck.poll();
                    truck.move();
                    crossingTruck.add(truck);
                    currentBridgeWeight += nextTruck.weight;
                }
            }
            time++;
        }
        return time;
    }

    private static void moveTruck(Queue<Truck> crossingTruck, int bridgeLength) {
        boolean isPassed = false;
        for (Truck truck : crossingTruck) {
            truck.move();
            if (truck.passBridge(bridgeLength)) {
                isPassed = true;
            }
        }
        if (isPassed) {
            final Truck passedTruck = crossingTruck.poll();
            currentBridgeWeight -= passedTruck.weight;
        }
    }

    private static void initRemainTruck(int[] truck_weights, Queue<Truck> remainTruck) {
        for (int truckWeight : truck_weights) {
            remainTruck.add(new Truck(0, truckWeight));
        }
    }

    private static class Truck {
        private int pos;
        private int weight;

        public Truck(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
        }

        public void move() {
            this.pos++;
        }

        public boolean passBridge(int bridgeLength) {
            return this.pos > bridgeLength;
        }

        public boolean canCross(int tolerableWeight) {
            return currentBridgeWeight + this.weight <= tolerableWeight;
        }
    }
}