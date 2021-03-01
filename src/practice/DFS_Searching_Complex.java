package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS_Searching_Complex {

    private static Map<String, Integer> skillToIdxMap = new HashMap<>();
    private static Map<Integer, String> idxToSkillMap = new HashMap<>();
    private static List<Integer>[] linkSkills;

    public static void main(java.lang.String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] skills = br.readLine().split(" ");
        for (int idx = 0; idx < skills.length; idx++) {
            skillToIdxMap.put(skills[idx], idx);
            idxToSkillMap.put(idx, skills[idx]);
        }

        int skillCount = Integer.parseInt(br.readLine());
        linkSkills = new ArrayList[skills.length];
        for (int idx = 0; idx < skills.length; idx++) {
            linkSkills[idx] = new ArrayList<>();
        }

        Set<Integer> fromSkills = new HashSet<>();
        Set<Integer> toSkills = new HashSet<>();
        for (int idx = 0; idx < skillCount; idx++) {
            String[] skilled = br.readLine().split(" ");
            final Integer fromIdx = skillToIdxMap.get(skilled[0]);
            final Integer toIdx = skillToIdxMap.get(skilled[1]);
            linkSkills[fromIdx].add(toIdx);
            fromSkills.add(fromIdx);
            toSkills.add(toIdx);

        }

        for (Integer fromSkill : fromSkills) {
            if (toSkills.contains(fromSkill)) continue;
            goDFS(fromSkill, idxToSkillMap.get(fromSkill));
        }
    }

    private static void goDFS(Integer skillIdx, String linkSkill) {
        if (linkSkills[skillIdx].isEmpty()) {
            System.out.println(linkSkill);
        }

        for (int nextSkillIdx : linkSkills[skillIdx]) {
            String nextSkill = linkSkill + " " + idxToSkillMap.get(nextSkillIdx);
            goDFS(nextSkillIdx, nextSkill);
        }
    }
}
