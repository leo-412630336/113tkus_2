public class RecursionVsIteration {

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int maxK = Math.min(i, k);
            for (int j = 0; j <= maxK; j++) {
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int val : arr) {
            product *= val;
        }
        return product;
    }


    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) return 0;
        char ch = Character.toLowerCase(str.charAt(index));
        int count = (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char ch : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(ch) >= 0) count++;
        }
        return count;
    }


    public static boolean isBalancedRecursive(String str) {
        return isBalancedHelper(str, 0, 0);
    }

    private static boolean isBalancedHelper(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;

        char ch = str.charAt(index);
        if (ch == '(') balance++;
        else if (ch == ')') balance--;

        return isBalancedHelper(str, index + 1, balance);
    }

    public static boolean isBalancedIterative(String str) {
        int balance = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(') balance++;
            else if (ch == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        int n = 10, k = 4;
        System.out.println("Binomial Recursive C(10,4): " + binomialRecursive(n, k));
        System.out.println("Binomial Iterative C(10,4): " + binomialIterative(n, k));

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Product Recursive: " + productRecursive(arr, 0));
        System.out.println("Product Iterative: " + productIterative(arr));

        String str = "Recursion is Powerful";
        System.out.println("Vowels Recursive: " + countVowelsRecursive(str, 0));
        System.out.println("Vowels Iterative: " + countVowelsIterative(str));

        String valid = "((())())";
        String invalid = "(()()))";
        System.out.println("Balanced Recursive (valid): " + isBalancedRecursive(valid));
        System.out.println("Balanced Iterative (invalid): " + isBalancedIterative(invalid));
    }
}