import java.util.HashMap;

class Solution {
    public int romanToInt(String s) {
        // 建立羅馬符號對應表
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));

            // 如果不是最後一個字元，且當前字元小於下一個字元 → 減法情況
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }
}