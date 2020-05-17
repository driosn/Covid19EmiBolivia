package com.example.covid19sistemas7mo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import adapters.InfoCardAdapter;
import globals.BoliviaDepartment;
import models.Department;
import models.InfoGroupData;
import models.TotalData;
import utils.Utils;

public class MainActivity extends AppCompatActivity {

    GridView infoCardGv;
    Button reloadDataBtn;
    ImageView boliviaMapIv;

    ArrayList<InfoGroupData> infoGroupDataList;
    InfoCardAdapter infoCardAdapter;

    Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        infoCardGv = findViewById(R.id.infoCardGridView);
        reloadDataBtn = findViewById(R.id.reloadDataButton);
        boliviaMapIv = findViewById(R.id.boliviaMapImageView);

        boliviaMapIv.setDrawingCacheEnabled(true);
        boliviaMapIv.buildDrawingCache(true);

        TotalData boliviaTotalData = Utils.getBoliviaTotalInfo();
        final ArrayList<Department> boliviaData = Utils.getBoliviaDepartmentsCovidInfo();

        infoGroupDataList = formatInfoGroupValues(boliviaTotalData);
        infoCardAdapter = new InfoCardAdapter(this, R.layout.info_grid_item, infoGroupDataList);

        infoCardGv.setAdapter(infoCardAdapter);

        boliviaMapIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bitmap = boliviaMapIv.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    Department selectedDepartment = boliviaData.get(Utils.checkDepartmentByRGB(r, g, b));

                    new MaterialAlertDialogBuilder(MainActivity.this)
                            .setTitle(selectedDepartment.getName())
                            .setMessage("Casos de hoy: " + selectedDepartment.getTodayCases() + "\n" + "Total confirmados: " + selectedDepartment.getTotalCases() + "\n" + "Total decesos: " + selectedDepartment.getDeceasesCases() + "\n" + "Total recuperados: " + selectedDepartment.getRecoveredCases() + "\n")
                            .show();

//                    Toast.makeText(MainActivity.this, selectedDepartment, Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    protected ArrayList<InfoGroupData> formatInfoGroupValues(TotalData boliviaTotalData) {
        ArrayList<InfoGroupData> formatedData = new ArrayList<>();

        formatedData.add(new InfoGroupData("Confirmados", boliviaTotalData.getConfirmedCases(), R.drawable.confirmed_logo));
        formatedData.add(new InfoGroupData("Decesos", boliviaTotalData.getDeceasesCases(), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Recuperados", boliviaTotalData.getRecoveredCases(), R.drawable.recovered_logo));

        return formatedData;
    }

}

