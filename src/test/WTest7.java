package test;

import java.util.*;
import java.util.stream.Collectors;

public class WTest7 {
    public static void main(String[] args) {
        String user = "mrko";
        String[][] friends = {{"donut", "andole"}, {"donut", "jun"}, {"donut", "mrko"},
                {"shakevan", "andole"}, {"shakevan", "jun"}, {"shakevan", "mrko"}};
        String[] visitor = {"bedi", "bedi", "donut", "bedi", "shakevan"};

        String[] answers = solution(user, friends, visitor);

        for (String answer : answers)
            System.out.print(answer + " ");
    }

    public static String[] solution(String user, String[][] friends, String[] visitors) {
        List<String> realFriendName = new ArrayList<>();
        for (String[] friend : friends) {
            String firstFriend = friend[0];
            String secondFriend = friend[1];
            if (user.equals(firstFriend)) {
                realFriendName.add(secondFriend);
            } else if (user.equals(secondFriend)) {
                realFriendName.add(firstFriend);
            }
        }

        Map<String, Integer> friendMap = new HashMap<>();
        for (String[] friend : friends) {
            String firstFriend = friend[0];
            String secondFriend = friend[1];
            if (realFriendName.contains(firstFriend) && !secondFriend.equals(user)) {
                if (friendMap.containsKey(secondFriend)) {
                    friendMap.put(secondFriend, friendMap.get(secondFriend) + 10);
                } else {
                    friendMap.put(secondFriend, 10);
                }
            }else if(realFriendName.contains(secondFriend) && !firstFriend.equals(user)){
                if (friendMap.containsKey(firstFriend)) {
                    friendMap.put(firstFriend, friendMap.get(firstFriend) + 10);
                } else {
                    friendMap.put(firstFriend, 10);
                }
            }
        }

        for (String visitor : visitors) {
            if (!realFriendName.contains(visitor)) {
                if (friendMap.containsKey(visitor)) {
                    friendMap.put(visitor, friendMap.get(visitor) + 1);
                } else {
                    friendMap.put(visitor, 1);
                }
            }
        }

        List<Friend> friendList = friendMap.keySet().stream()
                .map(name -> new Friend(name, friendMap.get(name)))
                .sorted(new FriendComparator())
                .collect(Collectors.toList());

        return convertStringArray(friendList);
    }

    private static String[] convertStringArray(List<Friend> friendList) {
        String[] friends = new String[friendList.size()];
        int idx = 0;
        for (Friend friend : friendList) {
            friends[idx++] = friend.name;
        }
        return friends;
    }

    private static class Friend {
        private String name;
        private int score;

        public Friend(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Friend)) return false;
            Friend friend = (Friend) o;
            return Objects.equals(name, friend.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class FriendComparator implements Comparator<Friend> {
        @Override
        public int compare(Friend o1, Friend o2) {
            if (o1.score - o2.score > 0) {
                return -1;
            } else if (o1.score == o2.score) {
                return o1.name.compareTo(o2.name);
            }
            return 0;
        }
    }
}
