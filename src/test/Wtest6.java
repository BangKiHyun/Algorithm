package test;

import java.util.*;

public class Wtest6 {
    public static void main(String[] args) {
        String[][] forms = {{"jm@email.com", "제이엠"}, {"jason@email.com", "제이슨"}, {"woniee@email.com", "워니"}, {"mj@email.com", "엠제이"}};
        for (String ans : solution(forms)) {
            System.out.print(ans + " ");
        }
    }

    public static String[] solution(String[][] forms) {
        Set<String> emailSet = new TreeSet<>();
        List<Crew> crews = new ArrayList<>();
        initCrew(crews, forms);

        for (Crew crew : crews) {
            for (Crew targetCrew : crews) {
                if (targetCrew.equals(crew)) {
                    continue;
                }
                if (targetCrew.isDuplicateName(crew.name)) {
                    emailSet.add(targetCrew.email);
                }
            }
        }

        return convertStringArray(emailSet);
    }

    private static void initCrew(List<Crew> crews, String[][] forms) {
        Arrays.stream(forms)
                .map(form -> new Crew(form[0], form[1]))
                .forEach(crews::add);
    }

    private static String[] convertStringArray(Set<String> set) {
        String[] emails = new String[set.size()];
        int idx = 0;
        for (String email : set) {
            emails[idx++] = email;
        }

        return emails;
    }

    private static class Crew {

        private String email;
        private String name;

        public Crew(String email, String name) {
            this.email = email;
            this.name = name;
        }

        public boolean isDuplicateName(String name) {
            for (int i = 0; i < this.name.length() - 1; i++) {
                String targetName = this.name.substring(i, i + 2);
                for (int j = 0; j < name.length() - 1; j++) {
                    String standardName = name.substring(j, j + 2);
                    if (targetName.equals(standardName)) {
                        return true;
                    }
                }
            }

            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Crew)) return false;
            Crew crew = (Crew) o;
            return Objects.equals(email, crew.email) &&
                    Objects.equals(name, crew.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(email, name);
        }
    }
}
