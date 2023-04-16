package model;

public class Country implements Comparable<Country> {
    private String name;
    private int golden;
    private int silver;
    private int bronze;

    public Country() {
        this.name = "";
        this.golden = 0;
        this.silver = 0;
        this.bronze = 0;
    }

    public Country(String name) {
        this.name = name;
        this.golden = 0;
        this.silver = 0;
        this.bronze = 0;
    }

    public int compareMedals(Country c, int sortCri) {
        int result = 0;
        try {
            switch (sortCri) {
                case 1:
                    result = this.golden - c.getGolden();
                    break;
                case 2:
                    result = this.silver - c.getSilver();
                    break;
                case 3:
                    result = this.bronze - c.getBronze();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sort criteria");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return result == 0 ? this.name.compareTo(c.getName()) : result;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.getName());
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getGolden() {
        return golden;
    }

    public void setGolden(int golden) {
        this.golden = golden;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "name: " + this.name + ", Golden medals: "
                + this.golden + ", Silver medals: " + this.silver + ", bronze medals: " +
                this.bronze;
    }


}