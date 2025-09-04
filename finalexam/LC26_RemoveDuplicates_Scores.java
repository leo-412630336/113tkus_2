import java.util.Scanner;

public class LC26_RemoveDuplicates_Scores {

    /**
     * @param nums 學生學號陣列（已排序）
     * @return 移除重複後的新長度
     *
     * 時間複雜度: O(n) - 只需要遍歷陣列一次。
     * 空間複雜度: O(1) - 在原陣列上操作，沒有使用額外的空間。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 1; // 寫入指針
        for (int i = 1; i < nums.length; i++) { // 讀取指針
            // 如果當前讀取到的元素與上一個不重複的元素不同
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入學號總數 n:");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個已排序的學號，以空格分隔:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        scanner.close();

        int newLength = removeDuplicates(nums);

        System.out.println("壓縮後長度為: " + newLength);
        System.out.print("前段內容為: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}