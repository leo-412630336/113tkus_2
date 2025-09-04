class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到 target
            } else if (nums[mid] < target) {
                left = mid + 1; // 往右找
            } else {
                right = mid - 1; // 往左找
            }
        }

        // target 不在陣列中，left 就是插入位置
        return left;
    }
}