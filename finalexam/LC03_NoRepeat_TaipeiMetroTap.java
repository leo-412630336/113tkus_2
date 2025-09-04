import java.util.HashMap;
import java.util.Scanner;

public class LC03_NoRepeat_TaipeiMetroTap {

    /**
     * @param s 捷運進出站刷卡流水
     * @return 最長不含重複字元子字串長度
     *
     * 時間複雜度: O(n) - 左右指針都只遍歷一次字串。
     * 空間複雜度: O(k) - k 為字元集的大小，最差情況下為字元集總數（例如 ASCII 碼 256 個）。
     */
    public static int lengthOfLongestSubstring(String s) {
        // 邊界條件：空字串長度為 0
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 用 HashMap 記錄每個字元最近一次出現的索引
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0; // 滑動視窗的左邊界

        // right 為滑動視窗的右邊界，向右擴展
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 檢查當前字元是否在視窗中重複
            if (map.containsKey(currentChar)) {
                // 如果重複，將左邊界 left 移動到重複字元上次出現的下一個位置
                // 使用 Math.max 是為了防止 left 向後退，確保視窗只向前滑動
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // 更新當前字元在 HashMap 中的最新索引
            map.put(currentChar, right);

            // 計算當前視窗長度並更新最大長度
            // 視窗長度 = right - left + 1
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入捷運刷卡流水字串:");
        String s = scanner.nextLine();
        scanner.close();

        int result = lengthOfLongestSubstring(s);
        System.out.println("最長不重複刷卡區間的長度為: " + result);
    }
}