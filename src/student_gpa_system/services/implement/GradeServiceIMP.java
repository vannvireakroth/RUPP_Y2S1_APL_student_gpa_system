package student_gpa_system.services.implement;

import student_gpa_system.services.GradeService;

public class GradeServiceIMP implements GradeService {

    protected int mathScore;
    protected int scienceScore;
    protected int englishScore;
    protected int historyScore;
    protected int artsScore;

    @Override
    public int getTotal() {
        return mathScore + scienceScore + englishScore
                + historyScore + artsScore;
    }

    @Override
    public double getGPA() {
        return getTotal() / 5.0;
    }

    @Override
    public String getGrade() {
        double gpa = getGPA();
        if (gpa >= 3.5) return "A";
        if (gpa >= 3) return "B";
        if (gpa >= 2) return "C";
        if (gpa >= 1) return "D";
        return "F";
    }
}