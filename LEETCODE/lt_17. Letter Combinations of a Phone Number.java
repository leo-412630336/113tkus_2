import java.util.*;

class Solution {
    // 數字到字母的對應
    private static final String[] KEYS = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder path, String digits, int index) {
        // 如果已經處理完所有數字，加入結果
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        // 當前數字對應的字母串
        String letters = KEYS[digits.charAt(index) - '0'];

        // 遍歷每個可能字母
        for (char c : letters.toCharArray()) {
            path.append(c); // 選擇
            backtrack(res, path, digits, index + 1); // 遞迴處理下一位數字
            path.deleteCharAt(path.length() - 1); // 回溯（撤銷選擇）
        }
    }
}