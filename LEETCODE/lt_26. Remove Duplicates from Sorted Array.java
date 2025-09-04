class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int writeIndex = 1; // 用來寫入下個唯一值的位置

        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[readIndex - 1]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // 唯一元素的個數
    }
}