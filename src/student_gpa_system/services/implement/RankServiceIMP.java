package student_gpa_system.services.implement;

import student_gpa_system.services.RankService;

public class RankServiceIMP extends GradeServiceIMP implements RankService {

    @Override
    public double getGPA() {
        return getTotal() /125.0;  // inherited from GradeServiceIMP
    }
}