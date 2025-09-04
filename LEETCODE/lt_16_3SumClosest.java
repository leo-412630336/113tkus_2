import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);
        int n = nums.length;
        // 初始化答案為前三個數字的和
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果比之前的 closer，更新答案
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                // 根據 sum 和 target 的大小移動指針
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // sum == target，直接回傳，因為不可能比這更接近
                    return sum;
                }
            }
        }

        return closestSum;
    }
}