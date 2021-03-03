package programmers.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class pg_후보키 {
    private static int attributeSize;
    private static int rawSize;
    private static List<HashSet<Integer>> candidateKeySet = new ArrayList<>();

    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};
        System.out.println(solution(relation));
    }

    public static int solution(String[][] relation) {
        attributeSize = relation[0].length;
        rawSize = relation.length;

        for (int size = 1; size <= attributeSize; size++) {
            findCandidateKey(0, size, 0, new HashSet<>(), relation);
        }

        return candidateKeySet.size();
    }

    private static void findCandidateKey(int startIdx, int size, int depth, HashSet<Integer> maybeCandidateKey, String[][] relation) {
        if (size == depth) {
            if (alreadyContainsCandidateKey(maybeCandidateKey)) return;

            if (isUniqueness(maybeCandidateKey, relation)) {
                candidateKeySet.add(maybeCandidateKey);
                return;
            }
        }

        for (int attributeIdx = startIdx; attributeIdx < attributeSize; attributeIdx++) {
            HashSet<Integer> newCandidateKey = new HashSet<>(maybeCandidateKey);
            maybeCandidateKey.add(attributeIdx);
            findCandidateKey(startIdx + 1, size, depth + 1, newCandidateKey, relation);
        }
    }

    private static boolean alreadyContainsCandidateKey(HashSet<Integer> maybeCandidateKey) {
        for (Set<Integer> keySet : candidateKeySet) {
            if (maybeCandidateKey.containsAll(keySet)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUniqueness(HashSet<Integer> maybeCandidateKey, String[][] relation) {
        Set<String> tupleSet = new HashSet<>();
        for (int rawidx = 0; rawidx < rawSize; rawidx++) {
            StringBuilder tuple = new StringBuilder();
            for (int attributeIdx : maybeCandidateKey) {
                tuple.append(relation[rawidx][attributeIdx]);
            }

            String strTuple = String.valueOf(tuple);
            if (tupleSet.contains(strTuple)) {
                return false;
            }
            tupleSet.add(strTuple);
        }
        return true;
    }
}
