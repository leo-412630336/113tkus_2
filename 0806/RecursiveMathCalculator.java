public class RecursiveMathCalculator {

    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n <= 1) return 1;

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindrome(int num) {
        String str = Integer.toString(num);
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return isPalindromeHelper(str, left + 1, right - 1);
    }

    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.printf("C(%d, %d) = %d\n", n, k, combination(n, k));
        int catalanN = 4;
        System.out.printf("Catalan(%d) = %d\n", catalanN, catalan(catalanN));
        int hanoiN = 3;
        System.out.printf("Hanoi(%d) 移動步數 = %d\n", hanoiN, hanoiMoves(hanoiN));
        int palindromeNum1 = 12321;
        int palindromeNum2 = 12345;
        System.out.printf("%d 是否為回文數？%b\n", palindromeNum1, isPalindrome(palindromeNum1));
        System.out.printf("%d 是否為回文數？%b\n", palindromeNum2, isPalindrome(palindromeNum2));
    }
}