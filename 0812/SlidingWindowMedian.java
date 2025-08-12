import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 存較小的一半（最大堆）
    private PriorityQueue<Integer> minHeap; // 存較大的一半（最小堆）

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> medians = new ArrayList<>();
        Map<Integer, Integer> delayed = new HashMap<>(); // 延遲刪除表

        for (int i = 0; i < nums.length; i++) {
            // 加入新元素
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }

            // 平衡兩個堆的大小
            balanceHeaps();

            // 當視窗滿了
            if (i >= k - 1) {
                // 計算中位數
                if (k % 2 == 1) {
                    medians.add((double) maxHeap.peek());
                } else {
                    medians.add(((double) maxHeap.peek() + minHeap.peek()) / 2.0);
                }

                // 移除離開視窗的元素
                int outNum = nums[i - k + 1];
                delayed.put(outNum, delayed.getOrDefault(outNum, 0) + 1);

                // 從對應堆中減少大小
                if (outNum <= maxHeap.peek()) {
                    prune(maxHeap, delayed);
                } else {
                    prune(minHeap, delayed);
                }

                // 平衡兩個堆
                balanceHeaps();
            }
        }

        // 轉成 double[]
        double[] result = new double[medians.size()];
        for (int i = 0; i < medians.size(); i++) {
            result[i] = medians.get(i);
        }
        return result;
    }

    // 平衡兩個堆的大小
    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // 移除延遲刪除的元素
    private void prune(PriorityQueue<Integer> heap, Map<Integer, Integer> delayed) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] arr1 = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] res1 = swm.medianSlidingWindow(arr1, 3);
        System.out.println(Arrays.toString(res1)); // [1, -1, -1, 3, 5, 6]

        int[] arr2 = {1, 2, 3, 4};
        double[] res2 = swm.medianSlidingWindow(arr2, 2);
        System.out.println(Arrays.toString(res2)); // [1.5, 2.5, 3.5]
    }
}