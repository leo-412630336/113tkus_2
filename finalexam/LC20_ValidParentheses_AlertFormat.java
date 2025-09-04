import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class LC20_ValidParentheses_AlertFormat {

    /**
     * @param s 僅包含 '()[]{}' 的格式字串
     * @return 括號是否合法匹配
     *
     * 時間複雜度: O(n) - 只需要遍歷一次字串。
     * 空間複雜度: O(n) - 最差情況下，Stack 需要儲存所有開括號，例如 "((((...))))"。
     */
    public static boolean isValid(String s) {
        // 邊界條件：空字串為合法
        if (s == null || s.length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            // 如果是閉括號
            if (map.containsKey(c)) {
                // 如果堆疊為空，或者棧頂元素不匹配
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else {
                // 如果是開括號，推入堆疊
                stack.push(c);
            }
        }

        // 最後檢查堆疊是否為空。若不空，表示有未閉合的開括號
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入災防通報格式字串（只包含 ()[]{}）：");
        String input = scanner.nextLine();
        scanner.close();

        boolean result = isValid(input);
        
        System.out.println("格式是否合法: " + result);
    }
}