package student_gpa_system.controller;

import student_gpa_system.constructor.Students;
import student_gpa_system.services.implement.StudentServiceIMP;

import java.util.Scanner;

public class StudentController {
    public StudentServiceIMP studentServiceIMP = new StudentServiceIMP();
    Scanner scanner = new Scanner(System.in);

    // ── 1. Add Student ────────────────────────────────────────────────────────
    public void AddStudent() {
        studentServiceIMP.addStudent();
    }

    // ── 2. View All Students ──────────────────────────────────────────────────
    public void ViewAllStudents() {
        studentServiceIMP.viewAllStudents();
    }

    // ── 3. Update Marks ───────────────────────────────────────────────────────
    public void UpdateMarks() {
        studentServiceIMP.updateMarks();
    }

    // ── 4. Delete Student ─────────────────────────────────────────────────────
    public void DeleteStudent() {
        studentServiceIMP.deleteStudent();
    }

    // ── 5. Show Rankings ──────────────────────────────────────────────────────
    public void ShowRankings() {
        studentServiceIMP.showRankings();
    }

    // ── 6. Search Student ─────────────────────────────────────────────────────
    public void SearchStudent() {
        studentServiceIMP.searchStudent();
    }

    public void PrintTranscript(){
        studentServiceIMP.printTranscript();
    }
}
