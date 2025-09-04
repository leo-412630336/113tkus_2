import java.text.DecimalFormat;
import java.util.Scanner;

public class LC04_Median_QuakeFeeds {

    /**
     * @param nums1 第一個已排序數列
     * @param nums2 第二個已排序數列
     * @return 兩個數列合併後的中位數
     *
     * 時間複雜度: O(log(min(n, m))) - 在較短陣列上進行二分搜尋。
     * 空間複雜度: O(1) - 只使用了幾個變數。
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的陣列，以優化二分搜尋的效率
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n = nums1.length;
        int m = nums2.length;
        int low = 0;
        int high = n;
        int halfLen = (n + m + 1) / 2;

        while (low <= high) {
            int i = low + (high - low) / 2; // nums1 的切割位置
            int j = halfLen - i;             // nums2 的切割位置

            // 定義四個邊界值
            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = (i == n) ? Integer.MAX_VALUE : nums1[i];

            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = (j == m) ? Integer.MAX_VALUE : nums2[j];

            // 判斷切割是否正確
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // 找到了完美的切割位置
                if ((n + m) % 2 == 1) {
                    // 總長度為奇數，中位數為左半邊的最大值
                    return Math.max(maxLeftA, maxLeftB);
                } else {
                    // 總長度為偶數，中位數為中間兩個數的平均
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                }
            } else if (maxLeftA > minRightB) {
                // nums1 的左半邊過大，需要將 i 向左移動
                high = i - 1;
            } else { // maxLeftB > minRightA
                // nums2 的左半邊過大，需要將 i 向右移動
                low = i + 1;
            }
        }
        return -1.0; // 正常情況下不會到達這裡
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入第一個數列的長度 n 和第二個數列的長度 m，以空格分隔:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        int[] nums1 = new int[n];
        System.out.println("請輸入 " + n + " 個已排序的整數，以空格分隔:");
        for (int i = 0; i < n; i++) {
            nums1[i] = scanner.nextInt();
        }
        
        int[] nums2 = new int[m];
        System.out.println("請輸入 " + m + " 個已排序的整數，以空格分隔:");
        for (int i = 0; i < m; i++) {
            nums2[i] = scanner.nextInt();
        }
        
        scanner.close();

        double median = findMedianSortedArrays(nums1, nums2);
        
        // 格式化輸出，保留一位小數
        DecimalFormat df = new DecimalFormat("#.#");
        System.out.println("兩個數列合併後的中位數為: " + df.format(median));
    }
}