package model;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private ArrayList<Country> countries;
    private Sorting sorting;

    public Controller() {
        countries = new ArrayList<>();
        sorting = new Sorting();
    }

    public void readGson() {
        Gson gson = new Gson();
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir + "/data");
        File result = new File(projectDir + "/data/result.json");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            String json = new String(java.nio.file.Files.readAllBytes(result.toPath()));
            Country[] countries = gson.fromJson(json, Country[].class);
            if (countries != null) {
                this.countries.addAll(Arrays.asList(countries));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGson() {
        Gson gson = new Gson();
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir + "/data");
        File result = new File(projectDir + "/data/result.json");
        String countries = gson.toJson(this.countries);
        try {
            FileOutputStream fos = new FileOutputStream(result);
            fos.write(countries.getBytes(StandardCharsets.UTF_8));
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCountry(String name, int golden, int silver, int bronze) {
        Country country = search(name);
        if (country == null) {
            country = new Country(name);
            countries.add(country);
        }
        country.setGolden(country.getGolden() + golden);
        country.setSilver(country.getSilver() + silver);
        country.setBronze(country.getBronze() + bronze);
    }

    public String sortMedals() {
        String msg = "";
        ArrayList<Country> g = new ArrayList<>();
        ArrayList<Country> s = new ArrayList<>();
        ArrayList<Country> b = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getGolden() > 0) {
                g.add(countries.get(i));
            }
            if (countries.get(i).getSilver() > 0) {
                s.add(countries.get(i));
            }
            if (countries.get(i).getBronze() > 0) {
                b.add(countries.get(i));
            }
        }
        msg += "GOLD MEDALS:" + "\n";
        g = sorting.sortingByCri(g, 1);
        for (Country c : g) {
            msg += c.toString() + "\n";
        }
        msg += "SILVER MEDALS:" + "\n";
        s = sorting.sortingByCri(s, 2);
        for (Country c : s) {
            msg += c.toString() + "\n";
        }
        msg += "BRONZE MEDALS:" + "\n";
        b = sorting.sortingByCri(b, 3);
        for (Country c : b) {
            msg += c.toString() + "\n";
        }
        return msg;
    }

    private Country search(String name) {
        Country country = null;
        for (Country value : countries) {
            if (value.getName().equals(name)) {
                country = value;
            }
        }
        return country;
    }

    public String sortTotalMedals() {
        String msg = "Ranking by total medals: " + "\n";
        ArrayList<Country> total = sorting.sortingTotalMedals(countries);
        for (Country c : total) {
            msg += c.toString() + "\n";
        }
        return msg;
    }

    public String sortByName() {
        String msg = "Ranking by alphabetical order: " + "\n";
        ArrayList<Country> total = sorting.sortAlphabetically(countries);
        for (Country c : total) {
            msg += c.toString() + "\n";
        }
        return msg;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public Sorting getSorting() {
        return sorting;
    }

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }
}
