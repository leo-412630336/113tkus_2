import java.util.*;

public class M04_TieredTaxSimple {
    private static int calcTax(int income) {
        int tax = 0;
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        tax += income * 5 / 100;
        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = 0;
        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = calcTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }
        System.out.println("Average: " + (total / n));
    }
}

/*
時間複雜度：O(n)
空間複雜度：O(1)
*/