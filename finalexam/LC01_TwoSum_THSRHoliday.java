import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {

    /**
     * @param seats 每個班次可釋出的座位數陣列
     * @param target 臨時新增需求總座位
     * @return 兩個班次的索引，若無解則為 {-1, -1}
     *
     * 時間複雜度: O(n) - 只需要遍歷一次陣列。
     * 空間複雜度: O(n) - 最差情況下，HashMap 需要儲存 n 個元素。
     */
    public static int[] findTwoSeats(int[] seats, int target) {
        // 使用 HashMap 儲存「達到目標還需要的數」及其索引
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍歷座位數陣列
        for (int i = 0; i < seats.length; i++) {
            int currentSeatCount = seats[i];
            
            // 檢查 HashMap 中是否已存在當前座位數
            // 這一操作的平均時間複雜度為 O(1)
            if (map.containsKey(currentSeatCount)) {
                // 如果存在，代表我們找到了另一半，直接返回結果
                int anotherIndex = map.get(currentSeatCount);
                return new int[]{anotherIndex, i};
            }
            
            // 如果不存在，則計算並記錄我們需要的座位數
            int complement = target - currentSeatCount;
            // 將「需要的數」作為 Key，當前索引作為 Value 存入 HashMap
            // 這一操作的平均時間複雜度為 O(1)
            map.put(complement, i);
        }

        // 遍歷結束仍未找到，返回 -1 -1
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("請輸入 n（班次數）和 target（需求總座位），以空格分隔:");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        System.out.println("請輸入 " + n + " 個班次的座位數，以空格分隔:");
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        
        scanner.close();

        int[] result = findTwoSeats(seats, target);
        
        if (result[0] != -1) {
            System.out.println("找到的班次索引為: " + result[0] + " " + result[1]);
        } else {
            System.out.println("-1 -1");
        }
    }
}