package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_42583_Queue_re {
    public static void main(String[] args) {
        int l = 2;
        int w = 10;
        int[] tr = {7, 4, 5, 6};
                //{10};
        int ans = solution(l, w, tr);
        System.out.println(ans);
    }

    private static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Truck> remainTruck = new LinkedList<>();
        Queue<Truck> ingTruck = new LinkedList<>();

        for (int i : truck_weights) {
            remainTruck.add(new Truck(i, 0));
        }

        int ingWeight = 0;
        while (!remainTruck.isEmpty() || !ingTruck.isEmpty()) {
            time++;
            Truck tmpTruck = null;

            for (Truck t : ingTruck) {
                t.position++;
                if (t.position > bridge_length) {
                    tmpTruck = t;
                }
            }

            if(tmpTruck != null){
                ingTruck.poll();
                ingWeight -= tmpTruck.weight;
            }

            if (!remainTruck.isEmpty() && ingTruck.size() < bridge_length) {
                tmpTruck = remainTruck.peek();
                if (ingWeight + tmpTruck.weight <= weight) {
                    tmpTruck = remainTruck.poll();
                    ingTruck.add(tmpTruck);
                    tmpTruck.position++;
                    ingWeight += tmpTruck.weight;
                }
            }
        }
        return time;
    }

    private static class Truck {
        int weight, position;

        Truck(int weight, int position) {
            this.weight = weight;
            this.position = position;
        }
    }
}
