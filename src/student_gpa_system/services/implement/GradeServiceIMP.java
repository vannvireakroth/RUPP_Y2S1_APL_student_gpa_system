package student_gpa_system.services.implement;

import student_gpa_system.services.GradeService;

public class GradeServiceIMP implements GradeService {

    protected int mathScore;
    protected int engligScore;
    protected int programScore;
    protected int networkScore;
    protected int databaseScore;

    @Override
    public int getTotal() {
        return mathScore + engligScore + programScore
                + networkScore + databaseScore;
    }

    @Override
    public double getAVG() {
        return getTotal() / 5.0;
    }

    @Override
    public String getGrade() {
        double gpa = getAVG();
        if (gpa >=90) return "A";
        if (gpa >= 80) return "B";
        if (gpa >= 70) return "C";
        if (gpa >= 60) return "D";
        if (gpa >= 50) return "E";
        return "F";
    }
}