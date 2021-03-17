package programmers.disjoinset;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_지형_이동 {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int maxSize;
    private static boolean[][] visit;
    private static int[][] landTypeBoard;
    private static int landTypeCount = 0;

    public static void main(String[] args) {
        int[][] land = {{1, 4, 8, 10},
                {5, 5, 5, 5},
                {10, 10, 10, 10},
                {10, 10, 10, 20}};
        int height = 3;
        System.out.println(solution(land, height));
    }

    public static int solution(int[][] land, int height) {
        maxSize = land.length;
        visit = new boolean[maxSize][maxSize];
        landTypeBoard = new int[maxSize][maxSize];
        initAreaType(land, height);
        return getAnswer(land);
    }

    private static void initAreaType(int[][] land, int height) {
        for (int raw = 0; raw < maxSize; raw++) {
            for (int col = 0; col < maxSize; col++) {
                if (!visit[raw][col]) {
                    checkArea(raw, col, land, height, landTypeCount++);
                }
            }
        }
    }

    private static void checkArea(int startRaw, int startCol, int[][] land, int maxHeightOfMovable, int landType) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(new Node(startRaw, startCol));
        visit[startRaw][startCol] = true;
        landTypeBoard[startRaw][startCol] = landType;

        while (!nodeQueue.isEmpty()) {
            final Node curNode = nodeQueue.poll();
            int curLandHeight = land[curNode.raw][curNode.col];
            for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
                int nextRaw = curNode.raw + DX[dirIdx];
                int nextCol = curNode.col + DY[dirIdx];
                if (isRange(nextRaw, nextCol) && !visit[nextRaw][nextCol]
                        && isMovable(land[nextRaw][nextCol], curLandHeight, maxHeightOfMovable)) {
                    nodeQueue.add(new Node(nextRaw, nextCol));
                    visit[nextRaw][nextCol] = true;
                    landTypeBoard[nextRaw][nextCol] = landType;
                }
            }
        }
    }

    private static int getAnswer(int[][] land) {
        Queue<Group> groupQueue = new PriorityQueue<>();
        for (int raw = 0; raw < maxSize; raw++) {
            for (int col = 0; col < maxSize; col++) {
                findOtherGroup(raw, col, groupQueue, land);
            }
        }
        return getMinCost(groupQueue);
    }

    private static void findOtherGroup(int standardRaw, int standardCol, Queue<Group> groupQueue, int[][] land) {
        int standardLandType = landTypeBoard[standardRaw][standardCol];
        for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
            int nextRaw = standardRaw + DX[dirIdx];
            int nextCol = standardCol + DY[dirIdx];
            if (isRange(nextRaw, nextCol)) {
                int nextLandType = landTypeBoard[nextRaw][nextCol];
                if (standardLandType != nextLandType) {
                    int cost = Math.abs(land[standardRaw][standardCol] - land[nextRaw][nextCol]);
                    groupQueue.add(new Group(new Node(standardRaw, standardCol), new Node(nextRaw, nextCol), cost));
                }
            }
        }
    }

    private static int getMinCost(Queue<Group> groupQueue) {
        int cost = 0;
        int connectedCount = 1;

        boolean[] visitLand = new boolean[landTypeCount];
        while (landTypeCount != connectedCount) {
            final Group curGroup = groupQueue.poll();
            int firstGroup = curGroup.first.getType();
            int secondGroup = curGroup.second.getType();

            if (firstGroup != secondGroup && !visitLand[firstGroup] || !visitLand[secondGroup]) {
                union(curGroup.first, curGroup.second);
                visitLand[firstGroup] = true;
                visitLand[secondGroup] = true;
                cost += curGroup.cost;
                connectedCount++;
            }
        }
        return cost;
    }

    private static boolean isRange(int raw, int col) {
        return raw >= 0 && col >= 0 && raw < maxSize && col < maxSize;
    }

    private static boolean isMovable(int nextLandHeight, int curLandHeight, int maxHeightOfMovable) {
        return Math.abs(nextLandHeight - curLandHeight) <= maxHeightOfMovable;
    }

    private static void union(Node firstGroup, Node secondGroup) {
        int firstType = firstGroup.getType();
        int secondType = secondGroup.getType();

        if (firstType > secondType) {
            landTypeBoard[firstGroup.raw][firstGroup.col] = secondType;
        } else {
            landTypeBoard[secondGroup.raw][secondGroup.col] = firstType;
        }
    }

    private static class Node {
        private int raw;
        private int col;

        public Node(int raw, int col) {
            this.raw = raw;
            this.col = col;
        }

        public int getType() {
            return landTypeBoard[raw][col];
        }
    }

    private static class Group implements Comparable<Group> {
        private Node first;
        private Node second;
        private int cost;

        public Group(Node first, Node second, int cost) {
            this.first = first;
            this.second = second;
            this.cost = cost;
        }


        @Override
        public int compareTo(Group next) {
            return this.cost - next.cost;
        }
    }
}
