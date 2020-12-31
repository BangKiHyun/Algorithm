package programmers.sort;

import java.util.*;

public class pg_디스크_컨트롤러 {
    public static void main(String[] args) {
        int[][] jobs = {{0, 10},
                {4, 10},
                {5, 11},
                {15, 2}};
        System.out.println(solution(jobs));
    }

    public static int solution(int[][] jobs) {
        Queue<Disk> diskQueue = new PriorityQueue<>(new DiskComparator());
        initDiskQueue(jobs, diskQueue);

        int curTime = 0;
        int totalProcessTime = 0;

        List<Disk> diskList = new ArrayList<>();
        while (!diskQueue.isEmpty()) {
            diskList.add(diskQueue.poll());
        }

        while (!diskList.isEmpty()) {
            for (int i = 0; i < diskList.size(); i++) {
                if (curTime >= diskList.get(i).startTime) {
                    final Disk disk = diskList.remove(i);
                    curTime += disk.processTime;
                    totalProcessTime += curTime - disk.startTime;
                    break;
                }

                if (i == diskList.size() - 1) {
                    curTime++;
                }
            }
        }
        return totalProcessTime / jobs.length;
    }

    private static void initDiskQueue(int[][] jobs, Queue<Disk> diskQueue) {
        int len = jobs.length;
        for (int i = 0; i < len; i++) {
            diskQueue.offer(new Disk(jobs[i][0], jobs[i][1]));
        }
    }

    private static class Disk {
        private int startTime;
        private int processTime;

        public Disk(int startTime, int processTime) {
            this.startTime = startTime;
            this.processTime = processTime;
        }
    }

    private static class DiskComparator implements Comparator<Disk> {
        @Override
        public int compare(Disk o1, Disk o2) {
            if (o1.processTime - o2.processTime < 0) {
                return -1;
            } else if (o1.processTime == o2.processTime) {
                return o1.startTime - o2.startTime;
            }
            return 1;
        }
    }
}
