package com.example.covid19sistemas7mo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
import globals.Globals;
import models.Department;
import models.InfoGroupData;
import models.TotalData;
import utils.Utils;

public class MainActivity extends AppCompatActivity {

    GridView infoCardGv;
    Button reloadDataBtn;
    ImageView boliviaMapIv;
    ImageButton worldPageBtn;

    ArrayList<InfoGroupData> infoGroupDataList;
    ArrayList<Department> boliviaData;
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
        worldPageBtn = findViewById(R.id.worldLogoButton);

        boliviaMapIv.setDrawingCacheEnabled(true);
        boliviaMapIv.buildDrawingCache(true);

        TotalData boliviaTotalData = Utils.getBoliviaTotalInfo();
        boliviaData = Utils.getBoliviaDepartmentsCovidInfo();

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

                    int departmentIndex = Utils.checkDepartmentByRGB(r, g, b);

                    if(departmentIndex != -1) {
                        showDepartmentDialog(departmentIndex);
                    } else {
                        Toast.makeText(MainActivity.this, "Seleccione un departamento", Toast.LENGTH_SHORT).show();
                    }


                }
                return true;
            }
        });

        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_indefinitely);
        worldPageBtn.startAnimation(aniRotate);

        worldPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent worldIntent = new Intent(MainActivity.this, GlobalDataActivity.class);
                startActivity(worldIntent);
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

    protected ArrayList<InfoGroupData> formatDepartmentInfoGroupValues(Department department) {
        ArrayList<InfoGroupData> formatedData = new ArrayList<>();

        formatedData.add(new InfoGroupData("Casos de Hoy:", department.getTodayCases(), R.drawable.today_logo));
        formatedData.add(new InfoGroupData("Total Confirmados:", department.getTotalCases(), R.drawable.confirmed_logo));
        formatedData.add(new InfoGroupData("Total Decesos:", department.getDeceasesCases(), R.drawable.deceases_logo));
        formatedData.add(new InfoGroupData("Total Recuperados:", department.getRecoveredCases(), R.drawable.recovered_logo));

        return formatedData;
    }

    protected void showDepartmentDialog(int departmentIndex) {
        Department selectedDepartment = boliviaData.get(departmentIndex);


        ArrayList<InfoGroupData> departmentData = formatDepartmentInfoGroupValues(selectedDepartment);
        InfoCardAdapter infoCardAdapter = new InfoCardAdapter(MainActivity.this, R.layout.info_list_item, departmentData);

        AlertDialog.Builder departmentDialog = new AlertDialog.Builder(MainActivity.this);


        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        View view = factory.inflate(R.layout.department_info_dialog, null);

        TextView departmentNameTv = view.findViewById(R.id.departmentNameDialogTextView);
        ImageView departmentIv = view.findViewById(R.id.departmentDialogImageView);
        ListView departmentInfoLv = view.findViewById(R.id.departmentInfoListView);

        departmentNameTv.setText(selectedDepartment.getName());
        departmentIv.setImageResource(Globals.BOLIVIA_MAP_IMAGES[departmentIndex]);
        departmentInfoLv.setAdapter(infoCardAdapter);

        departmentDialog.setView(view);
        departmentDialog.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });
        departmentDialog.show();
    }

}

