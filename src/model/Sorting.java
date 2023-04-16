package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorting implements Comparator<Country> {


    public Sorting() {
    }

    public ArrayList<Country> sortingByCri(ArrayList<Country> listToSort, int sortCri) {
        for (int i = 1; i < listToSort.size(); i++) {
            Country current = listToSort.get(i);
            int j = i - 1;
            while (j >= 0 && current.compareMedals(listToSort.get(j), sortCri) > 0) {
                listToSort.set(j + 1, listToSort.get(j));
                --j;
            }
            listToSort.set(j + 1, current);
        }
        return listToSort;
    }

    public ArrayList<Country> sortingTotalMedals(ArrayList<Country> listToSort) {
        for (int i = 1; i < listToSort.size(); i++) {
            Country current = listToSort.get(i);
            int j = i - 1;
            while (j >= 0 && compare(current, listToSort.get(j)) > 0) {
                listToSort.set(j + 1, listToSort.get(j));
                --j;
            }
            listToSort.set(j + 1, current);
        }
        return listToSort;
    }

    public ArrayList<Country> sortAlphabetically(ArrayList<Country> listToSort) {
        for (int i = 1; i < listToSort.size(); i++) {
            Country current = listToSort.get(i);
            int j = i - 1;
            while (j >= 0 && current.compareTo(listToSort.get(j)) < 0) {
                listToSort.set(j + 1, listToSort.get(j));
                --j;
            }
            listToSort.set(j + 1, current);
        }
        return listToSort;
    }

    @Override
    public int compare(Country o1, Country o2) {
        int totalMedals1 = o1.getBronze() + o1.getSilver() + o1.getGolden();
        int totalMedals2 = o2.getBronze() + o2.getSilver() + o2.getGolden();
        return totalMedals1 - totalMedals2;
    }


}
