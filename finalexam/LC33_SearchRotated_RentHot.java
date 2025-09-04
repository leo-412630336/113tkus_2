import java.util.Scanner;

public class LC33_SearchRotated_RentHot {

    /**
     * @param nums 旋轉過的升序陣列
     * @param target 欲搜尋的設備 ID
     * @return 設備 ID 的索引，若無則為 -1
     *
     * 時間複雜度: O(log n) - 每次循環都將搜尋範圍減半。
     * 空間複雜度: O(1) - 只使用了幾個額外指針。
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷左半部是否為有序區間
            if (nums[left] <= nums[mid]) {
                // 如果 target 在左半部有序區間內
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右半部為有序區間
                // 如果 target 在右半部有序區間內
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入設備數量 n 和目標 ID target，以空格分隔:");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個設備 ID（旋轉過的升序陣列），以空格分隔:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        scanner.close();

        int result = search(nums, target);
        
        if (result != -1) {
            System.out.println("設備 " + target + " 的索引為: " + result);
        } else {
            System.out.println("找不到設備 " + target + "。");
        }
    }
}