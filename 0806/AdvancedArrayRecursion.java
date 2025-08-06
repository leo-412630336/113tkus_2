import java.util.Arrays;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);  
            quickSort(arr, pi + 1, high); 
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public static int[] mergeSortedArrays(int[] A, int[] B) {
        return mergeHelper(A, B, 0, 0);
    }

    private static int[] mergeHelper(int[] A, int[] B, int i, int j) {
        if (i == A.length) return Arrays.copyOfRange(B, j, B.length);
        if (j == B.length) return Arrays.copyOfRange(A, i, A.length);

        if (A[i] < B[j]) {
            int[] rest = mergeHelper(A, B, i + 1, j);
            return prepend(A[i], rest);
        } else {
            int[] rest = mergeHelper(A, B, i, j + 1);
            return prepend(B[j], rest);
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = val;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) return arr[low];

        int pi = partition(arr, low, high);

        if (pi == k) return arr[pi];
        else if (pi > k) return quickSelect(arr, low, pi - 1, k);
        else return quickSelect(arr, pi + 1, high, k);
    }

    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumHelper(arr, arr.length - 1, target);
    }

    private static boolean subsetSumHelper(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index < 0 || target < 0) return false;

        return subsetSumHelper(arr, index - 1, target - arr[index]) ||
               subsetSumHelper(arr, index - 1, target);
    }

    public static void main(String[] args) {
        int[] quickArr = {7, 2, 1, 6, 8, 5, 3, 4};
        System.out.println("原始陣列: " + Arrays.toString(quickArr));
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("快速排序後: " + Arrays.toString(quickArr));
        int[] sortedA = {1, 3, 5};
        int[] sortedB = {2, 4, 6, 7};
        int[] merged = mergeSortedArrays(sortedA, sortedB);
        System.out.println("\n合併後陣列: " + Arrays.toString(merged));
        int[] kthArr = {12, 3, 5, 7, 19, 1};
        int k = 3;
        int kth = kthSmallest(kthArr.clone(), k);
        System.out.printf("\n第 %d 小元素是: %d\n", k, kth);

        int[] subsetArr = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean hasSubset = subsetSum(subsetArr, target);
        System.out.printf("\n是否存在總和為 %d 的子序列？%b\n", target, hasSubset);
    }
}