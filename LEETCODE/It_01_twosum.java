import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] It_01_twosum(int[] nums, int target) {
        // 使用 HashMap 儲存「數值 -> 索引」
        Map<Integer, Integer> map = new HashMap<>();

        // 遍歷陣列
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 需要的另一個數字

            // 如果 map 已經有 complement，就找到答案
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // 把當前數字存入 map
            map.put(nums[i], i);
        }

        // 題目保證一定有解，這行理論上不會執行
        return new int[]{-1, -1};
    }
}