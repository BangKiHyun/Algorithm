package SW.D4;

import java.util.*;

public class sw_7792_시뮬 {
    private static String candidateName[];
    private static Set<Character> set;
    private static Queue<Candidate> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            candidateName = new String[n + 1];
            int alphabetCount = 0;

            for (int i = 0; i <= n; i++) {
                candidateName[i] = sc.nextLine();
                alphabetCount = getAlphabetCount(candidateName[i]);
                q.add(new Candidate(candidateName[i], alphabetCount));
            }

            System.out.println("#" + test_case + " " + findLeader());
        }
    }

    private static int getAlphabetCount(String candidateName) {
        candidateName = deleteBlank(candidateName);
        set = new HashSet<>();
        for (int i = 0; i < candidateName.length(); i++) {
            set.add(candidateName.charAt(i));
        }
        return set.size();
    }

    private static String deleteBlank(String name) {
        return name.replaceAll(" ", "");
    }

    private static String findLeader() {
        int max = -1;
        String leaderName = "";

        while (!q.isEmpty()) {
            Candidate candidate = q.poll();
            if (candidate.alphabetCount > max) {
                max = candidate.alphabetCount;
                leaderName = candidate.name;
            } else if (candidate.alphabetCount == max) {
                leaderName = compareLeaderAndCandidate(leaderName, candidate.name);
            }
        }
        return leaderName;
    }

    private static String compareLeaderAndCandidate(String leader, String candidate) {
        int len = getMinLengthOfLeaderAndCandidate(leader, candidate);

        for (int i = 0; i < len; i++) {
            if (leader.equals(candidate)) {
                continue;
            } else if (leader.charAt(i) > candidate.charAt(i)) {
                return leader;
            } else {
                return candidate;
            }
        }
        return "";
    }

    private static int getMinLengthOfLeaderAndCandidate(String leader, String candidate) {
        return Math.max(leader.length(), candidate.length());
    }

    private static class Candidate {
        String name;
        int alphabetCount;

        Candidate(String name, int alphabetCount) {
            this.name = name;
            this.alphabetCount = alphabetCount;
        }
    }
}
