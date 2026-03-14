package student_gpa_system.services.implement;

import student_gpa_system.constructor.Students;
import student_gpa_system.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentServiceIMP implements StudentService {

    List<Students> students = new ArrayList<>();
    Scanner        scanner  = new Scanner(System.in);

    // ── 1. Add Student ────────────────────────────────────────────────────────
    @Override
    public void addStudent() {
        System.out.println("\n  ── Add New Student ──");
        System.out.print("  Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) { System.out.println("  ✗ Name cannot be empty."); return; }

        int math    = readScore("  Math score     (0-100): ");
        int science = readScore("  English score  (0-100): ");
        int english = readScore("  Program score  (0-100): ");
        int history = readScore("  Network score  (0-100): ");
        int arts    = readScore("  Database score (0-100): ");

        Students s = new Students(name, math, science, english, history, arts);
        students.add(s);
        System.out.printf("  ✓ Student added! ID: %d | Total: %d | Avg: %.1f | Grade: %s%n",
                s.id, s.getTotal(), s.getAVG(), s.getGrade());
    }

    // ── 2. View All Students ──────────────────────────────────────────────────
    @Override
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n  ✗ No students on record.");
            return;
        }

        System.out.println("\n  ── All Students ──");
        printHeader();
        for (Students s : students) {
            System.out.printf("  %-7d %-22s %-10d %-10d %-10d %-10d %-10d %-8d %-8.1f %-6s%n",
                    s.id, s.name,
                    s.mathScore, s.engligScore, s.programScore,
                    s.networkScore, s.databaseScore,
                    s.getTotal(), s.getAVG(), s.getGrade());
        }
        printFooter();
    }

    // ── 3. Update Marks ───────────────────────────────────────────────────────
    @Override
    public void updateMarks() {
        int id = readInt("\n  Enter student ID to update: ");
        Students s = findById(id);
        if (s == null) { System.out.println("  ✗ Student not found."); return; }

        System.out.println("  Found: " + s.name);
        System.out.println("  Leave blank and press Enter to keep current value.");

        s.mathScore    = readScoreOptional("  Math     (current " + s.mathScore    + "): ", s.mathScore);
        s.engligScore = readScoreOptional("  English  (current " + s.engligScore + "): ", s.engligScore);
        s.programScore = readScoreOptional("  Program  (current " + s.programScore + "): ", s.programScore);
        s.networkScore = readScoreOptional("  Network  (current " + s.networkScore + "): ", s.networkScore);
        s.databaseScore = readScoreOptional("  Database (current " + s.databaseScore + "): ", s.databaseScore);

        System.out.printf("  ✓ Marks updated! Total: %d | Avg: %.1f | Grade: %s%n",
                s.getTotal(), s.getAVG(), s.getGrade());
    }

    // ── 4. Delete Student ─────────────────────────────────────────────────────
    @Override
    public void deleteStudent() {
        int id = readInt("\n  Enter student ID to delete: ");
        Students s = findById(id);
        if (s == null) { System.out.println("  ✗ Student not found."); return; }

        System.out.print("  Delete \"" + s.name + "\"? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            students.remove(s);
            System.out.println("  ✓ Student deleted.");
        } else {
            System.out.println("  Cancelled.");
        }
    }

    // ── 5. Show Rankings ──────────────────────────────────────────────────────
    @Override
    public void showRankings() {
        if (students.isEmpty()) { System.out.println("\n  ✗ No students to rank."); return; }

        List<Students> ranked = new ArrayList<>(students);
        ranked.sort((a, b) -> Double.compare(b.getAVG(), a.getAVG()));

        System.out.println("\n        STUDENT RANKINGS");
        System.out.println("  " + "─".repeat(50));
        System.out.printf("  %-4s  %-18s  %-6s  %-6s  %-6s%n",
                "Rank", "Name", "Total", "AVG", "Grade");
        System.out.println("  " + "─".repeat(50));

        int    rank        = 1;
        double prevAvg     = -1;
        int    displayRank = 1;

        for (int i = 0; i < ranked.size(); i++) {
            Students s = ranked.get(i);
            if (i == 0 || s.getAVG() != prevAvg) displayRank = rank;

            System.out.printf("  %-4d  %-18s  %-6d  %-6.1f  %-6s%n",
                    displayRank, s.name, s.getTotal(), s.getAVG(), s.getGrade());

            prevAvg = s.getAVG();
            rank++;
        }

        System.out.println("  " + "─".repeat(50));
        double classAvg = ranked.stream().mapToDouble(Students::getAVG).average().orElse(0);
        double highest  = ranked.get(0).getAVG();
        double lowest   = ranked.get(ranked.size() - 1).getAVG();
        System.out.printf("  Class Avg: %-6.1f  Highest: %-6.1f  Lowest: %-6.1f%n",
                classAvg, highest, lowest);
        System.out.println("  " + "─".repeat(50));
    }

    // ── 6. Search Student ─────────────────────────────────────────────────────
    @Override
    public void searchStudent() {
        System.out.print("\n  Search by name or ID: ");
        String query = scanner.nextLine().trim().toLowerCase();

        List<Students> results = new ArrayList<>();

        for (Students s : students) {
            if (s.name.toLowerCase().contains(query) || String.valueOf(s.id).equals(query))
                results.add(s);
        }

        if (results.isEmpty()) {
            System.out.println("  ✗ No students found matching \"" + query + "\".");
        } else {
            System.out.println("  Found " + results.size() + " result(s):");
            printHeader();
            for (Students s : results) {
                System.out.printf("  %-7d %-22s %-10d %-10d %-10d %-10d %-10d %-8d %-8.1f %-6s%n",
                        s.id, s.name,
                        s.mathScore, s.engligScore, s.programScore,
                        s.networkScore, s.databaseScore,
                        s.getTotal(), s.getAVG(), s.getGrade());
            }
            printFooter();
        }
    }

    @Override
    public void printTranscript(){
        System.out.print("\n  Search by name or ID: ");
        String query = scanner.nextLine().trim().toLowerCase();

        List<Students> results = new ArrayList<>();
        for (Students s : students) {
            if (s.name.toLowerCase().contains(query) || String.valueOf(s.id).equals(query))
                results.add(s);
        }

        if (results.isEmpty()) {
            System.out.println("  ✗ No students found matching \"" + query + "\".");
            return;
        }

        for (Students s : results) {
            System.out.println("  " + "─".repeat(60));
            System.out.println("                      STUDENT TRANSCRIPT");
            System.out.println("  " + "─".repeat(60));
            System.out.printf("  ID: %-8d  Name: %-30s GPA: %-9.1f%n", s.id, s.name, s.getAVG()/25);
            System.out.println("  " + "─".repeat(60));
            System.out.printf(" %-8s  %-22s  %-8s  %-6s  %-8s%n",
                    "Code", "Subject", "Credit", "Score", "GPA");
            System.out.println("  " + "─".repeat(60));
            System.out.printf(" %-8s  %-22s  %-8d  %-6d  %-8s%n", "SUB-001", "Math",     3, s.mathScore,    subjectGPA(s.mathScore));
            System.out.printf(" %-8s  %-22s  %-8d  %-6d  %-8s%n", "SUB-002", "English",  3, s.engligScore, subjectGPA(s.engligScore));
            System.out.printf(" %-8s  %-22s  %-8d  %-6d  %-8s%n", "SUB-003", "Program",  3, s.programScore, subjectGPA(s.programScore));
            System.out.printf(" %-8s  %-22s  %-8d  %-6d  %-8s%n", "SUB-004", "Network",  3, s.networkScore, subjectGPA(s.networkScore));
            System.out.printf(" %-8s  %-22s  %-8d  %-6d  %-8s%n", "SUB-005", "Database", 3, s.databaseScore,    subjectGPA(s.databaseScore));
            System.out.println("  " + "─".repeat(60));
        }
    };

    // ── Seed Sample Data ──────────────────────────────────────────────────────
    public void seedData() {
        students.add(new Students("Alice Johnson",  92, 88, 95, 87, 90));
        students.add(new Students("Bob Martinez",   74, 80, 68, 75, 72));
        students.add(new Students("Clara Nguyen",   85, 91, 87, 89, 84));
        students.add(new Students("David Okafor",   60, 55, 72, 65, 58));
        students.add(new Students("Eva Kowalski",   97, 94, 99, 96, 98));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private Students findById(int id) {
        return students.stream()
                .filter(s -> s.id == id)
                .findFirst()
                .orElse(null);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  ✗ Enter a valid number."); }
        }
    }

    private int readScore(String prompt) {
        while (true) {
            int val = readInt(prompt);
            if (val >= 0 && val <= 100) return val;
            System.out.println("  ✗ Score must be 0–100.");
        }
    }

    private int readScoreOptional(String prompt, int current) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return current;
        try {
            int val = Integer.parseInt(input);
            if (val >= 0 && val <= 100) return val;
            System.out.println("  ✗ Invalid. Keeping current value.");
        } catch (NumberFormatException e) {
            System.out.println("  ✗ Invalid. Keeping current value.");
        }
        return current;
    }

    private void printHeader() {
        System.out.println("  " + "─".repeat(105));
        System.out.printf("  %-7s %-22s %-10s %-10s %-10s %-10s %-10s %-8s %-8s %-6s%n",
                "ID", "Name", "Math", "English", "Program", "Network", "Database", "Total", "AVG", "Grade");
        System.out.println("  " + "─".repeat(105));
    }

    private void printFooter() {
        System.out.println("  " + "─".repeat(105));
    }

    private String subjectGPA(int score) {
        if (score >= 90) return "4.0 (A)";
        if (score >= 80) return "3.5 (B+)";
        if (score >= 70) return "3.0 (B)";
        if (score >= 60) return "2.0 (C)";
        if (score >= 50) return "1.0 (D)";
        return                  "0.0 (F)";
    }
}