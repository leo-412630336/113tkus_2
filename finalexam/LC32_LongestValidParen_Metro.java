import java.util.Scanner;
import java.util.Stack;

public class LC32_LongestValidParen_Metro {

    /**
     * @param s 僅包含 '(' 或 ')' 的字串
     * @return 最長合法括號子字串的長度
     *
     * 時間複雜度: O(n) - 只需對字串進行一次遍歷。
     * 空間複雜度: O(n) - 最差情況下，Stack 需要儲存所有開括號。
     */
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        
        // 推入 -1 作為初始基準
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 如果是左括號，將其索引推入堆疊
                stack.push(i);
            } else { // c == ')'
                // 如果是右括號，先彈出棧頂元素
                stack.pop();

                // 檢查堆疊是否為空
                if (stack.isEmpty()) {
                    // 如果堆疊為空，表示當前的 ')' 沒有配對，
                    // 將當前索引作為新的基準推入堆疊
                    stack.push(i);
                } else {
                    // 如果堆疊不空，表示成功配對，
                    // 計算當前合法區間的長度，並更新最大長度
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入北捷閘門日誌字串（只包含 ( 和 )）：");
        String input = scanner.nextLine();
        scanner.close();

        int result = longestValidParentheses(input);
        
        System.out.println("最長合法區間長度為: " + result);
    }
}