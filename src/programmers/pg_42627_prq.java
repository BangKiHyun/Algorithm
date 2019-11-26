package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_42627_prq {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        int ans = solution(jobs);
        System.out.println(ans);
    }

    private static int solution(int[][] jobs) {
        Queue<Disk> q = new PriorityQueue<>();
        List<Disk> list = new ArrayList<>();

        for (int i = 0; i < jobs.length; i++) {
            q.add(new Disk(jobs[i][0], jobs[i][1]));
        }

        for(int i=0;i<jobs.length;i++){
            list.add(q.poll());
        }

        int time = 0;
        int answer = 0;

        while (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (time >= list.get(i).start) {
                    time += list.get(i).work;
                    answer += time - list.get(i).start;
                    list.remove(i);
                    break;
                }
                //시작시간이 현재 시간보다 이전인 것이 없다면 1초 증가
                if (i == list.size() - 1) time++;
            }
        }

        System.out.println(answer);
        return answer / jobs.length;
    }

    private static class Disk implements Comparable<Disk> {
        int start, work;

        Disk(int start, int work) {
            this.start = start;
            this.work = work;
        }

        @Override
        public int compareTo(Disk o) {
            if (this.work < o.work) {
                return -1;
            } else if (this.work == o.work) {
                if (this.start < o.start) {
                    return -1;
                } else {
                    return 1;
                }
            } else return 1;
        }
    }
}
