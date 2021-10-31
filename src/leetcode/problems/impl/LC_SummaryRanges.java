package leetcode.problems.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class LC_SummaryRanges {
    TreeMap<Integer, Integer> intervals = new TreeMap<>();
    HashMap<Integer, Integer> reverseIntervals = new HashMap<>();

    public void addNum(int val) {
        if(this.intervals.size() == 0) {
            this.intervals.put(val, val);
            this.reverseIntervals.put(val, val);
            return;
        }
        Iterator<Integer> it = this.intervals.keySet().iterator();
        while(it.hasNext()) {
            int next = it.next();
            if(val >= next && val <= this.intervals.get(next)) {
                return;
            }
        }
        if(this.intervals.containsKey(val + 1) && this.reverseIntervals.containsKey(val - 1)) {
            int start = this.reverseIntervals.get(val - 1);
            int end = this.intervals.get(val + 1);
            this.intervals.remove(this.reverseIntervals.get(val - 1));
            this.reverseIntervals.remove(this.intervals.get(val + 1));
            this.intervals.remove(val + 1);
            this.reverseIntervals.remove(val - 1);
            this.intervals.put(start, end);
            this.reverseIntervals.put(end, start);
        }
        else if(this.intervals.containsKey(val + 1)) {
            int start = val;
            int end = this.intervals.get(val + 1);
            this.intervals.remove(val + 1);
            this.intervals.put(start, end);
            this.reverseIntervals.put(end, start);;
        }
        else if(this.reverseIntervals.containsKey(val - 1)) {
            int start = this.reverseIntervals.get(val - 1);
            int end = val;
            this.intervals.put(start, end);
            this.reverseIntervals.remove(val - 1);
            this.reverseIntervals.put(end, start);
        }
        else {
            this.intervals.put(val, val);
            this.reverseIntervals.put(val, val);
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[this.intervals.size()][2];
        Iterator<Integer> it = this.intervals.keySet().iterator();
        int i = 0;
        while(it.hasNext()) {
            int next = it.next();
            result[i][0] = next;
            result[i][1] = this.intervals.get(next);
            i++;
        }
        return result;
    }
}
