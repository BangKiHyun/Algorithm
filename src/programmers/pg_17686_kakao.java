package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class pg_17686_kakao {
    public static void main(String[] args) {
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat", "F-15"};

        for (String s : solution(files)) {
            System.out.println(s);
        }
    }

    private static String[] solution(String[] files) {
        ArrayList<File> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            int len = files[i].length();
            String head = getHead(files[i]);
            String number = getNumber(files[i].substring(head.length(), len));
            String tail = getTail(files[i], head.length() + number.length(), len);

            list.add(new File(head, number, tail));
        }

        Collections.sort(list, new FileComparator());

        String[] ans = new String[files.length];
        int idx = 0;
        for (File i : list) {
            if (i.tail == null) ans[idx] = i.head + i.number;
            else ans[idx] = i.head + i.number + i.tail;
            idx++;
        }

        return ans;
    }

    private static String getHead(String file) {
        for (int i = 0; i < file.length(); i++) {
            if (isNumber(file.charAt(i))) return file.substring(0, i);
        }
        return "";
    }

    private static String getNumber(String file) {
        int numberCnt = 0;
        for (int i = 0; i < file.length(); i++) {
            if (!isNumber(file.charAt(i)) || numberCnt == 5) return file.substring(0, i);
            numberCnt++;
        }
        return file;
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static String getTail(String file, int start, int end) {
        return file.substring(start, end);
    }


    private static class File {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }

    private static class FileComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            String firstHead = o1.head.toUpperCase();
            String secondHead = o2.head.toUpperCase();

            if (firstHead.compareTo(secondHead) < 0) return -1;
            else if (firstHead.compareTo(secondHead) == 0) {
                if (Integer.parseInt(o1.number) < Integer.parseInt(o2.number)) return -1;
                else if (Integer.parseInt(o1.number) == Integer.parseInt(o2.number)) return 0;
                else return 1;
            } else return 1;
        }
    }
}

//TC
//{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
//{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat", "F-15"};
