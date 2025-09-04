import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC40_CombinationSum2_Procurement {
    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序是去重的前提
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.target = target;
        backtrack(target, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(int remain, List<Integer> combo, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(combo));
            return;
        }
        if (remain < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重：如果當前元素與前一個相同，且在同一層中，則跳過
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combo.add(candidates[i]);
            // 每個元素只能用一次，下一層遞迴從 i+1 開始
            backtrack(remain - candidates[i], combo, i + 1);
            combo.remove(combo.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入物資數量 n 和預算 target (以空格分隔):");
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] candidates = new int[n];
        System.out.println("請輸入 " + n + " 個物資價格 (以空格分隔):");
        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt();
        }
        scanner.close();

        LC40_CombinationSum2_Procurement solution = new LC40_CombinationSum2_Procurement();
        List<List<Integer>> combos = solution.combinationSum2(candidates, target);
        
        System.out.println("所有可行物資組合如下:");
        for (List<Integer> combo : combos) {
            System.out.println(combo);
        }
    }
}