package student_gpa_system.services;

import student_gpa_system.controller.StudentController;

public interface StudentService {
    void addStudent();
    void viewAllStudents();
    void updateMarks();
    void deleteStudent();
    void showRankings();
    void searchStudent();
}
