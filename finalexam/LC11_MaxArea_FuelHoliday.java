import java.util.Scanner;

public class LC11_MaxArea_FuelHoliday {

    /**
     * @param heights 每個油槽的高度
     * @return 最大面積
     *
     * 時間複雜度: O(n) - 兩個指針最多各移動 n-1 次，只進行一次遍歷。
     * 空間複雜度: O(1) - 只使用了幾個變數。
     */
    public static int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            // 計算當前面積
            int currentArea = (right - left) * Math.min(heights[left], heights[right]);
            
            // 更新最大面積
            maxArea = Math.max(maxArea, currentArea);

            // 移動較短邊的指針以尋求更大的高度
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入油槽的數量 n:");
        int n = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個油槽的高度，以空格分隔:");
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        
        scanner.close();

        int result = maxArea(heights);
        System.out.println("可形成的最大輸出帶寬（面積）為: " + result);
    }
}