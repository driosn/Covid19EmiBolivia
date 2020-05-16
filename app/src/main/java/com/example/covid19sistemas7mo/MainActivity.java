package com.example.covid19sistemas7mo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import globals.BoliviaDepartment;
import models.Department;
import models.TotalData;
import utils.Utils;

public class MainActivity extends AppCompatActivity {

    TextView firstText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        firstText = findViewById(R.id.firstText);
        button = findViewById(R.id.miboton);

        firstText.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Department> boliviaData = Utils.getBoliviaDepartmentsCovidInfo();
                TotalData boliviaTotalData = Utils.getBoliviaTotalInfo();

                firstText.setText(boliviaTotalData.getConfirmedCases() + "\n"+ boliviaTotalData.getRecoveredCases() + "\n"+ boliviaTotalData.getDeceasesCases() + "\n");

                for(Department department: boliviaData) {
                    firstText.setText(firstText.getText() + department.getName() + "\n"+ department.getTodayCases() + "\n"+ department.getTotalCases() + "\n"+ department.getDeceasesCases() + "\n"+ department.getRecoveredCases() + "\n\n");
                }
            }
        });

    }
}
