package student_gpa_system.controller;

import student_gpa_system.services.implement.StudentServiceIMP;

import java.util.Scanner;

public class StudentController {
    public StudentServiceIMP studentServiceIMP = new StudentServiceIMP();
    Scanner scanner = new Scanner(System.in);

    // ── 1. Add Student ────────────────────────────────────────────────────────
    public void handleAddStudent() {
        studentServiceIMP.addStudent();
    }

    // ── 2. View All Students ──────────────────────────────────────────────────
    public void handleViewAllStudents() {
        studentServiceIMP.viewAllStudents();
    }

    // ── 3. Update Marks ───────────────────────────────────────────────────────
    public void handleUpdateMarks() {
        studentServiceIMP.updateMarks();
    }

    // ── 4. Delete Student ─────────────────────────────────────────────────────
    public void handleDeleteStudent() {
        studentServiceIMP.deleteStudent();
    }

    // ── 5. Show Rankings ──────────────────────────────────────────────────────
    public void handleShowRankings() {
        studentServiceIMP.showRankings();
    }

    // ── 6. Search Student ─────────────────────────────────────────────────────
    public void handleSearchStudent() {
        studentServiceIMP.searchStudent();
    }

}
