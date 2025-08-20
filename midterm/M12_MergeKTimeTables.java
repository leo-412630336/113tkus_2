import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry {
        int time, listIdx, idx;
        Entry(int t, int l, int i) { time = t; listIdx = l; idx = i; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = sc.nextInt();
            lists.add(arr);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>((a,b) -> a.time - b.time);
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) pq.offer(new Entry(lists.get(i)[0], i, 0));
        }

        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            res.add(e.time);
            int[] curList = lists.get(e.listIdx);
            if (e.idx + 1 < curList.length) {
                pq.offer(new Entry(curList[e.idx + 1], e.listIdx, e.idx + 1));
            }
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i < res.size() - 1) System.out.print(" ");
        }
    }
}

/*
時間複雜度：O(N log K)
空間複雜度：O(K)
*/