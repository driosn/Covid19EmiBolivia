package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class TotalGlobalDataContainer {

    @SerializedName("Global") public TotalGlobalData globalData;

    public TotalGlobalDataContainer() {}

    public TotalGlobalDataContainer(TotalGlobalData globalData) {
        this.globalData = globalData;
    }

    public static TotalGlobalData parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        TotalGlobalData totalGlobalData = gson.fromJson(response, TotalGlobalData.class);
        return totalGlobalData;
    }
}
