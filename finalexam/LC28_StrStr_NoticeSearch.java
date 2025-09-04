import java.util.Scanner;

public class LC28_StrStr_NoticeSearch {

    /**
     * @param haystack 公告全文
     * @param needle 欲搜尋的關鍵字
     * @return 首次出現的起始索引，若無則為 -1
     *
     * 時間複雜度: O((n-m+1) * m) ≈ O(n*m)
     * 空間複雜度: O(1)
     */
    public static int strStr(String haystack, String needle) {
        // 邊界情況：如果 needle 為空字串
        if (needle.length() == 0) {
            return 0;
        }

        int n = haystack.length();
        int m = needle.length();

        // 暴力法
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    // 如果不匹配，跳出內層迴圈
                    break;
                }
            }
            // 如果內層迴圈完整執行，表示找到匹配
            if (j == m) {
                return i;
            }
        }

        // 遍歷結束仍未找到
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入公告全文（haystack）:");
        String haystack = scanner.nextLine();
        
        System.out.println("請輸入欲搜尋的關鍵字（needle）:");
        String needle = scanner.nextLine();
        
        scanner.close();

        int index = strStr(haystack, needle);
        
        if (index != -1) {
            System.out.println("關鍵字首次出現的索引為: " + index);
        } else {
            System.out.println("找不到該關鍵字。");
        }
    }
}