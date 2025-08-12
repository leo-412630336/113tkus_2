import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) return 0;

        // 找所有上升段的利潤
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int buy = 0;

        while (buy < prices.length - 1) {
            // 找谷底
            while (buy < prices.length - 1 && prices[buy] >= prices[buy + 1]) buy++;
            int valley = prices[buy];

            // 找山頂
            while (buy < prices.length - 1 && prices[buy] <= prices[buy + 1]) buy++;
            int peak = prices[buy];

            if (peak > valley) {
                maxHeap.offer(peak - valley);
            }
        }

        // 從 Max Heap 取出前 k 筆利潤
        int profit = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
            k--;
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2,4,1}));           // 2
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3}));     // 7
        System.out.println(maxProfit(2, new int[]{1,2,3,4,5}));       // 4
    }
}