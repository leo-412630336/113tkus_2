import java.util.*;

public class M11_HeapSortWithTie {
    static class Student {
        int score, index;
        Student(int s, int i) { score = s; index = i; }
    }

    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2*i + 1, r = 2*i + 2;
        if (l < n && (arr[l].score > arr[largest].score ||
            (arr[l].score == arr[largest].score && arr[l].index < arr[largest].index))) {
            largest = l;
        }
        if (r < n && (arr[r].score > arr[largest].score ||
            (arr[r].score == arr[largest].score && arr[r].index < arr[largest].index))) {
            largest = r;
        }
        if (largest != i) {
            Student tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    private static void heapSort(Student[] arr) {
        int n = arr.length;
        for (int i = n/2 -1; i >=0; i--) heapify(arr, n, i);
        for (int i = n-1; i > 0; i--) {
            Student tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) arr[i] = new Student(sc.nextInt(), i);

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i < n-1) System.out.print(" ");
        }
    }
}

/*
時間複雜度：O(n log n)
空間複雜度：O(1) 除輸入陣列外
*/