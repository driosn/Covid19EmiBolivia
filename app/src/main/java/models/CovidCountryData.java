package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidCountryData {

    @SerializedName("Countries") public List<GlobalCountry> countryList;

    public CovidCountryData() {}

    public CovidCountryData(List<GlobalCountry> countryList) {
        this.countryList = countryList;
    }

    public static GlobalCountry parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        GlobalCountry globalCountry = gson.fromJson(response, GlobalCountry.class);
        return globalCountry;
    }
}
