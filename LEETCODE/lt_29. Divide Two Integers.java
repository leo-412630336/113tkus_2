class Solution {
    public int divide(int dividend, int divisor) {
        // 特殊溢位情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 判斷符號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long 避免溢位
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        int result = 0;

        // 位移減法
        for (int i = 31; i >= 0; i--) {
            if ((dvd >> i) >= dvs) {
                result += 1 << i;
                dvd -= dvs << i;
            }
        }

        return negative ? -result : result;
    }
}