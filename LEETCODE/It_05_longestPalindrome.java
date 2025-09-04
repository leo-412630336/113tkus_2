class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0; // 最長回文子字串起始索引
        int end = 0;   // 最長回文子字串結束索引

        for (int i = 0; i < s.length(); i++) {
            // 以 i 為中心，找奇數長度回文
            int len1 = expandFromCenter(s, i, i);
            // 以 i 和 i+1 為中心，找偶數長度回文
            int len2 = expandFromCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                // 更新最長回文子字串的起訖索引
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // 從中心向外擴展，返回回文子字串長度
    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 注意：right - left - 1 才是回文長度
        return right - left - 1;
    }
}