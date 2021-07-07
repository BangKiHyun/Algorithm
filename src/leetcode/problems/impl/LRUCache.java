package leetcode.problems.impl;

import java.util.LinkedHashMap;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> linkedHashMap;
    private int cap;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        if (!linkedHashMap.containsKey(key)) return -1;
        int value = linkedHashMap.get(key);
        linkedHashMap.remove(key);
        linkedHashMap.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (linkedHashMap.containsKey(key)) { // 키가 존재하면 키 값 update
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
        } else if (cap > 0) { // 키가 존재하지 않고 수용할 수 있다면 키 추가
            linkedHashMap.put(key, value);
            cap--;
        } else { // 수용할 수 없으면 가장 최근에 사용한 키 제거
            linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
            linkedHashMap.put(key, value);
        }
    }
}
