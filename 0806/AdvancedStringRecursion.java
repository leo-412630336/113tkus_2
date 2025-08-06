import java.util.*;

public class AdvancedStringRecursion {

    public static void generatePermutations(String str) {
        generatePermutationsHelper("", str);
    }

    private static void generatePermutationsHelper(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            generatePermutationsHelper(
                prefix + remaining.charAt(i),
                remaining.substring(0, i) + remaining.substring(i + 1)
            );
        }
    }

    public static boolean recursiveMatch(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.isEmpty()) return false;

        if (text.charAt(0) == pattern.charAt(0)) {
            return recursiveMatch(text.substring(1), pattern.substring(1));
        } else {
            return recursiveMatch(text.substring(1), pattern);
        }
    }

    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, "", new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, String result, Set<Character> seen) {
        if (str.isEmpty()) return result;
        char ch = str.charAt(0);
        if (!seen.contains(ch)) {
            seen.add(ch);
            result += ch;
        }
        return removeDuplicatesHelper(str.substring(1), result, seen);
    }

    public static void generateSubstrings(String str) {
        generateSubstringsHelper(str, "");
    }

    private static void generateSubstringsHelper(String remaining, String current) {
        if (!current.isEmpty()) {
            System.out.println(current);
        }
        for (int i = 0; i < remaining.length(); i++) {
            generateSubstringsHelper(remaining.substring(i + 1), current + remaining.charAt(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("1. 字串排列組合:");
        generatePermutations("abc");

        System.out.println("\n2. 字串匹配:");
        System.out.println(recursiveMatch("hello world", "hlo")); 
        System.out.println(recursiveMatch("hello world", "hrd"));  

        System.out.println("\n3. 移除重複字符:");
        System.out.println(removeDuplicates("banana")); 

        System.out.println("\n4. 子字串組合:");
        generateSubstrings("abc");
    }
}