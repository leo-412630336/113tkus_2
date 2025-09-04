import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是左括號，就放進 stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 如果遇到右括號但 stack 是空的，直接 false
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                // 檢查是否配對
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // 如果最後 stack 為空，表示完全匹配
        return stack.isEmpty();
    }
}