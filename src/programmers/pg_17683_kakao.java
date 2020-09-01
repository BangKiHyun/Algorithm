package programmers;

import java.util.*;

public class pg_17683_kakao {
    private static final int MAX_HOUR = 24;
    private static final int MAX_MINUTE = 60;

    private static final Map<String, String> CHANGE_LIST = initChangeMap();

    private static Map<String, String> initChangeMap() {
        Map<String, String> map = new HashMap<>();
        map.put("C#", "H");
        map.put("D#", "I");
        map.put("F#", "J");
        map.put("G#", "K");
        map.put("A#", "L");
        map.put("B#", "M");

        return map;
    }

    public static void main(String[] args) {
        String m = "A#";
        String[] musicinfos = {"13:00,13:02,HAPPY,B#A#", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));
    }

    private static String solution(String m, String[] musicinfos) {
        m = changeString(m);

        List<MusicInfo> musicInfos = new ArrayList<>();
        int idx = 0;
        for (String strMusicInfo : musicinfos) {
            MusicInfo musicInfo = setMusicInfo(strMusicInfo, idx);
            if (musicInfo.hasSheet(m)) {
                musicInfos.add(musicInfo);
            }
        }

        musicInfos.sort(new MusicComparator());

        return musicInfos.size() == 0 ? "(None)" : musicInfos.get(0).name;
    }

    private static MusicInfo setMusicInfo(final String musicInfo, int idx) {
        String[] split = musicInfo.split(",");
        int minute = getMinute(split[0], split[1]);

        String music = changeString(split[3]);
        music = getMusic(minute, music);

        return new MusicInfo(idx, minute, split[2], music);
    }

    private static int getMinute(final String start, final String end) {
        String[] hourAndMinute1 = start.split(":");
        String[] hourAndMinute2 = end.split(":");

        int hour = parseInt(hourAndMinute2[0]) - parseInt(hourAndMinute1[0]);
        int minute = parseInt(hourAndMinute2[1]) - parseInt(hourAndMinute1[1]);

        if (hour < 0) hour += MAX_HOUR;
        if (minute < 0) {
            hour--;
            minute += MAX_MINUTE;
        }

        return (hour * MAX_MINUTE) + minute;
    }

    private static String getMusic(int minute, final String sheet) {
        StringBuilder sb = new StringBuilder();
        int sheetLength = sheet.length();
        int mok = minute / sheetLength;
        int measure = minute % sheetLength;

        for (int i = 0; i < mok; i++) {
            sb.append(sheet);
        }

        sb.append(sheet, 0, measure);

        return String.valueOf(sb);
    }

    private static String changeString(String string) {
        for (String key : CHANGE_LIST.keySet()) {
            string = string.replaceAll(key, CHANGE_LIST.get(key));
        }
        return string;
    }

    private static int parseInt(final String number) {
        return Integer.parseInt(number);
    }

    private static class MusicInfo {
        int idx;
        int minute;
        String name;
        String sheet;

        public MusicInfo(final int idx, final int minute, final String name, final String sheet) {
            this.idx = idx;
            this.minute = minute;
            this.name = name;
            this.sheet = sheet;
        }

        public boolean hasSheet(final String m) {
            String compare = m + "#";

            String temp1 = sheet.replace(compare, "");
            if (temp1.length() != sheet.length()) {
                String temp2 = temp1.replace(m, "");
                if (temp1.length() != temp2.length()) return true;
            } else {
                temp1 = sheet.replace(m, "");
                if (temp1.length() != sheet.length()) return true;
            }

            return false;
        }
    }

    private static class MusicComparator implements Comparator<MusicInfo> {

        @Override
        public int compare(final MusicInfo o1, final MusicInfo o2) {
            if (o1.minute < o2.minute) return 1;
            else if (o1.minute == o2.minute) {
                if (o1.idx < o2.idx) return -1;
                else return 1;
            } else {
                return -1;
            }
        }
    }
}

//tc
//m,   musicinfos
//ABC, C#DEFGAB, ABCDEF