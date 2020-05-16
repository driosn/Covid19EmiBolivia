package models;

import globals.BoliviaDepartment;

public class Department {

    private String name;
    private String todayCases;
    private String totalCases;
    private String deceasesCases;
    private String recoveredCases;

    public Department(String name, String todayCases, String totalCases, String deceasesCases, String recoveredCases) {
        this.name = name;
        this.todayCases = todayCases;
        this.totalCases = totalCases;
        this.deceasesCases = deceasesCases;
        this.recoveredCases = recoveredCases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getDeceasesCases() {
        return deceasesCases;
    }

    public void setDeceasesCases(String deceasesCases) {
        this.deceasesCases = deceasesCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }
}
