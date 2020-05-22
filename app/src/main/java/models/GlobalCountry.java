package models;

import com.google.gson.annotations.SerializedName;

public class GlobalCountry {

    @SerializedName("Country") private String countryName;
    @SerializedName("CountryCode") private String countryCode;
    @SerializedName("Slug") private String slug;
    @SerializedName("NewConfirmed") private int newConfirmed;
    @SerializedName("TotalConfirmed") private int totalConfirmed;
    @SerializedName("NewDeaths") private int newDeaths;
    @SerializedName("TotalDeaths") private int totalDeaths;
    @SerializedName("NewRecovered") private int newRecovered;
    @SerializedName("TotalRecovered") private int totalRecovered;
    @SerializedName("Date") private String date;

    public GlobalCountry() {}

    public GlobalCountry(
            String countryName,
            String countryCode,
            String slug,
            int newConfirmed,
            int totalConfirmed,
            int newDeaths,
            int totalDeaths,
            int newRecovered,
            int totalRecovered,
            String date
    ) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.slug = slug;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
        this.date = date;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
