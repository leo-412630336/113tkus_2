class Solution {
    public boolean isPalindrome(int x) {
        // 負數和末位為0但不等於0的數字都不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        // 只反轉數字的一半
        while (x > reversedHalf) {
            int pop = x % 10;
            x /= 10;
            reversedHalf = reversedHalf * 10 + pop;
        }

        // 奇數位數時，中間數字可以忽略
        return x == reversedHalf || x == reversedHalf / 10;
    }
}