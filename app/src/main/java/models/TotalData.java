package models;

public class TotalData {

    private String confirmedCases;
    private String deceasesCases;
    private String recoveredCases;

    public TotalData(String confirmedCases, String deceasesCases, String recoveredCases) {
        this.confirmedCases = confirmedCases;
        this.deceasesCases = deceasesCases;
        this.recoveredCases = recoveredCases;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(String confirmedCases) {
        this.confirmedCases = confirmedCases;
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
