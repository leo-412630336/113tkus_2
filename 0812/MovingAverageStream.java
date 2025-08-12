import java.util.*;

public class MovingAverageStream {
    private int size;
    private long sum = 0;
    private Queue<Integer> window;

    // For median
    private PriorityQueue<Integer> maxHeap; // Left
    private PriorityQueue<Integer> minHeap; // Right
    private Map<Integer, Integer> delayedRemoval;

    // For min & max
    private Deque<Integer> minDeque;
    private Deque<Integer> maxDeque;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayedRemoval = new HashMap<>();

        this.minDeque = new LinkedList<>();
        this.maxDeque = new LinkedList<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // Maintain median heaps
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        // Maintain minDeque
        while (!minDeque.isEmpty() && minDeque.peekLast() > val) {
            minDeque.pollLast();
        }
        minDeque.offerLast(val);

        // Maintain maxDeque
        while (!maxDeque.isEmpty() && maxDeque.peekLast() < val) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(val);

        // Remove old element if window exceeds size
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeFromMedian(removed);

            if (!minDeque.isEmpty() && minDeque.peekFirst() == removed) {
                minDeque.pollFirst();
            }
            if (!maxDeque.isEmpty() && maxDeque.peekFirst() == removed) {
                maxDeque.pollFirst();
            }
        }

        return (double) sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    public int getMin() {
        return minDeque.peekFirst();
    }

    public int getMax() {
        return maxDeque.peekFirst();
    }

    // --- Helper methods ---
    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private void removeFromMedian(int num) {
        delayedRemoval.put(num, delayedRemoval.getOrDefault(num, 0) + 1);

        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            pruneHeap(maxHeap);
        } else {
            pruneHeap(minHeap);
        }
        balanceHeaps();
    }

    private void pruneHeap(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayedRemoval.containsKey(heap.peek())) {
            int top = heap.peek();
            delayedRemoval.put(top, delayedRemoval.get(top) - 1);
            if (delayedRemoval.get(top) == 0) {
                delayedRemoval.remove(top);
            }
            heap.poll();
        }
    }

    // --- Testing ---
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));  // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3));  // 4.666...
        System.out.println(ma.next(5));  // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}