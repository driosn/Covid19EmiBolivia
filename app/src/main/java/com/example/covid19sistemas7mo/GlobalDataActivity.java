package com.example.covid19sistemas7mo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adapters.CountryCardAdapter;
import adapters.InfoCardAdapter;
import globals.Globals;
import models.CovidCountryData;
import models.Department;
import models.GlobalCountry;
import models.InfoGroupData;
import models.TotalGlobalData;
import models.TotalGlobalDataContainer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.CovidService;

public class GlobalDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView countryListGv;

    TotalGlobalData globalData;
    List<GlobalCountry> countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_data);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        countryListGv = findViewById(R.id.countryGridView);

        fetchGlobalData();
        fetchCountriesData();

    }

    public void fetchGlobalData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidService covidService = retrofit.create(CovidService.class);

        Call<TotalGlobalDataContainer> totalGlobalDataCall = covidService.getGlobalData();

        totalGlobalDataCall.enqueue(new Callback<TotalGlobalDataContainer>() {
            @Override
            public void onResponse(Call<TotalGlobalDataContainer> call, Response<TotalGlobalDataContainer> response) {
                globalData = response.body().globalData;
            }

            @Override
            public void onFailure(Call<TotalGlobalDataContainer> call, Throwable t) {
                Toast.makeText(GlobalDataActivity.this, "Hubo un error al traer la data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchCountriesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidService covidService = retrofit.create(CovidService.class);

        Call<CovidCountryData> covidCountryDataCall = covidService.getCountriesData();

        covidCountryDataCall.enqueue(new Callback<CovidCountryData>() {
            @Override
            public void onResponse(Call<CovidCountryData> call, Response<CovidCountryData> response) {

                countriesList = response.body().countryList;

                List<String> countriesListName = new ArrayList<>();

                for(GlobalCountry country : countriesList) {
                    countriesListName.add(country.getCountryName());
                }

                CountryCardAdapter countryAdapter = new CountryCardAdapter(GlobalDataActivity.this, R.layout.country_grid_item, countriesList);
                countryListGv.setAdapter(countryAdapter);
                countryListGv.setOnItemClickListener(GlobalDataActivity.this);
            }

            @Override
            public void onFailure(Call<CovidCountryData> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDepartmentDialog(countriesList.get(position));
    }

    protected void showDepartmentDialog(GlobalCountry selectedCountry) {

        ArrayList<InfoGroupData> countryData = formatCountryInfoGroupValues(selectedCountry);
        InfoCardAdapter infoCardAdapter = new InfoCardAdapter(GlobalDataActivity.this, R.layout.info_list_item, countryData);

        AlertDialog.Builder countryDialog = new AlertDialog.Builder(GlobalDataActivity.this);


        LayoutInflater factory = LayoutInflater.from(GlobalDataActivity.this);
        View view = factory.inflate(R.layout.department_info_dialog, null);

        TextView countryNameTv = view.findViewById(R.id.departmentNameDialogTextView);
        ImageView countryFlagIv = view.findViewById(R.id.departmentDialogImageView);
        ListView countryInfoLv = view.findViewById(R.id.departmentInfoListView);

        Picasso.get().load("https://www.countryflags.io/" + selectedCountry.getCountryCode().toLowerCase() + "/shiny/64.png").into(countryFlagIv);
        countryNameTv.setText(selectedCountry.getCountryName());
        countryInfoLv.setAdapter(infoCardAdapter);

        countryDialog.setView(view);
        countryDialog.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });
        countryDialog.show();
    }

    protected ArrayList<InfoGroupData> formatCountryInfoGroupValues(GlobalCountry country) {
        ArrayList<InfoGroupData> formatedData = new ArrayList<>();

        formatedData.add(new InfoGroupData("Nuevos confirmados:", String.valueOf(country.getNewConfirmed()), R.drawable.total_logo));
        formatedData.add(new InfoGroupData("Total confirmados:", String.valueOf(country.getTotalConfirmed()), R.drawable.confirmed_logo));
        formatedData.add(new InfoGroupData("Nuevos decesos:", String.valueOf(country.getNewDeaths()), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Total decesos:", String.valueOf(country.getTotalDeaths()), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Nuevos recuperados:", String.valueOf(country.getNewRecovered()), R.drawable.recovered_logo));
        formatedData.add(new InfoGroupData("Total recuperados:", String.valueOf(country.getTotalRecovered()), R.drawable.recovered_logo));
        formatedData.add(new InfoGroupData("Fecha de los datos:", country.getDate(), R.drawable.today_logo));

        return formatedData;
    }

    protected ArrayList<InfoGroupData> formatWorldInfoGroupValues(TotalGlobalData globalData) {
        ArrayList<InfoGroupData> formatedData = new ArrayList<>();

        formatedData.add(new InfoGroupData("Nuevos confirmados:", String.valueOf(globalData.getNewConfirmed()), R.drawable.total_logo));
        formatedData.add(new InfoGroupData("Total confirmados:", String.valueOf(globalData.getTotalConfirmed()), R.drawable.confirmed_logo));
        formatedData.add(new InfoGroupData("Nuevos decesos:", String.valueOf(globalData.getNewDeaths()), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Total decesos:", String.valueOf(globalData.getTotalDeaths()), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Nuevos recuperados:", String.valueOf(globalData.getNewRecovered()), R.drawable.recovered_logo));
        formatedData.add(new InfoGroupData("Total recuperados:", String.valueOf(globalData.getTotalRecovered()), R.drawable.recovered_logo));

        return formatedData;
    }

    protected void showWorldDialog(TotalGlobalData totalData) {

        ArrayList<InfoGroupData> formattedTotalData = formatWorldInfoGroupValues(totalData);
        InfoCardAdapter infoCardAdapter = new InfoCardAdapter(GlobalDataActivity.this, R.layout.info_list_item, formattedTotalData);

        AlertDialog.Builder worldDIalog = new AlertDialog.Builder(GlobalDataActivity.this);

        LayoutInflater factory = LayoutInflater.from(GlobalDataActivity.this);
        View view = factory.inflate(R.layout.department_info_dialog, null);

        TextView worldNameTv = view.findViewById(R.id.departmentNameDialogTextView);
        ImageView worldIconIv = view.findViewById(R.id.departmentDialogImageView);
        ListView worldInfoLv = view.findViewById(R.id.departmentInfoListView);


        worldIconIv.setImageResource(R.drawable.world_logo_button);
        worldNameTv.setText("Datos Mundiales");
        worldInfoLv.setAdapter(infoCardAdapter);

        worldDIalog.setView(view);
        worldDIalog.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });
        worldDIalog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.worldLogoIcon:
                showWorldDialog(globalData);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
