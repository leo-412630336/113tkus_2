import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int index;

        Item(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<Item> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty;
            return b.index - a.index;
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item item = new Item(name, qty, i);

            if (minHeap.size() < K) {
                minHeap.offer(item);
            } else {
                Item top = minHeap.peek();
                if (top.qty < item.qty ||
                   (top.qty == item.qty && top.index > item.index)) {
                    minHeap.poll();
                    minHeap.offer(item);
                }
            }
        }

        List<Item> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.index - b.index;
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
時間複雜度：
輸入 n 筆資料，維護大小 K 的最小堆 → O(n log K)
最後輸出排序 K 筆 → O(K log K)
總時間複雜度：O(n log K)
空間複雜度：O(K)
*/