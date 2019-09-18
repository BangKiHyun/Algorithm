package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_42583_Queue {
    static Queue<TruckNode> remainTruck = new LinkedList<>();
    static Queue<TruckNode> ingTruck = new LinkedList<>();

    public static void main(String[] args) {
        int b_l = 100;
        int w = 100;
        int[] t_w = {10,10,10,10,10,10,10,10,10,10};

        int ans = solution(b_l, w, t_w);
        System.out.println(ans);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        for (int n : truck_weights) {
            remainTruck.add(new TruckNode(n, 0));
        }

        while (!remainTruck.isEmpty() || !ingTruck.isEmpty()) {
            answer++;
            int sum = 0;
            TruckNode tmpTruck = null;

            for (TruckNode truckNode : ingTruck) {
                sum += truckNode.weight;
                truckNode.position++;

                if (truckNode.position > bridge_length) {
                    tmpTruck = truckNode;
                }
            }

            if (tmpTruck != null) {
                ingTruck.poll();
                sum -= tmpTruck.weight;
            }

            if (!remainTruck.isEmpty() && (ingTruck.size() < bridge_length)) {
                TruckNode truckNode = remainTruck.peek();

                if (sum + truckNode.weight <= weight) {
                    truckNode = remainTruck.poll();
                    ingTruck.add(truckNode);
                    truckNode.position++;
                }
            }
        }
        return answer;
    }

    static class TruckNode {
        int weight, position;

        TruckNode(int w, int position) {
            this.weight = w;
            this.position = position;
        }
    }
}
