class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 忽略前導空白
        while (i < n && s.charAt(i) == ' ') i++;

        // 2. 處理符號
        int sign = 1;
        if (i < n) {
            char c = s.charAt(i);
            if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            }
        }

        // 3. 讀取數字並轉換
        int result = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break; // 遇到非數字停止

            int digit = c - '0';

            // 4. 溢位檢查
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}