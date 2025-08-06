import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];

        return merged;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mostFrequent = arr[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public static List<List<Integer>> splitArrayBalanced(int[] arr) {
        Arrays.sort(arr); 
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        int sum1 = 0, sum2 = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                part1.add(arr[i]);
                sum1 += arr[i];
            } else {
                part2.add(arr[i]);
                sum2 += arr[i];
            }
        }

        return Arrays.asList(part1, part2);
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printList(List<Integer> list) {
        System.out.println(list.toString());
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {2, 3, 6, 7, 9};

        System.out.println("原始陣列1: ");
        printArray(arr1);
        System.out.println("移除重複: ");
        printArray(removeDuplicates(arr1));

        System.out.println("\n已排序陣列合併:");
        printArray(mergeSortedArrays(arr1, arr2));

        int[] arr3 = {1, 3, 2, 3, 4, 3, 5, 2};
        System.out.println("\n出現頻率最高的元素: " + findMostFrequent(arr3));

        int[] arr4 = {10, 20, 15, 5, 25};
        List<List<Integer>> split = splitArrayBalanced(arr4);
        System.out.println("\n分割成兩個總和最接近的子陣列:");
        System.out.println("子陣列 1: " + split.get(0));
        System.out.println("子陣列 2: " + split.get(1));
    }
}