import java.util.Scanner;

public class LC34_SearchRange_DelaySpan {

    /**
     * @param nums 延誤等級陣列（已排序）
     * @param target 欲搜尋的延誤等級
     * @return 包含首尾索引的陣列，若不存在則為 {-1, -1}
     *
     * 時間複雜度: O(log n) - 雖然呼叫了兩次，但每次都是對數時間。
     * 空間複雜度: O(1) - 只使用了幾個額外變數。
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        // 尋找左邊界
        result[0] = findBound(nums, target, true);

        // 如果左邊界未找到，則直接返回
        if (result[0] == -1) {
            return result;
        }

        // 尋找右邊界
        result[1] = findBound(nums, target, false);

        return result;
    }

    /**
     * 輔助函式：使用二分搜尋法尋找左右邊界
     *
     * @param nums 陣列
     * @param target 目標值
     * @param findFirst 是否尋找第一個出現的位置
     * @return 找到的索引，若無則為 -1
     */
    private static int findBound(int[] nums, int target, boolean findFirst) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid; // 暫存可能的結果
                if (findFirst) {
                    // 尋找左邊界，繼續向左搜尋
                    right = mid - 1;
                } else {
                    // 尋找右邊界，繼續向右搜尋
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入事件總數 n 和目標延誤等級 target，以空格分隔:");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個已排序的等級值，以空格分隔:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        scanner.close();

        int[] result = searchRange(nums, target);
        
        System.out.println("延誤等級 " + target + " 的出現區間為: " + result[0] + " " + result[1]);
    }
}