package programmers.regex;

public class pg_신규_아이디_추천 {

    public static void main(String[] args) {
        String new_id = "abcdefghijklmn.p";
        //"123_.def";
        //"=.=";
        //"z-+.^.";
        //"...!@BaT#*..y.abcdefghijklm";
        System.out.println(solution(new_id));
    }

    public static String solution(String new_id) {
        String recommendedId = new_id.toLowerCase();
        recommendedId = recommendedId.replaceAll("[^-_.a-z0-9]", "");
        recommendedId = recommendedId.replaceAll("[.]{2,}", ".");
        recommendedId = recommendedId.replaceAll("^[.]|[.]$", "");

        if (recommendedId.isEmpty()) {
            recommendedId = "a";
        }

        if (recommendedId.length() >= 16) {
            recommendedId = recommendedId.substring(0, 15);
            recommendedId = recommendedId.replaceAll("[.]$", "");
        }

        if (recommendedId.length() <= 2) {
            while (recommendedId.length() < 3) {
                recommendedId += recommendedId.charAt(recommendedId.length() - 1);
            }
        }
        return recommendedId;
    }
}
