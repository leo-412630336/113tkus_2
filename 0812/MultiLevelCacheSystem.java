import java.util.*;

public class MultiLevelCacheSystem {

    static class CacheNode {
        int key;
        String value;
        int freq;
        long timestamp;
        int cost;

        CacheNode(int key, String value, int cost) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
            this.cost = cost;
        }

        double score() {
            return (double) freq / cost;
        }
    }

    static class CacheLevel {
        int capacity;
        int cost;
        Map<Integer, CacheNode> map;
        PriorityQueue<CacheNode> pq; // min-heap by score, then timestamp

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.pq = new PriorityQueue<>((a, b) -> {
                int cmp = Double.compare(a.score(), b.score());
                if (cmp == 0) return Long.compare(a.timestamp, b.timestamp);
                return cmp;
            });
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        void add(CacheNode node) {
            map.put(node.key, node);
            pq.offer(node);
        }

        CacheNode removeLowest() {
            CacheNode lowest = pq.poll();
            if (lowest != null) map.remove(lowest.key);
            return lowest;
        }

        void remove(int key) {
            CacheNode node = map.remove(key);
            if (node != null) pq.remove(node);
        }
    }

    CacheLevel L1, L2, L3;

    public MultiLevelCacheSystem() {
        L1 = new CacheLevel(2, 1);
        L2 = new CacheLevel(5, 3);
        L3 = new CacheLevel(10, 10);
    }

    public String get(int key) {
        CacheNode node = findNode(key);
        if (node == null) return null;
        node.freq++;
        node.timestamp = System.nanoTime();
        updatePQ(node);
        tryPromote(node);
        return node.value;
    }

    public void put(int key, String value) {
        CacheNode node = findNode(key);
        if (node != null) {
            node.value = value;
            node.freq++;
            node.timestamp = System.nanoTime();
            updatePQ(node);
            tryPromote(node);
            return;
        }

        // 新節點放在 L1
        CacheNode newNode = new CacheNode(key, value, L1.cost);
        if (L1.isFull()) demote(L1, newNode);
        else L1.add(newNode);
    }

    private CacheNode findNode(int key) {
        if (L1.map.containsKey(key)) return L1.map.get(key);
        if (L2.map.containsKey(key)) return L2.map.get(key);
        if (L3.map.containsKey(key)) return L3.map.get(key);
        return null;
    }

    private void updatePQ(CacheNode node) {
        if (L1.map.containsKey(node.key)) {
            L1.pq.remove(node);
            L1.pq.offer(node);
        } else if (L2.map.containsKey(node.key)) {
            L2.pq.remove(node);
            L2.pq.offer(node);
        } else if (L3.map.containsKey(node.key)) {
            L3.pq.remove(node);
            L3.pq.offer(node);
        }
    }

    private void tryPromote(CacheNode node) {
        if (L3.map.containsKey(node.key) && node.score() > lowestScore(L2)) {
            moveNode(L3, L2, node);
        }
        if (L2.map.containsKey(node.key) && node.score() > lowestScore(L1)) {
            moveNode(L2, L1, node);
        }
    }

    private double lowestScore(CacheLevel level) {
        if (level.pq.isEmpty()) return -1;
        return level.pq.peek().score();
    }

    private void moveNode(CacheLevel from, CacheLevel to, CacheNode node) {
        from.remove(node.key);
        node.cost = to.cost;
        if (to.isFull()) {
            CacheNode lowest = to.removeLowest();
            if (to == L1) demote(L1, lowest);
            else if (to == L2) demote(L2, lowest);
        }
        to.add(node);
    }

    private void demote(CacheLevel from, CacheNode node) {
        if (from == L1) {
            node.cost = L2.cost;
            if (L2.isFull()) demote(L2, L2.removeLowest());
            L2.add(node);
        } else if (from == L2) {
            node.cost = L3.cost;
            if (L3.isFull()) L3.removeLowest(); // 最後一層直接丟掉
            L3.add(node);
        }
    }

    public void printState() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
        System.out.println("------");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printState(); // L1: [2,3], L2: [1], L3: []

        cache.get(1); cache.get(1); cache.get(2);
        cache.printState(); // 1 應該移到 L1

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printState();
    }
}