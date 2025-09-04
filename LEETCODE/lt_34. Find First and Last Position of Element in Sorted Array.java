class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return result;

        result[0] = findBound(nums, target, true);  // 找左邊界
        if (result[0] == -1) return result;         // 如果找不到，直接回傳 [-1,-1]
        result[1] = findBound(nums, target, false); // 找右邊界
        return result;
    }

    // findLeft = true -> 找左邊界, false -> 找右邊界
    private int findBound(int[] nums, int target, boolean findLeft) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (findLeft) {
                    right = mid - 1; // 繼續找左邊
                } else {
                    left = mid + 1;  // 繼續找右邊
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }
}