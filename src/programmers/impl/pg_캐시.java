package programmers.impl;

import java.util.LinkedList;
import java.util.Queue;

public class pg_캐시 {

    private static final int CACHE_HIT = 1;
    private static final int CACHE_MISS = 5;

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(solution(cacheSize, cities));
    }

    public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * CACHE_MISS;
        }

        Queue<String> cacheQueue = new LinkedList<>();
        int answer = 0;
        int curCacheSize = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            curCacheSize++;
            if (cacheQueue.contains(city)) {
                cacheQueue.remove(city);
                answer += CACHE_HIT;
                curCacheSize--;
                cacheQueue.offer(city);
            } else {
                if (curCacheSize > cacheSize) {
                    cacheQueue.poll();
                    curCacheSize--;
                }
                answer += CACHE_MISS;
                cacheQueue.offer(city);
            }
        }

        return answer;
    }
}
