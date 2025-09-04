class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            // 若只有一行，或者行數 >= 字串長度，直接返回原字串
            return s;
        }

        // 建立 StringBuilder 陣列，每行對應一個 StringBuilder
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int curRow = 0;      // 當前行
        boolean goingDown = false; // 控制方向

        // 遍歷每個字元
        for (char c : s.toCharArray()) {
            rows[curRow].append(c); // 將字元加到對應行

            // 遇到頂部或底部就換方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }

            curRow += goingDown ? 1 : -1;
        }

        // 將所有行串起來
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}