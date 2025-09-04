import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC17_PhoneCombos_CSShift {

    // 數字到字母的映射
    private static final String[] MAPPING = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    
    private List<String> result = new ArrayList<>();
    private String digits;

    /**
     * @param digits 輸入的數字字串
     * @return 所有可能的字母組合列表
     *
     * 時間複雜度: O(4^n * n) - n 是數字字串長度。4 是最多字母數（7, 9）。
     * 每次遞迴都會遍歷 3 或 4 個字母，並且每次加入字串操作需要 O(n)。
     * 空間複雜度: O(n) - 遞迴深度為 n，StringBuilder 的空間也為 n。
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        this.digits = digits;
        backtrack(0, new StringBuilder());
        return result;
    }

    private void backtrack(int index, StringBuilder currentCombination) {
        // 遞迴終止條件：處理完所有數字
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // 獲取當前數字對應的字母字串
        String letters = MAPPING[digits.charAt(index) - '0'];

        // 遍歷所有可能的字母
        for (char letter : letters.toCharArray()) {
            // 選擇：將字母加入當前組合
            currentCombination.append(letter);
            
            // 遞迴：處理下一個數字
            backtrack(index + 1, currentCombination);
            
            // 回溯：移除剛才加入的字母，探索其他分支
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入客服任務標籤數字代碼（2-9，例如 23）：");
        String input = scanner.nextLine();
        scanner.close();

        LC17_PhoneCombos_CSShift solution = new LC17_PhoneCombos_CSShift();
        List<String> combinations = solution.letterCombinations(input);

        if (combinations.isEmpty()) {
            System.out.println("輸入為空，無組合可輸出。");
        } else {
            System.out.println("所有可能的字母組合如下:");
            for (String combo : combinations) {
                System.out.println(combo);
            }
        }
    }
}