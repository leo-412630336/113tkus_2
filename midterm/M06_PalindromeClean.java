import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().toLowerCase();
        int left = 0, right = s.length() - 1;
        boolean ok = true;
        while (left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) left++;
            while (left < right && !Character.isLetter(s.charAt(right))) right--;
            if (left < right && s.charAt(left) != s.charAt(right)) {
                ok = false;
                break;
            }
            left++;
            right--;
        }
        System.out.println(ok ? "Yes" : "No");
    }
}

/*
時間複雜度：O(n)
空間複雜度：O(1)
*/