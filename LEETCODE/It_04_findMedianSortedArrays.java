class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的陣列，這樣二分搜尋才會更高效
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0, right = m;
        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            // 左邊最大 & 右邊最小（處理邊界）
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // 檢查是否劃分正確
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 偶數情況：取中間兩個數的平均
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } 
                // 奇數情況：取左邊最大值
                else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } 
            // nums1 左邊太大，往左縮小
            else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } 
            // nums1 左邊太小，往右增加
            else {
                left = partition1 + 1;
            }
        }

        // 理論上不會到這裡
        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}