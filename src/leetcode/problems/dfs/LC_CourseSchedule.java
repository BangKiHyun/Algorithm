package leetcode.problems.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = toGraph(prerequisites);

        Set<Integer> visited = new HashSet<>(), visiting = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(graph, i, visited, visiting)) return false;
        }

        return visited.size() == numCourses;
    }

    private boolean isCyclic(Map<Integer, Set<Integer>> graph, int start, Set<Integer> visited,
                             Set<Integer> visiting) {
        if (visited.contains(start)) return false;
        if (visiting.contains(start)) return true;

        if (!graph.containsKey(start) || graph.get(start).isEmpty()) {
            visited.add(start);
            return false;
        }

        visiting.add(start);
        for (int nei : graph.get(start)) {
            if (isCyclic(graph, nei, visited, visiting)) return true;
        }

        visiting.remove(start);
        visited.add(start);
        return false;
    }

    private Map<Integer, Set<Integer>> toGraph(int[][] prereqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int from, to;

        for (int[] prereq : prereqs) {
            from = prereq[1];
            to = prereq[0];
            if (!graph.containsKey(from)) graph.put(from, new HashSet<>());
            graph.get(from).add(to);
        }
        return graph;
    }
}
