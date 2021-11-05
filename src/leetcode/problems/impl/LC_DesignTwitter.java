package leetcode.problems.impl;

import java.util.*;

public class LC_DesignTwitter {

    HashMap<Integer, HashSet<Integer>> followerMap = null;
    Deque<Integer[]> post = null;

    public LC_DesignTwitter() {
        followerMap = new HashMap<>();
        post = new ArrayDeque<>();
    }

    public void postTweet(int userId, int tweetId) {
        post.addFirst(new Integer[]{userId, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {

        Iterator<Integer[]> it = post.iterator();
        List<Integer> news = new ArrayList<>();
        HashSet<Integer> followee = null;

        if(followerMap.containsKey(userId)) {
            followee = followerMap.get(userId);
        } else {
            followee = new HashSet<>();
        }

        while(it.hasNext()) {
            if(news.size() == 10) {
                break;
            }
            Integer[] post = it.next();
            if(post[0] == userId || followee.contains(post[0])) {
                news.add(post[1]);
            }
        }
        return news;
    }

    public void follow(int followerId, int followeeId) {
        if(followerMap.containsKey(followerId)) {
            followerMap.get(followerId).add(followeeId);
        } else {
            HashSet<Integer> set = new HashSet<>();
            set.add(followerId);
            set.add(followeeId);
            followerMap.put(followerId, set);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if(followerId != followeeId) {
            if(followerMap.containsKey(followerId)) {
                followerMap.get(followerId).remove(followeeId);
            }
        }
    }
}
