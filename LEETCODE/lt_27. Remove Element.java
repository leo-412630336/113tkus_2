class Solution {
    public int removeElement(int[] nums, int val) {
        int writeIndex = 0; // 用來寫入非 val 的位置

        for (int readIndex = 0; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != val) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 返回非 val 元素的個數
    }
}