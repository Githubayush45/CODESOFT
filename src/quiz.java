import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApp {
    private static final int TIMELIMIT= 5; // seconds
    private static int quesno = 0;
    private static int score = 0;
    private static boolean answered = false;
    private static Timer timer;

    public static void main(String[] args) {
        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'),
                new Question("What is 2 + 2?", new String[]{"A. 3", "B. 4", "C. 5", "D. 6"}, 'B'),
                new Question("Which planet is known as the Red Planet?", new String[]{"A. Earth", "B. Venus", "C. Mars", "D. Jupiter"}, 'C'),
                new Question("Who wrote 'Romeo and Juliet'?", new String[]{"A. William Shakespeare", "B. Mark Twain", "C. Jane Austen", "D. Charles Dickens"}, 'A'),
                new Question("What is the largest ocean on Earth?", new String[]{"A. Atlantic Ocean", "B. Indian Ocean", "C. Arctic Ocean", "D. Pacific Ocean"}, 'D'),
                new Question("What is the square root of 64?", new String[]{"A. 6", "B. 7", "C. 8", "D. 9"}, 'C'),
                new Question("In which year did the Titanic sink?", new String[]{"A. 1905", "B. 1912", "C. 1918", "D. 1923"}, 'B'),
                new Question("What is the chemical symbol for gold?", new String[]{"A. Au", "B. Ag", "C. Pb", "D. Fe"}, 'A'),
                new Question("Who painted the Mona Lisa?", new String[]{"A. Vincent van Gogh", "B. Pablo Picasso", "C. Leonardo da Vinci", "D. Michelangelo"}, 'C'),
                new Question("What is the capital of Japan?", new String[]{"A. Beijing", "B. Seoul", "C. Tokyo", "D. Bangkok"}, 'C')
        };

        Scanner scanner = new Scanner(System.in);

        while (quesno < questions.length) {
            answered = false;
            display(questions[quesno]);

            timer = new Timer();
            timer.schedule(new TimerTask() {

                public void run() {
                    if (!answered) {
                        System.out.println("\n time finish");
                        quesno++;
                        if (quesno < questions.length) {
                            display(questions[quesno]);
                        } else {
                            res(questions);
                            System.exit(0);
                        }
                    }
                }
            }, TIMELIMIT* 1000);

            char ans = scanner.next().charAt(0);
            timer.cancel();
            answered = true;

            if (questions[quesno].isCorrect(ans)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            quesno++;
        }

        res(questions);
        scanner.close();
    }

    private static void display(Question question) {
        System.out.println(question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
        System.out.print("your answer: ");
    }

    private static void res(Question[] questions) {
        System.out.println("\nQuiz finsihed");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i].getQuestion());
            System.out.println("Correct answer: " + questions[i].getRightans());
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private char rightans;

    public Question(String question, String[] options, char rightans) {
        this.question = question;
        this.options = options;
        this.rightans = rightans;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public char getRightans() {
        return rightans;
    }

    public boolean isCorrect(char answer) {
        return answer == rightans;
    }
}

