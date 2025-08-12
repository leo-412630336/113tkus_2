import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    // 儲存 heap 元素資訊：值、來自哪個陣列、在陣列中的索引
    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex;
        int elementIndex;

        public Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value); // 小頂堆
        }
    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        int totalLength = 0;

        // 初始化 heap：每個陣列的第一個元素
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new Element(arrays[i][0], i, 0));
                totalLength += arrays[i].length;
            }
        }

        int[] result = new int[totalLength];
        int idx = 0;

        // 每次取出最小值，然後放入該陣列的下一個元素
        while (!minHeap.isEmpty()) {
            Element e = minHeap.poll();
            result[idx++] = e.value;

            int nextIndex = e.elementIndex + 1;
            if (nextIndex < arrays[e.arrayIndex].length) {
                minHeap.add(new Element(
                    arrays[e.arrayIndex][nextIndex],
                    e.arrayIndex,
                    nextIndex
                ));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr3 = {{1}, {0}};

        System.out.println(Arrays.toString(mergeKSortedArrays(arr1)));
        System.out.println(Arrays.toString(mergeKSortedArrays(arr2)));
        System.out.println(Arrays.toString(mergeKSortedArrays(arr3)));
    }
}