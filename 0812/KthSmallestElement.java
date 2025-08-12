import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    // 方法 1：大小為 K 的 Max Heap
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 大頂堆

        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大的，確保 heap 大小為 k
            }
        }
        return maxHeap.peek(); // Heap 頂端即第 k 小
    }

    // 方法 2：Min Heap 提取 K 次
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 小頂堆
        for (int num : arr) {
            minHeap.add(num);
        }

        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll(); // 取出最小值 k 次
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];

            System.out.println("陣列：" + java.util.Arrays.toString(arr) + ", K=" + k);
            int result1 = kthSmallestMaxHeap(arr, k);
            int result2 = kthSmallestMinHeap(arr, k);

            System.out.println("方法 1 (Max Heap) → 第 " + k + " 小元素：" + result1);
            System.out.println("方法 2 (Min Heap) → 第 " + k + " 小元素：" + result2);
            System.out.println();
        }
    }
}
