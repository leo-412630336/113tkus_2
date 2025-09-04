import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 使用滑動視窗 (Sliding Window)
        Set<Character> window = new HashSet<>();
        int left = 0;   // 左指針
        int right = 0;  // 右指針
        int maxLen = 0; // 最長長度

        while (right < s.length()) {
            char c = s.charAt(right);

            // 如果目前字元已經出現過，就移動左指針，直到移除重複字元
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            // 加入新字元到集合
            window.add(c);

            // 更新最長長度
            maxLen = Math.max(maxLen, right - left + 1);

            // 右指針前進
            right++;
        }

        return maxLen;
    }
}