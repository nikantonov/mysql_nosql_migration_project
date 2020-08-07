package source.mongomodels;

public class Carinfo_mongo {
    private int numberOfRents;
    private double km;
    private String current_Place;

    public Carinfo_mongo(){}
    public Carinfo_mongo(int numberOfRents,double km, String current_place) {
        this.numberOfRents = numberOfRents;
        this.km = km;
        this.current_Place = current_place;
    }

    public int getNumberOfRents() {
        return numberOfRents;
    }

    public void setNumberOfRents(int numberOfRents) {
        this.numberOfRents = numberOfRents;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getCurrent_Place() {
        return current_Place;
    }

    public void setCurrent_Place(String current_Place) {
        this.current_Place = current_Place;
    }
}
