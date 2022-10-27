package baekJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_11000_강의실배정 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        final Queue<LectureTime> lectureTimes = new PriorityQueue<>();
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            lectureTimes.offer(new LectureTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        final Queue<Integer> endTime = new PriorityQueue<>();
        endTime.offer(Objects.requireNonNull(lectureTimes.poll()).end);

        while (!lectureTimes.isEmpty()) {
            final LectureTime curLectureTime = lectureTimes.poll();
            if(curLectureTime.start >= endTime.peek()) {
                endTime.poll();
            }
            endTime.offer(curLectureTime.end);
        }
        System.out.println(endTime.size());
    }

    private static class LectureTime implements Comparable<LectureTime> {
        private int start;
        private int end;

        public LectureTime(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(LectureTime o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }
}
