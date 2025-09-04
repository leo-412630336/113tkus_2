class Solution {
    public int reverse(int x) {
        int rev = 0; // 存放反轉後的數字

        while (x != 0) {
            int pop = x % 10; // 取最後一位
            x /= 10;          // 去掉最後一位

            // 溢位檢查
            // 如果 rev > Integer.MAX_VALUE/10 或 rev < Integer.MIN_VALUE/10，乘 10 後肯定溢位
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            rev = rev * 10 + pop; // 將 pop 加到 rev 的末尾
        }

        return rev;
    }
}