import java.util.Arrays;

public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("選擇排序過程：");
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + compareCount);
        System.out.println("總交換次數: " + swapCount);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("氣泡排序過程：");
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
            if (!swapped) break;
        }

        System.out.println("總比較次數: " + compareCount);
        System.out.println("總交換次數: " + swapCount);
    }

    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11};

        int[] selectionArr = Arrays.copyOf(original, original.length);
        System.out.println("原始陣列（選擇排序）: " + Arrays.toString(selectionArr));
        selectionSort(selectionArr);
        System.out.println("排序結果: " + Arrays.toString(selectionArr));

        System.out.println("\n====================\n");

        int[] bubbleArr = Arrays.copyOf(original, original.length);
        System.out.println("原始陣列（氣泡排序）: " + Arrays.toString(bubbleArr));
        bubbleSort(bubbleArr);
        System.out.println("排序結果: " + Arrays.toString(bubbleArr));
    }
}