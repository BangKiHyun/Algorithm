package programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

public class pg_다리를_지나는_트럭 {
    private static int curBridgeWeight = 0;

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int truck_weight[] = {7, 4, 5, 6};

        System.out.println(solution(bridge_length, weight, truck_weight));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> remainTruck = new LinkedList<>();
        Queue<Truck> movingTruck = new LinkedList<>();

        initRemainTruck(remainTruck, truck_weights);

        int cnt = 0;
        while (!remainTruck.isEmpty() || !movingTruck.isEmpty()) {
            cnt++;
            moveTruck(movingTruck, bridge_length);

            if (!remainTruck.isEmpty() && movingTruck.size() < bridge_length) {
                final Truck nextTruck = remainTruck.peek();
                int totalBridgeWeight = curBridgeWeight + nextTruck.weight;

                if (totalBridgeWeight <= weight) {
                    final Truck truck = remainTruck.poll();
                    truck.move();
                    movingTruck.add(truck);
                    curBridgeWeight += truck.weight;
                }
            }
        }

        return cnt;
    }

    private static void initRemainTruck(Queue<Truck> remainTruck, int[] truck_weights) {
        for (int truck_weight : truck_weights) {
            remainTruck.add(new Truck(0, truck_weight));
        }
    }

    private static void moveTruck(Queue<Truck> movingTruck, int bridgeLength) {
        Truck passTruck = null;
        for (Truck truck : movingTruck) {
            truck.move();

            if (truck.isPass(bridgeLength)) {
                passTruck = movingTruck.peek();
            }
        }

        if (passTruck != null) {
            curBridgeWeight -= movingTruck.poll().weight;
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

        public boolean isPass(int bridgeLength) {
            return this.pos > bridgeLength;
        }
    }
}
