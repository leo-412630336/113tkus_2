import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int substringLen = wordLen * wordCount;

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // 遍歷每個可能起始位置
        for (int i = 0; i <= s.length() - substringLen; i++) {
            String sub = s.substring(i, i + substringLen);
            if (isValid(sub, wordLen, wordCount, wordFreq)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isValid(String sub, int wordLen, int wordCount, Map<String, Integer> wordFreq) {
        Map<String, Integer> seen = new HashMap<>();
        for (int i = 0; i < wordCount; i++) {
            int start = i * wordLen;
            String word = sub.substring(start, start + wordLen);
            if (!wordFreq.containsKey(word)) return false;
            seen.put(word, seen.getOrDefault(word, 0) + 1);
            if (seen.get(word) > wordFreq.get(word)) return false;
        }
        return true;
    }
}