public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1) return true; // 空陣列或只有一個元素都算合法

        // 最後一個非葉子節點的索引
        int lastNonLeaf = (n - 2) / 2;

        for (int i = 0; i <= lastNonLeaf; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            // 檢查左子節點
            if (left < n && arr[i] < arr[left]) {
                System.out.println("false (索引" + left + "的" + arr[left] +
                                   "大於父節點" + arr[i] + ")");
                return false;
            }
            // 檢查右子節點
            if (right < n && arr[i] < arr[right]) {
                System.out.println("false (索引" + right + "的" + arr[right] +
                                   "大於父節點" + arr[i] + ")");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] test : testCases) {
            boolean result = isValidMaxHeap(test);
            if (result) {
                System.out.println("true");
            }
        }
    }
}