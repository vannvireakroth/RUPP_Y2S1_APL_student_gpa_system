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
    public double getAverage() {
        return getTotal() / 5.0;
    }

    @Override
    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }
}