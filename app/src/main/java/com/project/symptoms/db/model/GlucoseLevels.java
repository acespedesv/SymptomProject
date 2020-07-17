package com.project.symptoms.db.model;

public class GlucoseLevels {

    private int id;
    private String level;
    private String fastingPlasma;
    private String toleranceTest;

    public GlucoseLevels(int id, String level, String fastingPlasma, String toleranceTest) {
        this.id = id;
        this.level = level;
        this.fastingPlasma = fastingPlasma;
        this.toleranceTest = toleranceTest;
    }

    public GlucoseLevels(String level, String fastingPlasma, String toleranceTest) {
        this.level = level;
        this.fastingPlasma = fastingPlasma;
        this.toleranceTest = toleranceTest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFastingPlasma() {
        return fastingPlasma;
    }

    public void setFastingPlasma(String fastingPlasma) {
        this.fastingPlasma = fastingPlasma;
    }

    public String getToleranceTest() {
        return toleranceTest;
    }

    public void setToleranceTest(String toleranceTest) {
        toleranceTest = toleranceTest;
    }
}
