package services;

import models.CovidCountryData;
import models.TotalGlobalData;
import models.TotalGlobalDataContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidService {
    @GET("summary")
    Call<TotalGlobalDataContainer> getGlobalData();

    @GET("summary")
    Call<CovidCountryData> getCountriesData();
}
