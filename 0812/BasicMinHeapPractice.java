import java.util.ArrayList;

public class BasicMinHeapPractice {

    private ArrayList<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    // 插入元素
    public void insert(int val) {
        heap.add(val);              // 加到最後
        siftUp(heap.size() - 1);    // 上浮調整
    }

    // 取出最小元素並移除
    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);      // 最後一個放到頂端
            siftDown(0);            // 下沉調整
        }
        return min;
    }

    // 查看最小元素但不移除
    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }

    // 回傳大小
    public int size() {
        return heap.size();
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 上浮
    private void siftUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (heap.get(idx) < heap.get(parent)) {
                swap(idx, parent);
                idx = parent;
            } else break;
        }
    }

    // 下沉
    private void siftDown(int idx) {
        int left, right, smallest;
        while (true) {
            left = idx * 2 + 1;
            right = idx * 2 + 2;
            smallest = idx;

            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest != idx) {
                swap(idx, smallest);
                idx = smallest;
            } else break;
        }
    }

    // 交換
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    // 測試程式
    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        int[] nums = {15, 10, 20, 8, 25, 5};
        for (int num : nums) {
            minHeap.insert(num);
        }

        System.out.println("ExtractMin 順序：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
    }
}