import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueWithHeap {

    // 任務類別
    static class Task {
        String name;
        int priority;
        long timestamp; // 用來處理相同優先級時的先後順序

        public Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }

    private PriorityQueue<Task> queue;
    private long counter; // 用來生成遞增的時間戳

    public PriorityQueueWithHeap() {
        counter = 0;
        queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.priority != t2.priority) {
                    return Integer.compare(t2.priority, t1.priority); // 優先級高的排前面
                }
                return Long.compare(t1.timestamp, t2.timestamp); // 同優先級先來的先執行
            }
        });
    }

    // 添加任務
    public void addTask(String name, int priority) {
        queue.add(new Task(name, priority, counter++));
    }

    // 執行下一個任務
    public void executeNext() {
        if (queue.isEmpty()) {
            System.out.println("沒有任務可執行");
            return;
        }
        Task task = queue.poll();
        System.out.println("執行任務：" + task.name + " (優先級：" + task.priority + ")");
    }

    // 查看下一個任務
    public void peek() {
        if (queue.isEmpty()) {
            System.out.println("沒有任務");
            return;
        }
        Task task = queue.peek();
        System.out.println("下一個任務：" + task.name + " (優先級：" + task.priority + ")");
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        PriorityQueue<Task> newQueue = new PriorityQueue<>(queue.comparator());
        boolean found = false;

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (task.name.equals(name)) {
                task.priority = newPriority;
                task.timestamp = counter++; // 更新時間，確保新優先級的排序正確
                found = true;
            }
            newQueue.add(task);
        }

        queue = newQueue;

        if (!found) {
            System.out.println("找不到任務：" + name);
        }
    }

    // 測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        pq.peek(); // 預期：緊急修復

        pq.executeNext(); // 緊急修復
        pq.executeNext(); // 更新
        pq.executeNext(); // 備份
        pq.executeNext(); // 沒有任務可執行

        // 測試 changePriority
        pq.addTask("掃描", 2);
        pq.addTask("下載", 4);
        pq.changePriority("掃描", 6); // 提升優先級

        pq.executeNext(); // 掃描
        pq.executeNext(); // 下載
    }
}