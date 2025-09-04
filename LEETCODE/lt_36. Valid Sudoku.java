class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 使用 HashSet 分別檢查行、列、小格子
        HashSet<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    // 建立三個唯一標識字串
                    String rowKey = num + " in row " + i;
                    String colKey = num + " in col " + j;
                    String boxKey = num + " in box " + (i/3) + "-" + (j/3);
                    
                    // 如果已經存在，表示違反規則
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}