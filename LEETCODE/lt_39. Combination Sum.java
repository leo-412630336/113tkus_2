import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, 
                           List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            // 找到一組和為 target 的組合
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return; // 超過 target，直接返回
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            // 因為可以重複使用，所以 i 不加 1
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1); // 回溯
        }
    }
}