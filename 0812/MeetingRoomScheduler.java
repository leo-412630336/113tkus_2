import java.util.*;

public class MeetingRoomScheduler {

    // 第一部分：計算最少需要的會議室數量
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 按開始時間排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 儲存結束時間

        for (int[] meeting : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll(); // 重用房間
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    // 第二部分：只有 1 個會議室的最大總會議時間
    public static int maxMeetingTimeWithOneRoom(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // 按結束時間排序
        int n = intervals.length;
        int[] dp = new int[n];

        dp[0] = intervals[0][1] - intervals[0][0];

        for (int i = 1; i < n; i++) {
            int include = intervals[i][1] - intervals[i][0];
            int lastNonConflict = findLastNonConflict(intervals, i);
            if (lastNonConflict != -1) {
                include += dp[lastNonConflict];
            }
            dp[i] = Math.max(dp[i - 1], include);
        }
        return dp[n - 1];
    }

    // 找最後一個不衝突的會議
    private static int findLastNonConflict(int[][] intervals, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (intervals[i][1] <= intervals[index][0]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] meetings1 = {{0,30},{5,10},{15,20}};
        System.out.println("最少會議室: " + minMeetingRooms(meetings1)); // 2

        int[][] meetings2 = {{1,4},{2,3},{4,6}};
        System.out.println("1 個會議室最大總時間: " + maxMeetingTimeWithOneRoom(meetings2)); // 5
    }
}