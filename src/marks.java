import java.util.Scanner;

public class marks{
    public static int ttmarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public static double avgper(int totalMarks, int subjs) {
        return (double) totalMarks / subjs;
    }
    public static String grade(double avper) {
        if (avper >= 90) {
            return "A - Awesome!";
        } else if (avper >= 80) {
            return "B - Brilliant!";
        } else if (avper >= 70) {
            return "C - Cool!";
        } else if (avper >= 60) {
            return "D - Decent!";
        } else {
            return "F - Fair effort, keep trying!";
        }
    }

    public static void result(int totalMarks, double avper, String grade) {

        System.out.println("Results");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", avper);
        System.out.println("Grade: " + grade);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the no of subjects: ");
        int subjs = scanner.nextInt();

        int[] marks = new int[subjs];
        for (int i = 0; i < subjs; i++) {//giving marks to each subj
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        int totalMarks = ttmarks(marks);//total marks
        double avper = avgper(totalMarks, subjs);// avg per
        String grade = grade(avper);// grade
        result(totalMarks, avper, grade);//results
        System.out.println("good😎");
    }
}
