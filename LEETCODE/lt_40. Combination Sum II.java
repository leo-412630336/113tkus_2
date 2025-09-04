import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 先排序，方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) return;

        for (int i = start; i < candidates.length; i++) {
            // 去重：同一層不能選擇相同的數字
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            current.add(candidates[i]);
            // 因為每個數字只能用一次，所以下一層從 i+1 開始
            backtrack(candidates, target - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1); // 回溯
        }
    }
}