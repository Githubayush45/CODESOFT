import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<Student> studentincourse;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.studentincourse = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - studentincourse.size();
    }

    public boolean enrollStudent(Student student) {
        if (getAvailableSlots() > 0) {
            studentincourse.add(student);
            return true;
        } else {
            return false;
        }
    }

    public void dropStudent(Student student) {
        studentincourse.remove(student);
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Slots: " + getAvailableSlots());
        System.out.println("Schedule: " + String.join(", ", schedule));
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public String getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent(this)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Failed to register. No available slots for course: " + course.getTitle());
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent(this);
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for course: " + course.getTitle());
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + ":");
        for (Course course : registeredCourses) {
            System.out.println("- " + course.getTitle());
        }
    }
}

class CourseManagementSystem {
    private static HashMap<String, Course> courseDatabase = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        addSampleData();

        while (true) {
            System.out.println("\nCourse Management System:");
            System.out.println("1. List Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Drop Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addSampleData() {
        List<String> schedule1 = new ArrayList<>();
        schedule1.add("Monday 10:00-11:30");
        schedule1.add("Wednesday 10:00-11:30");

        List<String> schedule2 = new ArrayList<>();
        schedule2.add("Tuesday 14:00-15:30");
        schedule2.add("Thursday 14:00-15:30");

        Course course1 = new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 30, schedule1);
        Course course2 = new Course("MATH101", "Calculus I", "Introduction to differential and integral calculus.", 25, schedule2);

        courseDatabase.put(course1.getCode(), course1);
        courseDatabase.put(course2.getCode(), course2);

        Student student1 = new Student("S001", "AYUSH");
        Student student2 = new Student("S002", "JOHN");

        studentDatabase.put(student1.getStudentID(), student1);
        studentDatabase.put(student2.getStudentID(), student2);
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            course.displayCourseDetails();
            System.out.println();
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student != null) {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            Course course = courseDatabase.get(code);

            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Invalid course code.");
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student != null) {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            Course course = courseDatabase.get(code);

            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Invalid course code.");
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}
