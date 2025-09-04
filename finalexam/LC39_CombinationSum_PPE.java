import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC39_CombinationSum_PPE {
    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            combo.add(candidates[i]);
            // 由於同一元素可以重複使用，下一層遞迴從當前索引 i 開始
            backtrack(remain - candidates[i], combo, i);
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

        LC39_CombinationSum_PPE solution = new LC39_CombinationSum_PPE();
        List<List<Integer>> combos = solution.combinationSum(candidates, target);
        
        System.out.println("所有可行物資組合如下:");
        for (List<Integer> combo : combos) {
            System.out.println(combo);
        }
    }
}