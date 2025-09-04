import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC15_3Sum_THSRStops {

    /**
     * @param nums 站點票務調整量陣列
     * @return 所有和為 0 的不重複三元組
     *
     * 時間複雜度: O(n^2) - 排序為 O(n log n)，主迴圈為 O(n)，內部雙指針為 O(n)，總和為 O(n^2)。
     * 空間複雜度: O(log n) to O(n) - 取決於排序演算法的空間複雜度，或不計入結果列表則為 O(1)。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // 邊界檢查
        if (n < 3) {
            return result;
        }

        // 步驟 1: 排序
        Arrays.sort(nums);

        // 步驟 2: 遍歷並固定一個元素
        for (int i = 0; i < n - 2; i++) {
            // 提前終止：如果 nums[i] > 0，後續不可能有解
            if (nums[i] > 0) {
                break;
            }
            // 跳過重複的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 步驟 3: 雙指針尋找另外兩個元素
            int left = i + 1;
            int right = n - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    // 找到一組解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳過重複元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else { // sum > target
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入站點數量 n:");
        int n = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個票務調整量，以空格分隔:");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        scanner.close();

        List<List<Integer>> result = threeSum(nums);
        
        if (result.isEmpty()) {
            System.out.println("未找到任何組合。");
        } else {
            System.out.println("所有和為 0 的三元組如下:");
            for (List<Integer> triplet : result) {
                for (int num : triplet) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
}