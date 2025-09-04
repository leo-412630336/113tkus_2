import java.util.Scanner;

public class LC27_RemoveElement_Recycle {

    /**
     * @param nums 回收場分類清單陣列
     * @param val 要移除的指定代碼
     * @return 移除後的新長度
     *
     * 時間複雜度: O(n) - 只需要遍歷陣列一次。
     * 空間複雜度: O(1) - 在原陣列上操作，沒有使用額外的空間。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 0; // 寫入指針
        for (int i = 0; i < nums.length; i++) { // 讀取指針
            // 如果當前讀取到的元素不等於 val
            if (nums[i] != val) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入清單總數 n 和要移除的代碼 val，以空格分隔:");
        int n = scanner.nextInt();
        int val = scanner.nextInt();
        
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個清單數值，以空格分隔:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        scanner.close();

        int newLength = removeElement(nums, val);

        System.out.println("新長度為: " + newLength);
        System.out.print("前段結果為: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}