class Solution {
    public int maxArea(int[] height) {
        int left = 0;                   // 左指針
        int right = height.length - 1;  // 右指針
        int maxArea = 0;                // 最大面積

        while (left < right) {
            // 計算當前面積
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;

            // 更新最大面積
            maxArea = Math.max(maxArea, area);

            // 移動較短的指針，因為面積受限於最短邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}