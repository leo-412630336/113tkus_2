import java.util.*;

public class M01_BuildHeap {
    private static void heapifyDown(int[] heap, int n, int i, boolean isMax) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (isMax) {
            if (left < n && heap[left] > heap[extreme]) extreme = left;
            if (right < n && heap[right] > heap[extreme]) extreme = right;
        } else {
            if (left < n && heap[left] < heap[extreme]) extreme = left;
            if (right < n && heap[right] < heap[extreme]) extreme = right;
        }

        if (extreme != i) {
            int temp = heap[i];
            heap[i] = heap[extreme];
            heap[extreme] = temp;
            heapifyDown(heap, n, extreme, isMax);
        }
    }

    private static void buildHeap(int[] heap, int n, boolean isMax) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(heap, n, i, isMax);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        boolean isMax = type.equals("max");
        buildHeap(heap, n, isMax);

        for (int i = 0; i < n; i++) {
            System.out.print(heap[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }
}

/*
時間複雜度：O(n)
空間複雜度：O(1)
*/