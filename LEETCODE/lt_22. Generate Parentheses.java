import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, int open, int close, int max) {
        // 如果字串長度 = 2 * n，加入結果
        if (sb.length() == max * 2) {
            res.add(sb.toString());
            return;
        }

        // 如果還能加 "("
        if (open < max) {
            sb.append("(");
            backtrack(res, sb, open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }

        // 如果還能加 ")"
        if (close < open) {
            sb.append(")");
            backtrack(res, sb, open, close + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}