import java.util.*;

public class M02_YouBikeNextArrival {
    private static int toMinutes(String t) {
        String[] parts = t.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    private static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    private static int binarySearchNext(int[] arr, int query) {
        int lo = 0, hi = arr.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > query) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(sc.nextLine().trim());
        }
        int query = toMinutes(sc.nextLine().trim());

        int idx = binarySearchNext(times, query);
        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[idx]));
        }
    }
}

/*
時間複雜度：O(log n)
空間複雜度：O(n)
*/