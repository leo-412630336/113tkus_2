class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 取第一個字串作為比較基準
        String first = strs[0];
        int n = first.length();

        for (int i = 0; i < n; i++) {
            char c = first.charAt(i);

            // 遍歷其他字串的對應位置
            for (int j = 1; j < strs.length; j++) {
                // 若超過長度或字元不同，則返回目前的前綴
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }

        // 第一個字串本身就是公共前綴
        return first;
    }
}