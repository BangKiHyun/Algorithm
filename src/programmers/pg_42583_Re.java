package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_42583_Re {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        System.out.println(solution(bridge_length, weight, truck_weights));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> remainTruck = new LinkedList<>();
        Queue<Truck> moveTruck = new LinkedList<>();

        for (int truck_weight : truck_weights) {
            remainTruck.offer(new Truck(truck_weight, 0));
        }

        int time = 0;
        while (!remainTruck.isEmpty() || !moveTruck.isEmpty()) {
            time++;

            int curWeigth = 0;
            Truck tempTruck = null;
            for (Truck truck : moveTruck) {
                curWeigth += truck.weight;
                truck.move();

                if (truck.position > bridge_length) {
                    tempTruck = truck;
                }
            }

            if (tempTruck != null) {
                curWeigth -= moveTruck.poll().weight;
            }

            if (!remainTruck.isEmpty() && (moveTruck.size() < bridge_length)) {
                Truck truck = remainTruck.peek();
                if (curWeigth + truck.weight <= weight) {
                    truck.position++;
                    moveTruck.offer(truck);
                    remainTruck.poll();
                }
            }
        }

        return time;
    }

    private static class Truck {
        private int weight;
        private int position;

        public Truck(final int weight, final int position) {
            this.weight = weight;
            this.position = position;
        }

        public void move() {
            this.position++;
        }
    }
}
