package programmers.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pg_매칭_점수 {

    private static final String URL_DELIMITER = "<meta property=\"og:url\" content=\"";
    private static final String EXTERNAL_LINK_DELIMITER = "<a href=\"";
    private static final String BODY_BEGIN_DELIMITER = "<body>";
    private static final String BODY_END_DELIMITER = "</body>";

    private static List<Page> pageList = new ArrayList<>();

    public static void main(String[] args) {
        String word = "muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(solution(word, pages));
    }

    public static int solution(String word, String[] pages) {
        int idx = 0;

        word = word.toLowerCase();
        for (String page : pages) {
            page = page.toLowerCase();

            String url = getUrl(page);
            int matchingPoint = getMatchingPoint(page, word);
            List<String> externalLinkList = getExternalLinkList(page);

            pageList.add(new Page(idx++, url, matchingPoint, externalLinkList));
        }

        pageList.forEach(Page::updateTotalPoint);

        for(Page page: pageList){
            System.out.println(page.idx + " " + page.totalPoint);
        }
        Collections.sort(pageList);

        return pageList.get(0).idx;
    }

    private static String getUrl(String page) {
        int beginIdx = page.indexOf(URL_DELIMITER) + URL_DELIMITER.length();
        int endIdx = page.indexOf("\"", beginIdx);
        return page.substring(beginIdx, endIdx);
    }

    private static int getMatchingPoint(String page, String delimiter) {
        String body = page.substring(
                page.indexOf(BODY_BEGIN_DELIMITER), page.indexOf(BODY_END_DELIMITER));

        int matchingPoint = 0;
        int beginIdx = body.indexOf(delimiter);
        while (beginIdx != -1) {
            char first = body.charAt(beginIdx - 1);
            char last = body.charAt(beginIdx + delimiter.length());
            if (!Character.isLowerCase(first) && !Character.isLowerCase(last)) {
                matchingPoint++;
            }
            beginIdx = body.indexOf(delimiter, beginIdx + 1);
        }

        return matchingPoint;
    }

    private static List<String> getExternalLinkList(String page) {
        List<String> externalLinkList = new ArrayList<>();
        while (page.contains(EXTERNAL_LINK_DELIMITER)) {
            int beginIdx = page.indexOf(EXTERNAL_LINK_DELIMITER) + EXTERNAL_LINK_DELIMITER.length();
            int endIdx = page.indexOf("\"", beginIdx);
            externalLinkList.add(page.substring(beginIdx, endIdx));

            page = page.substring(endIdx);
        }
        return externalLinkList;
    }

    private static class Page implements Comparable<Page> {
        private int idx;
        private String url;
        private double mathcingPoint;
        private List<String> externalLinkList;
        private double totalPoint;

        public Page(int idx, String url, int mathcingPoint, List<String> externalLinkList) {
            this.idx = idx;
            this.url = url;
            this.mathcingPoint = mathcingPoint;
            this.externalLinkList = externalLinkList;
            totalPoint = mathcingPoint;
        }

        public void updateTotalPoint() {
            for (String externalLink : externalLinkList) {
                Page externalPage = findPageByUrl(externalLink);
                if (externalPage != null) {
                    externalPage.totalPoint += (this.mathcingPoint / this.externalLinkList.size());
                }
            }
        }

        private Page findPageByUrl(String url) {
            for (Page page : pageList) {
                if (page.equalsUrl(url)) {
                    return page;
                }
            }
            return null;
        }

        private boolean equalsUrl(String url) {
            return this.url.equals(url);
        }

        @Override
        public int compareTo(Page o) {
            return Double.compare(o.totalPoint, this.totalPoint);
        }
    }
}
