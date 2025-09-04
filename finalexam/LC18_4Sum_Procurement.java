import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC18_4Sum_Procurement {

    /**
     * @param nums 物資價格陣列
     * @param target 預算總額
     * @return 所有和為 target 的不重複四元組
     *
     * 時間複雜度: O(n^3) - 排序為 O(n log n)，外層兩層迴圈為 O(n^2)，內層雙指針為 O(n)。
     * 空間複雜度: O(log n) to O(n) - 排序所需的空間，或不計入結果列表則為 O(1)。
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // 邊界檢查
        if (n < 4) {
            return result;
        }

        // 步驟 1: 排序
        Arrays.sort(nums);

        // 步驟 2: 外層雙重迴圈，固定前兩個數
        for (int i = 0; i < n - 3; i++) {
            // 跳過重複的 i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 跳過重複的 j
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 步驟 3: 內層雙指針尋找另外兩個數
                int left = j + 1;
                int right = n - 1;
                long requiredSum = (long) target - nums[i] - nums[j];

                while (left < right) {
                    long sum = (long) nums[left] + nums[right];
                    if (sum == requiredSum) {
                        // 找到一組解
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // 跳過重複的 left 和 right
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        
                        left++;
                        right--;
                    } else if (sum < requiredSum) {
                        left++;
                    } else { // sum > requiredSum
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入物資數量 n 和預算 target，以空格分隔:");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個物資價格，以空格分隔:");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        scanner.close();

        List<List<Integer>> result = fourSum(nums, target);
        
        if (result.isEmpty()) {
            System.out.println("未找到任何符合預算的四元組。");
        } else {
            System.out.println("所有符合預算的四元組如下:");
            for (List<Integer> quadruplet : result) {
                for (int num : quadruplet) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
}