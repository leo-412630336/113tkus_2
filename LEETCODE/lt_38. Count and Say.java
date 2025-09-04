class Solution {
    public String countAndSay(int n) {
        String result = "1"; // base case
        for (int i = 2; i <= n; i++) {
            result = nextSequence(result);
        }
        return result;
    }

    private String nextSequence(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        // 處理最後一段字符
        sb.append(count).append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}