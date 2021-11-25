package leetcode.problems.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LC_MinimumHeightTrees {

    public static void main(String[] args) {
        int n = 2;
        int[][] edges = {{0, 1}};
        final LC_MinimumHeightTrees task = new LC_MinimumHeightTrees();
        for(int answer : task.findMinHeightTrees(n, edges)){
            System.out.println(answer);
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        } else if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];

        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                q.offer(i);
            }
        }
        while (n > 2) {
            int size = q.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                Integer node = q.poll();
                List<Integer> adjList = adj.get(node.intValue());
                for (Integer temp : adjList) {
                    degree[temp.intValue()]--;
                    if (degree[temp.intValue()] == 1) {
                        q.offer(temp);
                    }
                }
            }
        }
        while (q.isEmpty() == false) {
            res.add(q.poll());
        }
        return res;
    }
}
