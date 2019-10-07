package programmers;

import java.util.ArrayList;

public class pg_49993_Topology {
    static ArrayList<Integer>[] list;

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        int res = solution(skill, skill_trees);
        System.out.println(res);
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;

        for (int i = 0; i < skill_trees.length; i++) {
            boolean check = true;
            int cnt = 0;
            String[] skills = skill_trees[i].split("");

            for (int j = 0; j < skills.length; j++) {
                if (cnt < skill.indexOf(skills[j])) {
                    check = false;
                    break;
                } else if (cnt == skill.indexOf(skills[j])) cnt++;
            }
            if (!check) {
                answer--;
            }
        }
        return answer;
    }

}
