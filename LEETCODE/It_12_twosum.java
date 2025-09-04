class Solution {
    public String intToRoman(int num) {
        // 先建立羅馬數字對應表，從大到小排序
        int[] values =    {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols ={"M", "CM","D", "CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            // 當 num >= values[i]，就減去對應數值並加上符號
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }
}