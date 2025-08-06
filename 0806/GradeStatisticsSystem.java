public class GradeStatisticsSystem {

    public static char getGrade(int score) {
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int total = 0;
        int max = scores[0];
        int min = scores[0];

        int[] gradeCount = new int[5];

        System.out.println("學生\t分數\t等第");
        for (int i = 0; i < scores.length; i++) {
            int score = scores[i];
            char grade = getGrade(score);

            System.out.println((i + 1) + "\t" + score + "\t" + grade);

            total += score;
            if (score > max) max = score;
            if (score < min) min = score;

            switch (grade) {
                case 'A': gradeCount[0]++; break;
                case 'B': gradeCount[1]++; break;
                case 'C': gradeCount[2]++; break;
                case 'D': gradeCount[3]++; break;
                case 'F': gradeCount[4]++; break;
            }
        }

        double average = total / (double) scores.length;

        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) aboveAverageCount++;
        }

        System.out.println("\n--- 成績統計報表 ---");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("等第人數：");
        System.out.println("A: " + gradeCount[0]);
        System.out.println("B: " + gradeCount[1]);
        System.out.println("C: " + gradeCount[2]);
        System.out.println("D: " + gradeCount[3]);
        System.out.println("F: " + gradeCount[4]);
        System.out.println("高於平均的人數：" + aboveAverageCount);
    }
}