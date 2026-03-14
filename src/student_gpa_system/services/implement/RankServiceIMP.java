package student_gpa_system.services.implement;

import student_gpa_system.services.RankService;

public class RankServiceIMP extends GradeServiceIMP implements RankService {

    @Override
    public double getAVG() {
        return getTotal() /5.0;  // inherited from GradeServiceIMP
    }
}