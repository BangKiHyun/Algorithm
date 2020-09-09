package programmers;

import java.util.*;

public class pg_42893_kakao {
    private static final String URL_DELIMITER = "<meta property=\"og:url\" content=\"";
    private static final String LINK_DELIMITER = "<a href=\"";

    private static List<Page> pageList = new ArrayList<>();

    public static void main(String[] args) {
        String word = "muzi";
//        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(solution(word, pages));
    }

    private static int solution(String word, String[] pages) {
        word = word.toLowerCase();

        int idx = 0;
        for (String page : pages) {
            page = page.toLowerCase();
            String url = getUrl(page);
            double point = getPoint(page, word);
            List<String> links = getLinkPage(page);

            System.out.println(url + " " + point + " " + links.size());

            Page pageEntity = new Page(idx++, url, point, links);
            pageList.add(pageEntity);
        }

        for (Page page : pageList) {
            page.updateLinkPage();
        }
        Collections.sort(pageList);
        return pageList.get(0).idx;
    }

    private static String getUrl(final String page) {
        int startIdx = page.indexOf(URL_DELIMITER) + URL_DELIMITER.length();
        int endIdx = page.indexOf("\"", startIdx);
        return page.substring(startIdx, endIdx);
    }

    private static double getPoint(final String page, final String word) {
        double matchingPoint = 0;

        String newPage = page.substring(page.indexOf("<body>"), page.indexOf("</body>"));
        int startIdx = newPage.indexOf(word);
        while (startIdx != -1) {
            char first = newPage.charAt(startIdx - 1);
            char last = newPage.charAt(startIdx + word.length());

            if (!Character.isLowerCase(first) && !Character.isLowerCase(last)) {
                matchingPoint++;
            }
            startIdx = newPage.indexOf(word, startIdx + 1);
        }

        return matchingPoint;
    }

    private static List<String> getLinkPage(final String page) {
        List<String> links = new ArrayList<>();

        String temp = page;
        while (temp.contains(LINK_DELIMITER)) {
            int startIdx = temp.indexOf(LINK_DELIMITER) + LINK_DELIMITER.length();
            int endIdx = temp.indexOf("\"", startIdx);
            links.add(temp.substring(startIdx, endIdx));
            temp = temp.substring(endIdx, temp.length());
        }

        return links;
    }

    private static class Page implements Comparable<Page> {
        private final int idx;
        private final String url;
        private final double point;
        private final List<String> linkList;
        private double totalPoint;

        public Page(final int idx, final String url, final double point, final List<String> linkList) {
            this.idx = idx;
            this.url = url;
            this.point = point;
            this.linkList = linkList;
            totalPoint = 0;
        }

        public void updateLinkPage() {
            for (String link : linkList) {
                Page findPage = findPageBy(link);
                if (findPage != null) {
                    findPage.totalPoint += point / linkList.size();

                }
            }
        }

        private Page findPageBy(final String link) {
            for (Page page : pageList) {
                if (page.isEqualsOf(link)) {
                    return page;
                }
            }
            return null;
        }

        private boolean isEqualsOf(final String link) {
            return this.url.equals(link);
        }

        @Override
        public int compareTo(final Page o) {
            return Double.compare((o.totalPoint + o.point), (this.totalPoint + this.point));
        }
    }
}
