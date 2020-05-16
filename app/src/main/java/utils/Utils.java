package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import globals.Globals;
import models.Department;
import models.TotalData;

public class Utils {

    public static ArrayList<Department> getBoliviaDepartmentsCovidInfo() {

        String REQUEST_URL = "https://www.boliviasegura.gob.bo";
        ArrayList<Department> boliviaData = new ArrayList<>();
        int departmentIndex = 0;

        try {
            Document doc = Jsoup.connect(REQUEST_URL).get();
            Elements link = doc.select("tr > td");

            ArrayList<String> results = new ArrayList<>();
            for(Element mElement: link) {
                if(mElement.ownText().length() > 0) {
                    results.add(mElement.ownText());
                };
            }

            int importantDataLines = 39;

            // ALMACENAMOS DATOS POR DEPARTAMENTO - Van de 4 en 4
            for(int i = 3; i < importantDataLines; i += 4) {

                Department currentDepartment =
                        new Department(Globals.BOLIVIA_DEPARTMENTS[departmentIndex], results.get(i), results.get(i + 1), results.get(i + 2), results.get(i + 3));

                departmentIndex++;
                boliviaData.add(currentDepartment);
            }

            return boliviaData;
        } catch (IOException e) {
            return null;
        }
    }

    public static TotalData getBoliviaTotalInfo() {

        String REQUEST_URL = "https://www.boliviasegura.gob.bo";
        ArrayList<Department> boliviaData = new ArrayList<>();
        int departmentIndex = 0;

        try {
            Document doc = Jsoup.connect(REQUEST_URL).get();
            Elements link = doc.select("tr > td");

            ArrayList<String> results = new ArrayList<>();
            for(Element mElement: link) {
                if(mElement.ownText().length() > 0) {
                    results.add(mElement.ownText());
                };
            }

            TotalData totalData = new TotalData(results.get(0), results.get(1), results.get(2));

            return totalData;
        } catch (IOException e) {
            return null;
        }
    }

}
