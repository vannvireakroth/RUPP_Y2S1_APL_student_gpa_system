package student_gpa_system.constructor;

import student_gpa_system.services.implement.RankServiceIMP;

public class Students extends RankServiceIMP {

    private static int nextId = 1;

    public int    id;
    public String name;

    public Students(String name, int math, int english,
                   int program, int network, int database) {
        this.id           = nextId++;
        this.name         = name;
        this.mathScore    = math;
        this.engligScore = english;
        this.programScore = program;
        this.networkScore = network;
        this.databaseScore = database;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%-3d  %-20s  Total:%-4d  Avg:%-5.1f  Grade:%s",
                id, name, getTotal(), this.getAVG(), getGrade());
    }
}